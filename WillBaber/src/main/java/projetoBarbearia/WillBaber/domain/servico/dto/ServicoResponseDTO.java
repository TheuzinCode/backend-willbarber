package projetoBarbearia.WillBaber.domain.servico.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ServicoResponseDTO(
         Long id,
         String nomeServico,
         BigDecimal preco,
         String descricao,
         LocalTime tempoServico,
         Integer pontos

) {
}
