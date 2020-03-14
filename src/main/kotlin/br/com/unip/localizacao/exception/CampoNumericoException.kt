package br.com.unip.localizacao.exception

import org.springframework.http.HttpStatus

class CampoNumericoException : LocalizacaoBaseException {

    constructor() : this(ECodigoErro.CAMPO_DEVE_SER_NUMERICO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST)
}