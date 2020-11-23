package br.com.unip.localizacao.repository.entity.mysql

import java.time.LocalDate
import javax.persistence.*

@Entity
open class HorarioDiferenciado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var dataCadastro: LocalDate = LocalDate.now()

    @Column
    var dataEspecial: LocalDate

    @Column
    var abertura: String?

    @Column
    var fechamento: String?

    @ManyToOne
    var cadastro: Cadastro

    constructor(dataEspecial: LocalDate, abertura: String?, fechamento: String?, cadastro: Cadastro) {
        this.dataEspecial = dataEspecial
        this.abertura = abertura
        this.fechamento = fechamento
        this.cadastro = cadastro
    }
}