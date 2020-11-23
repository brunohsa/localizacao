package br.com.unip.localizacao.repository.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "estado")
class Estado {

    @Id
    var id: String? = null

    var nome: String

    var sigla: String

    constructor(nome: String, sigla: String) {
        this.nome = nome
        this.sigla = sigla
    }
}