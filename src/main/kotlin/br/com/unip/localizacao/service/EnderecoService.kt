package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.EnderecoDTO
import br.com.unip.localizacao.repository.IEnderecoRepository
import br.com.unip.localizacao.repository.IGoogleMapsRepository
import br.com.unip.localizacao.repository.entity.mongo.Endereco
import org.springframework.stereotype.Service

@Service
class EnderecoService(val enderecoRepositry: IEnderecoRepository,
                      val googleMapsRepository: IGoogleMapsRepository) : IEnderecoService {

    private val REGEX_NUMBER = "\\D".toRegex()

    override fun buscarPorCep(cep: String?): EnderecoDTO {
        val cepValidado = tratarEValidarCEP(cep)
        val endereco = enderecoRepositry.findByCep(cepValidado)
        if (endereco == null) {
            val enderecoDTO = googleMapsRepository.buscarEnderecoPorCep(cepValidado)
            val enderecoNovo = Endereco(enderecoDTO.cep,
                    enderecoDTO.bairro,
                    enderecoDTO.cidade,
                    enderecoDTO.estado,
                    enderecoDTO.latitude,
                    enderecoDTO.longitude)

            enderecoRepositry.save(enderecoNovo)
            return enderecoDTO
        }
        return map(endereco)
    }

    private fun tratarEValidarCEP(cep: String?): String {
        if (cep.isNullOrEmpty()) {
            throw RuntimeException("CEP invalido")
        }
        val cepFormatado = cep.replace(REGEX_NUMBER, "")
        if (cepFormatado.length < 8 || cepFormatado.length > 8) {
            throw RuntimeException("CEP invalido")
        }
        return cepFormatado
    }

    private fun map(endereco: Endereco): EnderecoDTO {
        return EnderecoDTO(endereco.cep,
                endereco.bairro,
                endereco.cidade,
                endereco.estado,
                endereco.latitude,
                endereco.longitude)
    }
}