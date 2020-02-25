package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO
import br.com.unip.localizacao.repository.IFornecedorRepository
import org.springframework.stereotype.Service

@Service
class FornecedorService(val fornecedorRepository: IFornecedorRepository) : IFornecedorService {

    override fun buscar(coordenadas: CoordenadasDTO): List<FornecedorEncontradoDTO> {
        return fornecedorRepository.buscar(coordenadas)
    }

}