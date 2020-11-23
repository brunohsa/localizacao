package br.com.unip.localizacao.dto

class FornecedorEncontradoDTO(val cadastroUUID: String,
                              val razaoSocial: String,
                              val distancia: Double) {

    var funcionamentoDTO: FuncionamentoDTO? = null
}