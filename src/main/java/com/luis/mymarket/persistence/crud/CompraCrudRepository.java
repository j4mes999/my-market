package com.luis.mymarket.persistence.crud;

import com.luis.mymarket.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Long> {

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
