package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.*

@Entity
class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    val uuid: String

    @Column
    var nota: Double? = null

    @Column
    @Enumerated(EnumType.STRING)
    var categoria: ECategoria? = null

    constructor(uuid: String) {
        this.uuid = uuid
    }
}