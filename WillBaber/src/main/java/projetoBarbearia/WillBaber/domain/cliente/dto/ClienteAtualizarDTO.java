package projetoBarbearia.WillBaber.domain.cliente.dto;

public record ClienteAtualizarDTO(
        String nomeCompleto,
        String email,
        String telefone,
        String senha
) {
}
