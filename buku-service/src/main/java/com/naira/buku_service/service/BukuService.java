package com.naira.buku_service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.naira.buku_service.model.Buku;
import com.naira.buku_service.repository.BukuRepository;

@Service
public class BukuService {
    @Autowired
    private BukuRepository BukuRepository;

    public List<Buku> getAllBukus(){
    return BukuRepository.findAll();
    }

    public Buku getBukuById(Long id) {
    return BukuRepository.findById(id).orElse(null);
    }

    public Buku createBuku(Buku buku){
    return BukuRepository.save(buku);
    }

    public void deleteBuku (Long id){
    BukuRepository.deleteById(id);
    }
}
