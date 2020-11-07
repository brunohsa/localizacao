package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.EstadoDTO
import br.com.unip.localizacao.repository.IEstadoRepository
import br.com.unip.localizacao.repository.entity.mongo.Estado
import org.springframework.stereotype.Service

@Service
class EstadoService(val estadoRepository: IEstadoRepository) : IEstadoService {

    override fun buscar(): List<EstadoDTO> {
        var estados = estadoRepository.findAll()
        return estados.toDTO()
    }

    private fun List<Estado>.toDTO() = this.map { e -> EstadoDTO(e.id!!, e.nome, e.sigla) }
}