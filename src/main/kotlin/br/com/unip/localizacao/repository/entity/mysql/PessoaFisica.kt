package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.Entity

@Entity
class PessoaFisica(nome: String, endereco: Endereco?, cadastro: Cadastro) : Pessoa(nome, endereco, cadastro)