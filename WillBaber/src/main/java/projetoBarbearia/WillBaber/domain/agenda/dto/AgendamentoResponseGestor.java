package projetoBarbearia.WillBaber.domain.agenda.dto;

import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponseGestor(
        Long id,
        String nomeCliente,
        String nomeBarbeiro,
        BigDecimal precoServico,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento,
        Integer pontos
) {
}
