package br.com.unip.localizacao.repository.entity.mysql

import javax.persistence.*

@Entity
open class HorarioFuncionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var abertura: String? = null

    @Column
    var fechamento: String? = null

    @Column
    @Enumerated(EnumType.STRING)
    lateinit var diaSemana: EDiaSemana

    @ManyToOne
    lateinit var cadastro: Cadastro

    @Column
    var fechado: Boolean = true

    constructor()

    constructor(abertura: String?, fechamento: String?, diaSemana: EDiaSemana, cadastro: Cadastro, fechado: Boolean)
            : this(diaSemana, cadastro) {
        this.abertura = abertura
        this.fechamento = fechamento
        this.fechado = fechado
    }

    constructor(diaSemana: EDiaSemana, cadastro: Cadastro) {
        this.diaSemana = diaSemana
        this.cadastro = cadastro
    }
}