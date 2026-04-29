package projetoBarbearia.WillBaber.domain.cliente.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String Telfone,
        String CPF,
        String Senha

) {
}
