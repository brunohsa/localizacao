package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.dto.EstadoDTO
import br.com.unip.localizacao.service.IEstadoService
import br.com.unip.localizacao.webservice.model.response.EstadoResponse
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/estados"])
class EstadoWS(val estadoService: IEstadoService) {

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping
    //@PreAuthorize("hasAuthority('$BUSCAR_ENDERECO_POR_CEP')")
    fun buscarEstados(): ResponseEntity<List<EstadoResponse>> {
        val dto = estadoService.buscar()
        return ResponseEntity.ok(dto.toResponse())
    }

    private fun List<EstadoDTO>.toResponse() = this.map { e -> EstadoResponse(e.id, e.nome, e.sigla) }
}