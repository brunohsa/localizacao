package br.com.unip.localizacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EnderecoResponse(@JsonProperty("cep") val cep: String,
                       @JsonProperty("bairro") val bairro: String,
                       @JsonProperty("cidade") val cidade: String,
                       @JsonProperty("estado") val estado: String,
                       @JsonProperty("latitude") val latitude: Double,
                       @JsonProperty("longitude") val longitude: Double)