package projetoBarbearia.WillBaber.domain.Login.dto;

import projetoBarbearia.WillBaber.domain.users.TipoUsers;

public record LoginBarbeiroResponseDTO(
        Long id,
        String nome,
        String email,
        TipoUsers tipoUsers
) {
}
