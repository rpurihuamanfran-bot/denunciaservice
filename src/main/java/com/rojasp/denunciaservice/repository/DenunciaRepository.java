package com.rojasp.denunciaservice.repository;
import com.rojasp.denunciaservice.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    List<Denuncia> findByDni(String dni);
}
