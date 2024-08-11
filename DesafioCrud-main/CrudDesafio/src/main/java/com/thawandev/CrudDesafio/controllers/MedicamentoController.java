package com.thawandev.CrudDesafio.controllers;

import com.thawandev.CrudDesafio.dto.MedicamentoDTO;
import com.thawandev.CrudDesafio.entities.Medicamentos;
import com.thawandev.CrudDesafio.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> findById(@PathVariable Long id) {
        MedicamentoDTO dto = medicamentoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MedicamentoDTO>> findAll(Pageable pageable) {
        Page<MedicamentoDTO> page = medicamentoService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<MedicamentoDTO> insert(@RequestBody MedicamentoDTO dto) {
        MedicamentoDTO newDto = medicamentoService.insert(dto);
        return ResponseEntity.status(201).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> update(@PathVariable Long id, @RequestBody MedicamentoDTO dto) {
        MedicamentoDTO updatedDto = medicamentoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @PatchMapping("/{id}/decrementar")
    public ResponseEntity<MedicamentoDTO> decrementarQuantidade(@PathVariable Long id, @RequestParam Integer quantity) {
        MedicamentoDTO updatedDto = medicamentoService.decrementarQuantidade(id, quantity);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicamentoDTO>> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String lote,
            @RequestParam(required = false) LocalDate validade) {
        List<MedicamentoDTO> result = medicamentoService.search(nome, lote, validade);
        return ResponseEntity.ok(result);
    }
}