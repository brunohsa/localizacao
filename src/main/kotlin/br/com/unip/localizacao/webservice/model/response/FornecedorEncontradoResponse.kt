package br.com.unip.localizacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class FornecedorEncontradoResponse(@JsonProperty("cadastro_uuid") val cadastroUUID: String,
                                   @JsonProperty("razao_social") val razaoSocial: String,
                                   @JsonProperty("distancia") val distancia: Double)