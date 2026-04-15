package projetoBarbearia.WillBaber.domain.users.dto;

public record UsersResponseDTO(
        String nome,
        String email,
        String senha,
        String cpf
) {
}
