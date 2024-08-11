package com.thawandev.CrudDesafio.repositories;

import com.thawandev.CrudDesafio.entities.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamentos, Long> {

    @Query("SELECT m FROM Medicamentos m WHERE " +
            "(LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR :nome IS NULL) AND " +
            "(LOWER(m.lote) LIKE LOWER(CONCAT('%', :lote, '%')) OR :lote IS NULL) AND " +
            "(m.validade = :validade OR :validade IS NULL)")
    List<Medicamentos> search(@Param("nome") String nome,
                              @Param("lote") String lote,
                              @Param("validade") LocalDate validade);
}