package br.com.unip.localizacao.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CoordenadasResponse(@JsonProperty("latitude") val latitude: Double,
                          @JsonProperty("longitude") val longitude: Double)