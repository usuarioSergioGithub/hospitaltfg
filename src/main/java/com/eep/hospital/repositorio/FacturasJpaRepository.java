package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("facturasJpaRepository")
public interface FacturasJpaRepository extends JpaRepository<Facturas, Serializable> {

    List<Facturas> findByPagada(boolean pagada);

}