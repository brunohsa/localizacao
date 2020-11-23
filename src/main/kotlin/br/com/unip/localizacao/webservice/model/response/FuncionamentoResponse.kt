package br.com.unip.localizacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class FuncionamentoResponse(@JsonProperty("abertura") val abertura: String?,
                            @JsonProperty("fechamento") val fechamento: String?,
                            @JsonProperty("aberto") val aberto: Boolean)