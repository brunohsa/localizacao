package br.com.unip.localizacao.repository

import br.com.unip.localizacao.repository.entity.mysql.HorarioDiferenciado
import br.com.unip.localizacao.repository.entity.mysql.HorarioFuncionamento
import br.com.unip.localizacao.dto.FuncionamentoDTO
import br.com.unip.localizacao.repository.entity.mysql.EDiaSemana
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import javax.persistence.EntityManager
import javax.persistence.NoResultException


@Repository
class HorarioFuncionamentoRepository(val em: EntityManager) : IHorarioFuncionamentoRepository {

    override fun buscarFuncionamentoHoje(cadastroUUID: String): FuncionamentoDTO {
        val hoje = LocalDate.now();

        val horarioDiferenciado = this.buscarHorarioDiferenciadoPorDia(cadastroUUID, hoje)
        if (horarioDiferenciado != null) {
            val aberto = this.getEstabelecimentoAberto(horarioDiferenciado.abertura!!, horarioDiferenciado.fechamento!!)
            return FuncionamentoDTO(horarioDiferenciado.abertura!!, horarioDiferenciado.fechamento!!, aberto)
        }
        val funcionamento = buscarHorarioFuncionamentoPorData(cadastroUUID, hoje)!!
        var aberto = !funcionamento.fechado
        if (aberto) {
            aberto = this.getEstabelecimentoAberto(funcionamento.abertura!!, funcionamento.fechamento!!)
        }
        return FuncionamentoDTO(funcionamento.abertura, funcionamento.fechamento, aberto)
    }

    private fun buscarHorarioDiferenciadoPorDia(cadastroUUID: String, data: LocalDate): HorarioDiferenciado? {
        val sql = """SELECT hd FROM ${HorarioDiferenciado::class.qualifiedName} hd
                     JOIN hd.cadastro c
                     WHERE c.uuid =:uuid
                     AND hd.dataEspecial =:dataEspecial"""

        val query = em.createQuery(sql)
        query.setParameter("uuid", cadastroUUID)
        query.setParameter("dataEspecial", data)

        return try {
            query.singleResult as HorarioDiferenciado
        } catch (e: NoResultException) {
            null
        }
    }

    private fun buscarHorarioFuncionamentoPorData(cadastroUUID: String, data: LocalDate): HorarioFuncionamento? {
        val diaDaSemanaIngles = data.dayOfWeek
        val diaSemana = EDiaSemana.getDiaDaSemanaIngles(diaDaSemanaIngles.name)

        val sql = """SELECT hf FROM ${HorarioFuncionamento::class.qualifiedName} hf
                     JOIN hf.cadastro c
                     WHERE c.uuid = :uuid
                     AND hf.diaSemana = :diaSemana"""

        val query = em.createQuery(sql)
        query.setParameter("uuid", cadastroUUID)
        query.setParameter("diaSemana", diaSemana)

        return try {
            query.singleResult as HorarioFuncionamento
        } catch (e: NoResultException) {
            null
        }
    }

    private fun getEstabelecimentoAberto(abertura: String, fechamento: String): Boolean {
        val agora = LocalTime.now()
        val horarioAbertura = converterHorario(abertura)
        val horarioFechamento = converterHorario(fechamento)

        return agora.isAfter(horarioAbertura) && agora.isBefore(horarioFechamento)
    }

    private fun converterHorario(horas: String): LocalTime {
        val format = DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("H:mm").toFormatter()
        try {
            return LocalTime.parse(horas, format)
        } catch (e: DateTimeParseException) {
            throw RuntimeException(e)
        }
    }
}