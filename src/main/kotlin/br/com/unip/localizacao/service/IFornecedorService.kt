package br.com.unip.localizacao.service

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO

interface IFornecedorService {

    fun buscar(coordenadas: CoordenadasDTO) : List<FornecedorEncontradoDTO>
}