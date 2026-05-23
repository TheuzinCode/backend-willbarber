package projetoBarbearia.WillBaber.domain.gestor.dto;

import projetoBarbearia.WillBaber.domain.users.TipoUsers;

public record GestorResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        TipoUsers tipoUsers
) {
}
