package br.com.unip.localizacao.webservice.model.response

import br.com.unip.localizacao.exception.ECodigoErro
import com.fasterxml.jackson.annotation.JsonProperty

class Erro(@JsonProperty("codigo") val codigoErro: ECodigoErro,
           @JsonProperty("mensagem") val mensagem: String)