package br.com.unip.localizacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EnderecoDTO(@JsonProperty("cep") val cep: String,
                  @JsonProperty("logradouro") val logradouro: String,
                  @JsonProperty("bairro") val bairro: String,
                  @JsonProperty("localidade") val cidade: String,
                  @JsonProperty("uf") val estado: String)