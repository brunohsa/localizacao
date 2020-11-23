package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.EnderecoDTO
import br.com.unip.localizacao.exception.ECodigoErro.CEP_INVALIDO
import br.com.unip.localizacao.exception.ParametroInvalidoException
import br.com.unip.localizacao.repository.ICoordenadasRepository
import br.com.unip.localizacao.repository.IEnderecoRepository
import br.com.unip.localizacao.repository.IGoogleMapsRepository
import br.com.unip.localizacao.repository.IViacepRepository
import br.com.unip.localizacao.repository.entity.mongo.Coordenadas
import br.com.unip.localizacao.repository.entity.mongo.Endereco
import org.springframework.stereotype.Service

@Service
class EnderecoService(val enderecoRepositry: IEnderecoRepository,
                      val coordenadasRepository: ICoordenadasRepository,
                      val googleMapsRepository: IGoogleMapsRepository,
                      val viacepRepository: IViacepRepository) : IEnderecoService {

    private val REGEX_NUMBER = "\\D".toRegex()

    override fun buscarCoordenadasPorCep(cep: String): CoordenadasDTO {
        val cepValidado = tratarEValidarCEP(cep)
        var coordenadas = coordenadasRepository.findByCep(cepValidado)
        if (coordenadas == null) {
            coordenadas = buscarCoordenadasGoogleMaps(cepValidado)
            coordenadasRepository.save(coordenadas)
        }

        return CoordenadasDTO(coordenadas.latitude, coordenadas.longitude)
    }

    private fun buscarCoordenadasGoogleMaps(cep: String): Coordenadas {
        val coordenadasDTO = googleMapsRepository.buscarCoordenadasPorCep(cep)
                ?: throw ParametroInvalidoException(CEP_INVALIDO)

        return Coordenadas(cep, coordenadasDTO.latitude, coordenadasDTO.longitude)
    }

    override fun buscarPorCep(cep: String): EnderecoDTO {
        val cepValidado = tratarEValidarCEP(cep)
        var endereco = enderecoRepositry.findByCep(cepValidado)
        if (endereco == null) {
            endereco = buscarEnderecoViacep(cepValidado)
            enderecoRepositry.save(endereco)
        }

        return EnderecoDTO(endereco.cep, endereco.logradouro, endereco.bairro, endereco.cidade, endereco.estado)
    }

    private fun buscarEnderecoViacep(cep: String): Endereco {
        val dto = viacepRepository.buscarEnderecoPorCEP(cep)
                ?: throw ParametroInvalidoException(CEP_INVALIDO)

        val cepFormatado = formatarCEP(cep)
        return Endereco(cepFormatado, dto.logradouro, dto.bairro, dto.cidade, dto.estado)
    }

    private fun tratarEValidarCEP(cep: String?): String {
        if (cep.isNullOrEmpty()) {
            throw ParametroInvalidoException(CEP_INVALIDO)
        }
        val cepFormatado = formatarCEP(cep)
        if (cepFormatado.length < 8 || cepFormatado.length > 8) {
            throw ParametroInvalidoException(CEP_INVALIDO)
        }
        return cepFormatado
    }

    private fun formatarCEP(cep: String): String {
        return cep.replace(REGEX_NUMBER, "")
    }
}