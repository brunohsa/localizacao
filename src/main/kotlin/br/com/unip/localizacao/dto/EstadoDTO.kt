package br.com.unip.localizacao.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EstadoDTO(@JsonProperty("id") val id: String,
                @JsonProperty("nome") val nome: String,
                @JsonProperty("sigla") val sigla: String)