package projetoBarbearia.WillBaber.domain.recompensa.dto;

import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MInhasRecompensasDTO(
        Long id,
        String nomeServico,
        String nomeBarbeiro,
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento
) {
}
