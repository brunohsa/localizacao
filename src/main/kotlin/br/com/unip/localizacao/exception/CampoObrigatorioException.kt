package br.com.unip.localizacao.exception

import br.com.unip.localizacao.exception.ECodigoErro.CAMPO_OBRIGATORIO
import org.springframework.http.HttpStatus.BAD_REQUEST

class CampoObrigatorioException : LocalizacaoBaseException {

    constructor() : this(CAMPO_OBRIGATORIO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}