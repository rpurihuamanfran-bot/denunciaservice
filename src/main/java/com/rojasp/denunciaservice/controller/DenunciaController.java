package com.rojasp.denunciaservice.controller;
import com.rojasp.denunciaservice.entity.Denuncia;
import com.rojasp.denunciaservice.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/denuncias")
@CrossOrigin(origins = "*")

public class DenunciaController {
    @Autowired
    private DenunciaService service;

    // 1. Registrar denuncia (POST /denuncias/)
    @PostMapping("/")
    public ResponseEntity<Denuncia> registrar(@RequestBody Denuncia denuncia) {
        Denuncia creada = service.registrar(denuncia);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // 2. Obtener denuncias paginadas (GET /denuncias/?offset=0&limit=5)
    @GetMapping("/")
    public ResponseEntity<List<Denuncia>> obtenerPaginadas(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        List<Denuncia> lista = service.obtenerPaginado(offset, limit);
        return ResponseEntity.ok(lista);
    }

    // 3. Obtener denuncia por id (GET /denuncias/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerPorId(@PathVariable Long id) {
        Optional<Denuncia> opt = service.obtenerPorId(id);
        return opt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. Obtener denuncias de un ciudadano por DNI (GET /denuncias/ciudadano/{dni})
    @GetMapping("/ciudadano/{dni}")
    public ResponseEntity<List<Denuncia>> obtenerPorDni(@PathVariable String dni) {
        List<Denuncia> lista = service.obtenerPorDni(dni);
        return ResponseEntity.ok(lista);
    }

    // 5. Atender denuncia (PUT /denuncias/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atender(@PathVariable Long id) {
        Denuncia atendida = service.atenderDenuncia(id);
        if (atendida != null) {
            return ResponseEntity.ok(atendida);
        }
        return ResponseEntity.notFound().build();
    }
}
