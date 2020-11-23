package br.com.unip.localizacao.exception

import br.com.unip.localizacao.exception.ECodigoErro.PARAMETRO_INVALIDO
import org.springframework.http.HttpStatus.BAD_REQUEST

class ParametroInvalidoException : LocalizacaoBaseException {

    constructor() : this(PARAMETRO_INVALIDO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}