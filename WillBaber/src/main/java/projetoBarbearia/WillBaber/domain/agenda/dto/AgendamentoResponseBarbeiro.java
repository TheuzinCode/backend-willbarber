package projetoBarbearia.WillBaber.domain.agenda.dto;

import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AgendamentoResponseBarbeiro(
        Long id,
        String nomeCliente,
        String nomeServico,
        LocalTime duracao,
        LocalDateTime dataHora,
        BigDecimal preco,
        StatusAgendamento statusAgendamento

) {
}
