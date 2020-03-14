package br.com.unip.localizacao.repository

import br.com.unip.localizacao.repository.entity.mongo.Coordenadas
import org.springframework.data.mongodb.repository.MongoRepository

interface ICoordenadasRepository : MongoRepository<Coordenadas, String> {

    fun findByCep(cep: String) : Coordenadas?
}