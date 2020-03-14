package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


@Repository
class FornecedorRepository(val em: EntityManager) : IFornecedorRepository {

    @Value("\${distancia.maxima.busca}")
    private val DISTANCIA_MAXIMA_BUSCA: Double = 0.0

    override fun buscar(coordenadas: CoordenadasDTO): List<FornecedorEncontradoDTO> {
        val distanciaSql = """
             (6371 * acos(
                 cos( radians(:lat) )
                 * cos( radians( e.latitude ) )
                 * cos( radians( e.longitude ) - radians(:long) )
                 + sin( radians(:lat) )
                 * sin( radians( e.latitude ) ) 
                 )
             )"""

        val sql = """
            SELECT new ${FornecedorEncontradoDTO::class.qualifiedName}(c.uuid, pj.nome, $distanciaSql)
            FROM PessoaJuridica pj
            JOIN pj.endereco e
            JOIN pj.cadastro c
            WHERE $distanciaSql < :distanciaMaximaBusca
            ORDER BY $distanciaSql asc"""

        val query = em.createQuery(sql, FornecedorEncontradoDTO::class.java)
        query.setParameter("lat", coordenadas.latitude)
        query.setParameter("long", coordenadas.longitude)
        query.setParameter("distanciaMaximaBusca", DISTANCIA_MAXIMA_BUSCA)

        return query.resultList
    }
}