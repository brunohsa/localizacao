package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.security.Permissoes.BUSCAR_LOJAS_POR_PROXIMIDADE
import br.com.unip.localizacao.service.IFornecedorService
import br.com.unip.localizacao.webservice.model.response.FornecedorEncontradoResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/fornecedores"])
class FornecedorWS(val fornecedorService: IFornecedorService) {

    @GetMapping(value = ["latitude/{lat}/longitude/{long}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('${BUSCAR_LOJAS_POR_PROXIMIDADE}')")
    fun buscar(@PathVariable("lat") lat: Double,
               @PathVariable("long") long: Double): ResponseEntity<List<FornecedorEncontradoResponse>> {
        val coordenadas = CoordenadasDTO(lat, long)
        val forns = fornecedorService.buscar(coordenadas)

        val response = forns
                .map { f -> FornecedorEncontradoResponse(f.cadastroUUID, f.razaoSocial, f.distancia) }
                .toList()

        return ResponseEntity.ok(response)
    }
}