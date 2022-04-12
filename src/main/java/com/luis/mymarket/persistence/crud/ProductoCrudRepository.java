package com.luis.mymarket.persistence.crud;

import com.luis.mymarket.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Product, Long> {
    List<Product> findByIdCategoriaOrderByNombreAsc(long idCategoria);

    Optional<List<Product>> findByCantidadStockLessThanAndEstado(long cantidadStock, boolean estado);


}
