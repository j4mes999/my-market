package com.luis.mymarket.domain.service;

import com.luis.mymarket.domain.Purchase;
import com.luis.mymarket.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByCustomer(String customerID){
        return purchaseRepository.getByCustomer(customerID);
    }

    public Purchase savePurchase(Purchase purchase){
        return purchaseRepository.save(purchase);
    }
}
