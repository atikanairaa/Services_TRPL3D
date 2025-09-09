package com.naira.order_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naira.order_service.model.Order;
import com.naira.order_service.repository.OrderRepository;
import com.naira.order_service.vo.Pelanggan;
import com.naira.order_service.vo.Produk;
import com.naira.order_service.vo.ResponseTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository OrderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders(){
    return OrderRepository.findAll();
    }

    public Order getOrderById(Long id) {
    return OrderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order){
    return OrderRepository.save(order);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Order order = getOrderById(id);
        Produk produk = restTemplate.getForObject("http://localhost:8081/api/produk/"
                + order.getProdukId(), Produk.class);
        Pelanggan pelanggan = restTemplate.getForObject("http://localhost:8082/api/pelanggan/"
                + order.getPelangganId(), Pelanggan.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);
        responseList.add(vo);
        return responseList;
    }

    public void deleteOrder (Long id){
    OrderRepository.deleteById(id);
    }
}
