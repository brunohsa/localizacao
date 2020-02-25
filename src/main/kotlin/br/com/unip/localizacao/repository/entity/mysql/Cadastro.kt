package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    val uuid : String

    constructor(uuid: String) {
        this.uuid = uuid
    }
}