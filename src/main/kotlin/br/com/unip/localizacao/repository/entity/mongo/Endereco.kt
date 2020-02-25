package br.com.unip.localizacao.repository.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "endereco")
class Endereco {

    @Id
    var id: String? = null

    var cep: String

    var bairro: String

    var cidade: String

    var estado: String

    var latitude: Double

    var longitude: Double

    constructor(cep: String, bairro: String, cidade: String, estado: String, latitude: Double, longitude: Double) {
        this.cep = cep
        this.bairro = bairro
        this.cidade = cidade
        this.estado = estado
        this.latitude = latitude
        this.longitude = longitude
    }
}