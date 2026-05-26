package projetoBarbearia.WillBaber.domain.agenda.agRecomDTO;

import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoRecompensaResponseDTO(
        Long id,
        String nomeServico,
        String nomeBarbeiro,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento
) {
}
