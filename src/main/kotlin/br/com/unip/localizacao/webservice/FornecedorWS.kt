package br.com.unip.localizacao.webservice

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FiltroFornecedorDTO
import br.com.unip.localizacao.dto.FuncionamentoDTO
import br.com.unip.localizacao.service.IFornecedorService
import br.com.unip.localizacao.webservice.model.response.FornecedorEncontradoResponse
import br.com.unip.localizacao.webservice.model.response.FuncionamentoResponse
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/fornecedores"])
class FornecedorWS(val fornecedorService: IFornecedorService) {

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["latitude/{lat}/longitude/{long}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun buscar(@PathVariable("lat") lat: Double,
               @PathVariable("long") long: Double,
               @ApiParam(required = false) @RequestParam(value = "nota_apartir_de") notaApartirDe: Int?,
               @ApiParam(required = false) @RequestParam(value = "nota_menor_que") notaMenorQue: Int?,
               @ApiParam(required = false) @RequestParam(value = "nome") nome: String?,
               @ApiParam(required = false) @RequestParam(value = "categoria") categoria: String?,
               @ApiParam(required = false) @RequestParam(value = "tipo_ordenacao") tipoOrdenacao: String?,
               @ApiParam(required = false) @RequestParam(value = "campo_ordenacao") campoOrdenacao: String?,
               @ApiParam(required = false) @RequestParam(value = "limite") limite: Int?): ResponseEntity<List<FornecedorEncontradoResponse>> {
        val coordenadas = CoordenadasDTO(lat, long)
        val filtro = FiltroFornecedorDTO(notaApartirDe, notaMenorQue, nome, categoria, tipoOrdenacao, campoOrdenacao, limite, coordenadas)
        val forns = fornecedorService.buscar(filtro)
        val response = forns
                .map { f -> FornecedorEncontradoResponse(f.cadastroUUID, f.razaoSocial, f.distancia, f.funcionamentoDTO?.toResponse()) }
                .toList()
        return ResponseEntity.ok(response)
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["latitude/{lat}/longitude/{long}/cadastros-uuid"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun buscarCadostrosUUID(@PathVariable("lat") lat: Double,
                            @PathVariable("long") long: Double): ResponseEntity<List<String>> {
        val coordenadas = CoordenadasDTO(lat, long)
        val forns = fornecedorService.buscarCadostrosUUID(coordenadas)

        return ResponseEntity.ok(forns)
    }

    private fun FuncionamentoDTO.toResponse() = FuncionamentoResponse(this.abertura, this.fechamento, this.aberto)
}