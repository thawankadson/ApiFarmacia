package org.springframework.hateoas.server.mvc;

import com.thawandev.CrudDesafio.dto.MedicamentoDTO;
import com.thawandev.CrudDesafio.entities.Medicamentos;

public interface ModelAssembler<T, T1> {
    MedicamentoDTO toModel(Medicamentos entity);
}
