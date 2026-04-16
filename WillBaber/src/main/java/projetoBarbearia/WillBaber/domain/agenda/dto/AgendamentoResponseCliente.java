package projetoBarbearia.WillBaber.domain.agenda.dto;

import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponseCliente(
        Long id,
        String nomeServico,
        String nomeBarbeiro,
        BigDecimal valorServico,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento

) {
}
