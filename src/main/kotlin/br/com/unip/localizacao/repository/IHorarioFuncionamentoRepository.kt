package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.FuncionamentoDTO

interface IHorarioFuncionamentoRepository {

    fun buscarFuncionamentoHoje(cadastroUUID : String) : FuncionamentoDTO
}