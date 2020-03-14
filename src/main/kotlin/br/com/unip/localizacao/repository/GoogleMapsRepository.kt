package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.maps.BuscaGoogleMapsDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class GoogleMapsRepository(val restRepository: IRestRepository) : IGoogleMapsRepository {

    @Value("\${url.maps.geocode}")
    private val URL_BASE_MAPS_GEOCODE: String = ""

    @Value("\${api.key.maps}")
    private val API_KEY_MAPS: String = ""

    override fun buscarCoordenadasPorCep(cep: String): CoordenadasDTO? {
        val parametros = mutableMapOf<String, String>()
        parametros["address"] = cep
        parametros["key"] = API_KEY_MAPS

        val busca = restRepository.get(URL_BASE_MAPS_GEOCODE, BuscaGoogleMapsDTO::class, parametros)
        if (busca.resultados.isEmpty()) {
            return null
        }
        val resultado = busca.resultados[0]

        val localizacao = resultado.geometriaDTO.localizacao
        return CoordenadasDTO(localizacao.latitude, localizacao.longitude)
    }
}