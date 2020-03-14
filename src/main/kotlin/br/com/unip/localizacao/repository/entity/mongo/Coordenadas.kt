package br.com.unip.localizacao.repository.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "coordenadas")
class Coordenadas {

    @Id
    var id: String? = null

    var cep: String

    val latitude: Double

    val longitude: Double

    constructor(cep: String, latitude: Double, longitude: Double) {
        this.cep = cep
        this.latitude = latitude
        this.longitude = longitude
    }
}