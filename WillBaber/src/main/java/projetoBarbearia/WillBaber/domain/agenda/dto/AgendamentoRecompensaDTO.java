package projetoBarbearia.WillBaber.domain.agenda.dto;

import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoRecompensaDTO(
        Long id,
        Long clienteId,
        String nomeServico,
        Long nomeBarbeiro,
        BigDecimal valorServico,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento,
        Integer pontosRecompensa
) {
}
