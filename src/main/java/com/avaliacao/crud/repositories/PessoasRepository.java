package com.avaliacao.crud.repositories;

import com.avaliacao.crud.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<PessoaModel,Long> {

}


