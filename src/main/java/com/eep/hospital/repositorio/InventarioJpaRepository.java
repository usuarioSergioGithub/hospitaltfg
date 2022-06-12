package com.eep.hospital.repositorio;



import com.eep.hospital.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("inventarioJpaRepository")
public interface InventarioJpaRepository extends JpaRepository<Inventario, Serializable> {

}
