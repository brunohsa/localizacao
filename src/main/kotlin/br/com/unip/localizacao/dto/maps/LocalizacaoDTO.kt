package br.com.unip.localizacao.dto.maps

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class LocalizacaoDTO(@JsonProperty("lat") val latitude: Double,
                     @JsonProperty("lng") val longitude: Double)