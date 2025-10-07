package com.naira.peminjaman_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naira.peminjaman_service.event.PeminjamanCreatedEvent;
import com.naira.peminjaman_service.event.PeminjamanDeletedEvent;
import com.naira.peminjaman_service.model.Peminjaman;
import com.naira.peminjaman_service.repository.PeminjamanRepository;
import com.naira.peminjaman_service.vo.Anggota;
import com.naira.peminjaman_service.vo.Buku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // NEW
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PeminjamanCommandService { // Renamed from PeminjamanService
    @Autowired
    private PeminjamanRepository peminjamanRepository; // Renamed for clarity

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;
    
    @Autowired
    private EmailService emailService;

    // Define a DateTimeFormatter for the desired format: "dd-MMMM-yyyy" (e.g., 26-October-2023)
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("id", "ID"));

    @Transactional // Ensures atomicity: save + send event + send email
    public Peminjaman createPeminjaman(Peminjaman peminjaman){
        peminjaman.setTanggalPeminjaman(LocalDate.now());
        peminjaman.setTanggalPengembalian(LocalDate.now().plusDays(7));

        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);

        // Fetch Anggota details to get email and name
        ServiceInstance anggotaServiceInstance = discoveryClient.getInstances("anggota-service").get(0);
        Anggota anggota = restTemplate.getForObject(anggotaServiceInstance.getUri() + "/api/anggota/"
                + savedPeminjaman.getAnggotaId(), Anggota.class);

        // Fetch Buku details for the email content
        ServiceInstance bukuServiceInstance = discoveryClient.getInstances("buku-service").get(0);
        Buku buku = restTemplate.getForObject(bukuServiceInstance.getUri() + "/api/buku/"
                + savedPeminjaman.getBukuId(), Buku.class);

        // Send Email Notification
        if (anggota != null && anggota.getEmail() != null) {
            String subject = "Peminjaman Buku Berhasil!";
            
            String formattedTanggalPeminjaman = savedPeminjaman.getTanggalPeminjaman().format(DATE_FORMATTER);
            String formattedTanggalPengembalian = savedPeminjaman.getTanggalPengembalian().format(DATE_FORMATTER);

            String body = String.format(
                "Yth. Bapak/Ibu %s,\n\n" +
                "Anda telah berhasil melakukan peminjaman buku dengan rincian sebagai berikut:\n\n" +
                "--- Rincian Peminjaman ---\n" +
                "ID Peminjaman: %d\n" +
                "Judul Buku: %s\n" +
                "Tanggal Pinjam: %s\n" +
                "Tanggal Harus Kembali: %s\n" +
                "--------------------------\n\n" +
                "Mohon untuk mengembalikan buku tepat waktu untuk menghindari denda. Terima kasih.\n\n" +
                "Salam,\n" +
                "Admin Perpustakaan",
                anggota.getNama(),
                savedPeminjaman.getId(),
                buku != null ? buku.getJudul() : "Judul Tidak Diketahui",
                formattedTanggalPeminjaman,
                formattedTanggalPengembalian
            );

            emailService.sendEmail(anggota.getEmail(), subject, body);
        }

        // NEW: Publish PeminjamanCreatedEvent to RabbitMQ for Read Model update
        PeminjamanCreatedEvent event = new PeminjamanCreatedEvent(
                savedPeminjaman.getId(),
                savedPeminjaman.getAnggotaId(),
                savedPeminjaman.getBukuId(),
                savedPeminjaman.getTanggalPeminjaman(),
                savedPeminjaman.getTanggalPengembalian(),
                anggota != null ? anggota.getNama() : null,
                anggota != null ? anggota.getEmail() : null,
                buku != null ? buku.getJudul() : null
        );
        rabbitMQProducer.sendEvent(event, "created"); // Send event with type "created"

        return savedPeminjaman;
    }

    @Transactional
    public void deletePeminjaman (Long id){
        peminjamanRepository.deleteById(id);
        // NEW: Publish PeminjamanDeletedEvent to RabbitMQ
        PeminjamanDeletedEvent event = new PeminjamanDeletedEvent(id);
        rabbitMQProducer.sendEvent(event, "deleted"); // Send event with type "deleted"
    }

    // Method getPeminjamanById and getAllPeminjamans moved to PeminjamanQueryService
}