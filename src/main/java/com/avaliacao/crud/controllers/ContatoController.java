package com.avaliacao.crud.controllers;

import com.avaliacao.crud.model.ContatoModel;
import com.avaliacao.crud.model.PessoaModel;
import com.avaliacao.crud.repositories.ContatoRepository;
import com.avaliacao.crud.repositories.PessoasRepository;
import com.avaliacao.crud.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private PessoasRepository pessoasRepository;
    private ContatoRepository contatoRepository;

    private ContatoService contatoService;
    @Autowired
    public ContatoController(ContatoRepository contatoRepository, PessoasRepository pessoasRepository,ContatoService contatoService) {
        this.contatoRepository = contatoRepository;
        this.pessoasRepository = pessoasRepository;
        this.contatoService = contatoService;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContatoModel>> getContatoId(@PathVariable Long id) {
        Optional<ContatoModel> pessoaObject = contatoService.getById(id);
        if (pessoaObject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoaObject);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ContatoModel> updateContato(@PathVariable Long id, @RequestBody ContatoModel contatoModel) {
        return new ResponseEntity<>(contatoService.update(contatoModel,id),HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> contatoDelete(@PathVariable Long id){
        Optional<ContatoModel> contatoObject = contatoRepository.findById(id);
        if(contatoObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("contato nao encontrada");
        }
        //contatoRepository.deleteById(id);
        contatoService.contatoDelete(id) ;
        return ResponseEntity.status(HttpStatus.OK).body("o contato foi deletado");
    }

}
