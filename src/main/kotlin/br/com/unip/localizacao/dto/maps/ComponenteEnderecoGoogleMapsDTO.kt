package br.com.unip.localizacao.dto.maps

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ComponenteEnderecoGoogleMapsDTO(@JsonProperty("long_name") val nome: String,
                                      @JsonProperty("types") val tipos: List<String>)