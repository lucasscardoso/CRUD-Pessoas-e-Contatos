package com.avaliacao.crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.crud.dtos.PessoaMalaDireta;
import com.avaliacao.crud.model.ContatoModel;
import com.avaliacao.crud.model.PessoaModel;
import com.avaliacao.crud.repositories.ContatoRepository;
import com.avaliacao.crud.repositories.PessoasRepository;
import com.avaliacao.crud.services.ContatoService;
import com.avaliacao.crud.services.PessoaService;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {


   private PessoasRepository pessoasRepository;

    private ContatoRepository contatoRepository;

    private ContatoService contatoService;
    private PessoaService pessoaService;


    @Autowired
    public PessoaController(ContatoRepository contatoRepository ,PessoasRepository pessoasRepository,ContatoService contatoService,PessoaService pessoaService){
        this.pessoasRepository = pessoasRepository;

        this.contatoRepository = contatoRepository;

        this.contatoService = contatoService;
        
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaModel> savePessoa(@RequestBody PessoaModel pessoaModel){

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoasRepository.save(pessoaModel));
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoasRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaId(@PathVariable Long id){
        Optional<PessoaModel> pessoaObject = pessoasRepository.findById(id);
        if(pessoaObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pessoa nao encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaObject.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable  Long id , @RequestBody PessoaModel pessoaModel){
    	ResponseEntity<Object> pessoaObject = pessoaService.update(id,pessoaModel);
        if(pessoaObject == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao encontrada");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable Long id){
        Optional<PessoaModel> pessoaObject = pessoasRepository.findById(id);
        if(pessoaObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao encontrada");
        }
        pessoasRepository.delete(pessoaObject.get());
        return ResponseEntity.status(HttpStatus.OK).body("a pessoa foi deletado");
    }
//coontato
    @PostMapping("/{id}/contatos")
    public ResponseEntity<ContatoModel> saveContato(@PathVariable Long id,@RequestBody ContatoModel contatoModel){

         ContatoModel contatoModels = contatoService.save(contatoModel, id);

       return ResponseEntity.status(HttpStatus.CREATED).body(contatoRepository.save(contatoModels));
    }

    //Lista de contatos

    @GetMapping("/{pessoa_id}/contatos")
    public List<ContatoModel> listarContatosDaPessoa(@PathVariable Long pessoa_id) {
        return contatoRepository.findAllByPessoaId(pessoa_id);
    }

    
    //mala direta
    
 //Mala direta
    
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<PessoaMalaDireta> getPessoaParaMalaDireta(@PathVariable Long id) {
        // Recupere a Pessoa com o ID especificado do serviço
        PessoaModel pessoaModel = pessoasRepository.findById(id).get();
        String formataEndereço= "";

        if (pessoaModel == null) {
            return ResponseEntity.notFound().build();
        }

       
        PessoaMalaDireta pessoaDTO = new PessoaMalaDireta(
            
        	pessoaModel.getId(),
            pessoaModel.getNome(),
            formataEndereço = "Logradouro: " + pessoaModel.getEndereco() + ", CEP: " + pessoaModel.getCep() + ",  Cidade: " + pessoaModel.getCidade() + " /  " + pessoaModel.getUf()
        );

        return ResponseEntity.ok(pessoaDTO);
    }
}
