package com.FCarrena.demoparkapi.repository;

import com.FCarrena.demoparkapi.entity.Cliente;
import com.FCarrena.demoparkapi.repository.projection.ClienteProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;





public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    @Query("select c from Cliente c")
    Page<ClienteProjection> findAllPageable(Pageable pageable);

    Cliente findByUsuarioId(Long id);

}
