package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO
import br.com.unip.localizacao.dto.FuncionamentoDTO
import br.com.unip.localizacao.repository.IFornecedorRepository
import br.com.unip.localizacao.repository.IHorarioFuncionamentoRepository
import org.springframework.stereotype.Service

@Service
class FornecedorService(val fornecedorRepository: IFornecedorRepository,
                        val funcionamentoRepository: IHorarioFuncionamentoRepository) : IFornecedorService {

    override fun buscar(coordenadas: CoordenadasDTO): List<FornecedorEncontradoDTO> {
        var fornecedores = fornecedorRepository.buscar(coordenadas)
        fornecedores.forEach { f -> f.funcionamentoDTO = this.getHorarioDeFuncionamento(f.cadastroUUID) }

        return fornecedores
    }

    override fun buscarCadostrosUUID(coordenadas: CoordenadasDTO): List<String> {
        return fornecedorRepository.buscarCadastrosUUID(coordenadas)
    }

    private fun getHorarioDeFuncionamento(cadastroUUID: String): FuncionamentoDTO {
        return funcionamentoRepository.buscarFuncionamentoHoje(cadastroUUID)
    }
}