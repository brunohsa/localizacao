package br.com.unip.localizacao.dto.maps

import com.fasterxml.jackson.annotation.JsonProperty


class ResultadoBuscaGoogleMapsDTO(@JsonProperty("geometry") val geometriaDTO: GeometriaDTO)