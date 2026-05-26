package projetoBarbearia.WillBaber.domain.cliente.dto;

public record RankingClienteDTO(
        Long clienteId,
        String nomeCliente,
        Long quantidadeCortes
) {
}
