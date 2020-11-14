package br.com.unip.localizacao.repository.entity.mysql

enum class EDiaSemana {

    SEGUNDA("MONDAY"),
    TERCA("TUESDAY"),
    QUARTA("WEDNESDAY"),
    QUINTA("THURSDAY"),
    SEXTA("FRIDAY"),
    SABADO("SATURDAY"),
    DOMINGO("SUNDAY");

    var diaIngles: String = ""

    constructor(diaIngles: String) {
        this.diaIngles = diaIngles
    }

    companion object {
        fun getDiaSemana(diaSemana: String): EDiaSemana {
            return values().firstOrNull { ds -> ds.name == diaSemana } ?: throw RuntimeException()
        }

        fun getDiaDaSemanaIngles(diaSemanaIngles: String): EDiaSemana {
            return values().firstOrNull { ds -> ds.diaIngles == diaSemanaIngles }
                    ?: throw RuntimeException()
        }
    }
}