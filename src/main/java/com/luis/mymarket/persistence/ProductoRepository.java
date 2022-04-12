package com.luis.mymarket.persistence;

import com.luis.mymarket.domain.repository.ProductRepository;
import com.luis.mymarket.persistence.crud.ProductoCrudRepository;
import com.luis.mymarket.persistence.entity.Product;
import com.luis.mymarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<com.luis.mymarket.domain.Product> getAll(){
        List<Product> productos = (List<Product>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<com.luis.mymarket.domain.Product>> getByCategory(long categoryId) {
        List<Product> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<com.luis.mymarket.domain.Product>> getScarseProducts(long quantity) {
        Optional<List<Product>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<com.luis.mymarket.domain.Product> getProduct(long productId) {
        return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public com.luis.mymarket.domain.Product save(com.luis.mymarket.domain.Product product) {
        Product producto = mapper.toProduct(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(long productId){
        productoCrudRepository.deleteById(productId);
    }

}
