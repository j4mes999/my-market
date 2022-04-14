package com.luis.mymarket.persistence;


import com.luis.mymarket.domain.Purchase;
import com.luis.mymarket.domain.repository.PurchaseRepository;
import com.luis.mymarket.persistence.crud.CompraCrudRepository;
import com.luis.mymarket.persistence.entity.Compra;
import com.luis.mymarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return compraCrudRepository.findByIdCliente(customerId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
