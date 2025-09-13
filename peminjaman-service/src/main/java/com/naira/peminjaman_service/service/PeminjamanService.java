package com.naira.peminjaman_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naira.peminjaman_service.model.Peminjaman;
import com.naira.peminjaman_service.repository.PeminjamanRepository;
import com.naira.peminjaman_service.vo.Anggota;
import com.naira.peminjaman_service.vo.Buku;
import com.naira.peminjaman_service.vo.ResponseTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;


@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository PeminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public List<Peminjaman> getAllPeminjamans(){
    return PeminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id) {
    return PeminjamanRepository.findById(id).orElse(null);
    }

    public Peminjaman createPeminjaman(Peminjaman peminjaman){
    return PeminjamanRepository.save(peminjaman);
    }

    public List<ResponseTemplate> getPeminjamanWithBookById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Peminjaman peminjaman = getPeminjamanById(id);
        ServiceInstance serviceInstance = discoveryClient.getInstances("buku-service").get(0);
        Buku buku = restTemplate.getForObject(serviceInstance.getUri() + "/api/buku/"
                + peminjaman.getBukuId(), Buku.class);
                serviceInstance = discoveryClient.getInstances("anggota-service").get(0);
        Anggota anggota = restTemplate.getForObject(serviceInstance.getUri() + "/api/anggota/"
                + peminjaman.getAnggotaId(), Anggota.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setBuku(buku);
        vo.setAnggota(anggota);
        responseList.add(vo);
        return responseList;
    }

    public void deletePeminjaman (Long id){
    PeminjamanRepository.deleteById(id);
    }
}