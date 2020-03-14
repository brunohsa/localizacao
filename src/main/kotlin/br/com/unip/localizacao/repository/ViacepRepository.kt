package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.EnderecoDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


@Repository
class ViacepRepository(val restRepository: IRestRepository, val objectMapper: ObjectMapper) : IViacepRepository {

    @Value("\${url.viacep}")
    private val URL_VIACEP: String = ""

    private val TAG_ERRO = "erro"

    override fun buscarEnderecoPorCEP(cep: String): EnderecoDTO? {
        val url = String.format(URL_VIACEP, cep)
        val retorno = restRepository.get(url, String::class)

        if (temErro(retorno)) {
            return null
        }
        return objectMapper.readValue(retorno, EnderecoDTO::class.java)
    }

    private fun temErro(response: String): Boolean {
        val responseJson = objectMapper.readTree(response)
        return responseJson.get(TAG_ERRO) != null
    }
}