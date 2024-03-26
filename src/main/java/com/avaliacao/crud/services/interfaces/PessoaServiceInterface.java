package com.avaliacao.crud.services.interfaces;

import com.avaliacao.crud.model.PessoaModel;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface PessoaServiceInterface {
        PessoaModel save(PessoaModel pessoaModel);
        Optional<PessoaModel> getById(Long id);
        List<PessoaModel> getAll();
        ResponseEntity<Object> update(Long id,PessoaModel pessoaModel);
        void delete(Long id);


}
