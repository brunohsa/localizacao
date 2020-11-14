package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FuncionamentoDTO
import br.com.unip.localizacao.service.IFornecedorService
import br.com.unip.localizacao.webservice.model.response.FornecedorEncontradoResponse
import br.com.unip.localizacao.webservice.model.response.FuncionamentoResponse
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/fornecedores"])
class FornecedorWS(val fornecedorService: IFornecedorService) {

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["latitude/{lat}/longitude/{long}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    //@PreAuthorize("hasAuthority('${BUSCAR_LOJAS_POR_PROXIMIDADE}')")
    fun buscar(@PathVariable("lat") lat: Double,
               @PathVariable("long") long: Double): ResponseEntity<List<FornecedorEncontradoResponse>> {
        val coordenadas = CoordenadasDTO(lat, long)
        val forns = fornecedorService.buscar(coordenadas)

        val response = forns
                .map { f -> FornecedorEncontradoResponse(f.cadastroUUID, f.razaoSocial, f.distancia, f.funcionamentoDTO?.toResponse()) }
                .toList()
        return ResponseEntity.ok(response)
    }

    private fun FuncionamentoDTO.toResponse() = FuncionamentoResponse(this.abertura, this.fechamento, this.aberto)
}