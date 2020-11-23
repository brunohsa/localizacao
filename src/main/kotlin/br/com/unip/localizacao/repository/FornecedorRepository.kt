package br.com.unip.localizacao.repository

import br.com.unip.localizacao.dto.CoordenadasDTO
import br.com.unip.localizacao.dto.FiltroFornecedorDTO
import br.com.unip.localizacao.dto.FornecedorEncontradoDTO
import br.com.unip.localizacao.repository.entity.mysql.ECategoria
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


@Repository
class FornecedorRepository(val em: EntityManager) : IFornecedorRepository {

    @Value("\${distancia.maxima.busca}")
    private val DISTANCIA_MAXIMA_BUSCA: Double = 0.0

    override fun buscar(filtro: FiltroFornecedorDTO): List<FornecedorEncontradoDTO> {
        val parametros = HashMap<String, Any>()

        val distanciaSql = """
             (6371 * acos(
                 cos( radians(:lat) )
                 * cos( radians( e.latitude ) )
                 * cos( radians( e.longitude ) - radians(:long) )
                 + sin( radians(:lat) )
                 * sin( radians( e.latitude ) ) 
                 )
             )"""

        var sql = """
            SELECT new ${FornecedorEncontradoDTO::class.qualifiedName}(c.uuid, pj.nome, $distanciaSql)
            FROM PessoaJuridica pj
            JOIN pj.endereco e
            JOIN pj.cadastro c
            WHERE $distanciaSql < :distanciaMaximaBusca """

        if (filtro.nome != null) {
            sql += " AND pj.nome like :nome "
            parametros["nome"] = "%${filtro.nome}%"
        }
        if (filtro.notaApartirDe != null) {
            sql += " AND c.nota >= :notaApartirDe"
            parametros["notaApartirDe"] = filtro.notaApartirDe
        }
        if (filtro.notaMenorQue != null) {
            sql += " AND c.nota < :notaMenorQue"
            parametros["notaMenorQue"] = filtro.notaMenorQue
        }
        if (filtro.categoria != null) {
            sql += " AND c.categoria = :categoria"
            parametros["categoria"] = ECategoria.valueOf(filtro.categoria)
        }

        sql += if (filtro.campoOrdenacao != null && filtro.tipoOrdenacao != null) {
            " ORDER BY ${filtro.campoOrdenacao} ${filtro.tipoOrdenacao} "
        } else {
            " ORDER BY $distanciaSql asc "
        }

        val query = em.createQuery(sql, FornecedorEncontradoDTO::class.java)

        if (filtro.limite != null) {
            query.maxResults = filtro.limite
        }
        query.setParameter("lat", filtro.coordenadas.latitude)
        query.setParameter("long", filtro.coordenadas.longitude)
        query.setParameter("distanciaMaximaBusca", DISTANCIA_MAXIMA_BUSCA)
        parametros.map { (key, value) -> query.setParameter(key, value) }

        return query.resultList
    }

    override fun buscarCadastrosUUID(coordenadas: CoordenadasDTO): List<String> {
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
            SELECT c.uuid
            FROM PessoaJuridica pj
            JOIN pj.endereco e
            JOIN pj.cadastro c
            WHERE $distanciaSql < :distanciaMaximaBusca
            ORDER BY $distanciaSql asc"""

        val query = em.createQuery(sql, String::class.java)
        query.setParameter("lat", coordenadas.latitude)
        query.setParameter("long", coordenadas.longitude)
        query.setParameter("distanciaMaximaBusca", DISTANCIA_MAXIMA_BUSCA)

        return query.resultList
    }
}