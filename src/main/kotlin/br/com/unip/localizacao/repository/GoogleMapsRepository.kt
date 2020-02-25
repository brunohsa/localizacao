package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.ETiposEnderecoGoogleMaps.BAIRRO
import br.com.unip.localizacao.dto.ETiposEnderecoGoogleMaps.CIDADE
import br.com.unip.localizacao.dto.ETiposEnderecoGoogleMaps.ESTADO
import br.com.unip.localizacao.dto.EnderecoDTO
import br.com.unip.localizacao.dto.maps.ComponenteEnderecoGoogleMapsDTO
import br.com.unip.localizacao.dto.maps.ResultadoGoogleMapsDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class GoogleMapsRepository(val restRepository: IRestRepository) : IGoogleMapsRepository {


    @Value("\${url.maps.geocode}")
    private val URL_BASE_MAPS_GEOCODE: String = ""

    @Value("\${api.key.maps}")
    private val API_KEY_MAPS: String = ""

    override fun buscarEnderecoPorCep(cep: String): EnderecoDTO {
        val parametros = mutableMapOf<String, String>()
        parametros["address"] = cep
        parametros["key"] = API_KEY_MAPS

        val result = restRepository.get(URL_BASE_MAPS_GEOCODE, ResultadoGoogleMapsDTO::class, parametros)
        val endereco = result.enderecos[0]

        val componentes = endereco.componentesEndereco
        val localizacao = endereco.geometriaDTO.localizacao

        return EnderecoDTO(cep,
                getBairro(componentes),
                getCidade(componentes),
                getEstado(componentes),
                localizacao.latitude,
                localizacao.longitude)
    }

    private fun getCidade(componentes: List<ComponenteEnderecoGoogleMapsDTO>): String {
        return componentes.first { c -> c.tipos.contains(CIDADE.valor) }.nome
    }

    private fun getEstado(componentes: List<ComponenteEnderecoGoogleMapsDTO>): String {
        return componentes.first { c -> c.tipos.contains(ESTADO.valor) }.nome
    }

    private fun getBairro(componentes: List<ComponenteEnderecoGoogleMapsDTO>): String {
        return componentes.first { c -> c.tipos.contains(BAIRRO.valor) }.nome
    }
}