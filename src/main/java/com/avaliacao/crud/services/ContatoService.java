package com.avaliacao.crud.services;

import com.avaliacao.crud.model.ContatoModel;
import com.avaliacao.crud.model.PessoaModel;
import com.avaliacao.crud.repositories.ContatoRepository;
import com.avaliacao.crud.repositories.PessoasRepository;
import com.avaliacao.crud.services.interfaces.ContatoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {


    private  PessoasRepository pessoaRepository;
    private  ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository ,PessoasRepository pessoaRepository ){
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }



    public ContatoModel save(ContatoModel contatoModel,Long pessoa_id) {
        Optional<PessoaModel> pessoaModelOptional= pessoaRepository.findById(pessoa_id);
        if(pessoaModelOptional.isPresent() && contatoModel.getTipoContato() == 0 ||contatoModel.getTipoContato() == 1 ){
            PessoaModel pessoaModel = pessoaModelOptional.get();
            contatoModel.setPessoa(pessoaModel);
            return contatoRepository.save(contatoModel);
        }
        
      return null ;
    }


    public Optional<ContatoModel> getById(Long id) {
        return contatoRepository.findById(id);
    }


    public List<ContatoModel> getAll(Long pessoa_id) {

        return contatoRepository.findAllByPessoaId(pessoa_id);


    }


    public ContatoModel update(ContatoModel contatoModel,Long id) {
        Optional<ContatoModel> contatoModelOptionalatt = contatoRepository.findById(id);
        if(contatoModelOptionalatt.isPresent()){
            ContatoModel contatoModelnew = contatoModelOptionalatt.get();
            contatoModelnew.setContato(contatoModel.getContato());
            contatoModelnew.setTipoContato(contatoModel.getTipoContato());
            return contatoRepository.save(contatoModelnew);
        }
        return contatoModel;
    }


    public void contatoDelete(Long id) {
    contatoRepository.deleteById(id);
    }
}
