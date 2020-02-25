package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var nome: String?

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "endereco_id")
    var endereco: Endereco?

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cadastro_id")
    val cadastro: Cadastro

    constructor(nome: String?, endereco: Endereco?, cadastro: Cadastro) {
        this.nome = nome
        this.endereco = endereco
        this.cadastro = cadastro
    }
}