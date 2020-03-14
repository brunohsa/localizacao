package br.com.unip.localizacao.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class LocalizacaoBaseException : RuntimeException {

    var codigoErro: ECodigoErro

    var httpStatus: HttpStatus

    constructor(codigoErro: ECodigoErro,
                httpStatus: HttpStatus) {
        this.codigoErro = codigoErro
        this.httpStatus = httpStatus
    }
}
