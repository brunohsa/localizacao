package br.com.unip.localizacao.repository

import br.com.unip.localizacao.repository.entity.mongo.Estado
import org.springframework.data.mongodb.repository.MongoRepository

interface IEstadoRepository : MongoRepository<Estado, String>