package projetoBarbearia.WillBaber.domain.recompensa.dto;

public record NovaRecompensaResponseDTO(
        Long id,
        String nomeRecompensa,
        String descricao,
        Integer pontos
) {
}
