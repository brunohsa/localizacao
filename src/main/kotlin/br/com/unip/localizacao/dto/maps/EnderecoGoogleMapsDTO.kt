package br.com.unip.localizacao.dto.maps

import com.fasterxml.jackson.annotation.JsonProperty


class EnderecoGoogleMapsDTO(@JsonProperty("address_components") val componentesEndereco: List<ComponenteEnderecoGoogleMapsDTO>,
                            @JsonProperty("geometry") val geometriaDTO: GeometriaDTO)