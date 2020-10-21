package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.security.Permissoes.BUSCAR_COORDENADAS
import br.com.unip.localizacao.security.Permissoes.BUSCAR_ENDERECO_POR_CEP
import br.com.unip.localizacao.service.IEnderecoService
import br.com.unip.localizacao.webservice.model.response.CoordenadasResponse
import br.com.unip.localizacao.webservice.model.response.EnderecoResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/enderecos"])
class EnderecoWS(val enderecoService: IEnderecoService) {

    @GetMapping(value = ["/{cep}/coordenadas"])
    //@PreAuthorize("hasAuthority('$BUSCAR_COORDENADAS')")
    fun buscarCoordenadasPorCep(@PathVariable("cep") cep: String): ResponseEntity<CoordenadasResponse> {
        val dto = enderecoService.buscarCoordenadasPorCep(cep)
        return ResponseEntity.ok(CoordenadasResponse(dto.latitude, dto.longitude))
    }

    @GetMapping(value = ["/{cep}"])
    //@PreAuthorize("hasAuthority('$BUSCAR_ENDERECO_POR_CEP')")
    fun buscarPorCep(@PathVariable("cep") cep: String): ResponseEntity<EnderecoResponse> {
        val dto = enderecoService.buscarPorCep(cep)
        val resp = EnderecoResponse(dto.cep, dto.logradouro, dto.bairro, dto.cidade, dto.estado)
        return ResponseEntity.ok(resp)
    }
}