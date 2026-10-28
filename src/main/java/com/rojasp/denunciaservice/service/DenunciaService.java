package com.rojasp.denunciaservice.service;
import com.rojasp.denunciaservice.entity.Denuncia;
import com.rojasp.denunciaservice.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository repository;

    public Denuncia registrar(Denuncia denuncia) {
        denuncia.setEstado(1);
        return repository.save(denuncia);
    }

    public List<Denuncia> obtenerPaginado(int offset, int limit) {
        int pageNumber = (limit > 0) ? offset / limit : 0;
        Pageable pageable = PageRequest.of(pageNumber, limit);
        return repository.findAll(pageable).getContent();
    }

    public Optional<Denuncia> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Denuncia> obtenerPorDni(String dni) {
        return repository.findByDni(dni);
    }

    public Denuncia atenderDenuncia(Long id) {
        Optional<Denuncia> opt = repository.findById(id);
        if (opt.isPresent()) {
            Denuncia d = opt.get();
            d.setEstado(0);
            return repository.save(d);
        }
        return null;
    }
}
