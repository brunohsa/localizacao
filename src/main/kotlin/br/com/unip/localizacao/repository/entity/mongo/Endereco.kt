package br.com.unip.localizacao.repository.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "endereco")
class Endereco {

    @Id
    var id: String? = null

    var cep: String

    var logradouro: String

    var bairro: String

    var cidade: String

    var estado: String


    constructor(cep: String, logradouro: String, bairro: String, cidade: String, estado: String) {
        this.cep = cep
        this.logradouro = logradouro
        this.bairro = bairro
        this.cidade = cidade
        this.estado = estado
    }
}