package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.dto.EnderecoDTO
import br.com.unip.localizacao.service.IEnderecoService
import br.com.unip.localizacao.webservice.model.response.EnderecoResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/enderecos"])
class EnderecoWS(val enderecoService: IEnderecoService) {

    @GetMapping(value = ["/{cep}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun buscarPorCep(@PathVariable("cep") cep: String): ResponseEntity<EnderecoResponse> {
        val endereco = enderecoService.buscarPorCep(cep)
        return ResponseEntity.ok(this.map(endereco))
    }

    private fun map(enderecoDTO: EnderecoDTO): EnderecoResponse {
        return EnderecoResponse(enderecoDTO.cep,
                enderecoDTO.bairro,
                enderecoDTO.cidade,
                enderecoDTO.estado,
                enderecoDTO.latitude,
                enderecoDTO.longitude)
    }
}