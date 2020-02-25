package br.com.unip.localizacao.repository

import br.com.unip.localizacao.repository.entity.mongo.Endereco
import org.springframework.data.mongodb.repository.MongoRepository

interface IEnderecoRepository : MongoRepository<Endereco, String> {

    fun findByCep(cep: String): Endereco?
}