package com.thawandev.CrudDesafio.service;

import com.thawandev.CrudDesafio.dto.MedicamentoDTO;
import com.thawandev.CrudDesafio.entities.Medicamentos;
import com.thawandev.CrudDesafio.repositories.MedicamentoRepository;
import com.thawandev.CrudDesafio.service.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository repository;

    @Transactional(readOnly = true)
    public MedicamentoDTO findById(Long id) {
        Medicamentos medicamento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));
        return new MedicamentoDTO(medicamento);
    }

    @Transactional(readOnly = true)
    public Page<MedicamentoDTO> findAll(Pageable pageable) {
        Page<Medicamentos> page = repository.findAll(pageable);
        return page.map(MedicamentoDTO::new);
    }

    @Transactional
    public MedicamentoDTO insert(MedicamentoDTO dto) {
        Medicamentos medicamento = new Medicamentos();
        copyDtoToEntity(dto, medicamento);
        medicamento = repository.save(medicamento);
        return new MedicamentoDTO(medicamento);
    }

    @Transactional
    public MedicamentoDTO update(Long id, MedicamentoDTO dto) {
        Medicamentos medicamento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));
        copyDtoToEntity(dto, medicamento);
        medicamento = repository.save(medicamento);
        return new MedicamentoDTO(medicamento);
    }

    @Transactional
    public void delete(Long id) {
        Medicamentos medicamento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));
        repository.delete(medicamento);
    }

    @Transactional
    public MedicamentoDTO decrementarQuantidade(Long medicamentoId, Integer quantidade) {
        Medicamentos medicamento = repository.findById(medicamentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));

        if (medicamento.getQuantidadeTotal() < quantidade) {
            throw new RuntimeException("Quantidade insuficiente no estoque");
        }

        medicamento.setSaidaEstoque(medicamento.getSaidaEstoque() + quantidade);
        medicamento.setQuantidadeTotal(medicamento.getQuantidadeTotal() - quantidade);

        medicamento = repository.save(medicamento);
        return new MedicamentoDTO(medicamento);
    }

    @Transactional(readOnly = true)
    public List<MedicamentoDTO> search(String nome, String lote, LocalDate validade) {
        List<Medicamentos> medicamentos = repository.search(nome, lote, validade);
        return medicamentos.stream().map(MedicamentoDTO::new).toList();
    }

    private void copyDtoToEntity(MedicamentoDTO dto, Medicamentos medicamento) {
        medicamento.setNome(dto.getNome());
        medicamento.setLote(dto.getLote());
        medicamento.setValidade(dto.getValidade());
        medicamento.setQuantidadeTotal(dto.getQuantidadeTotal());
        if (dto.getSaidaEstoque() != null) {
            medicamento.setSaidaEstoque(dto.getSaidaEstoque());
        }
    }
}