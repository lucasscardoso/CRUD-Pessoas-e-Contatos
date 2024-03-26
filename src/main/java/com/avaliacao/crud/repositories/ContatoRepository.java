package com.avaliacao.crud.repositories;

import com.avaliacao.crud.model.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

    List<ContatoModel> findAllByPessoaId(Long pessoa_id);


}
