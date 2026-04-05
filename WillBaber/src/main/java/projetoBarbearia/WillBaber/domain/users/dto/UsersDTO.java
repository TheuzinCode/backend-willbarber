package projetoBarbearia.WillBaber.domain.users.dto;

import projetoBarbearia.WillBaber.domain.horarioTrabalho.dto.HorarioTrabalhoDTO;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;

import java.util.List;

public record UsersDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        TipoUsers tipo,
        List<HorarioTrabalhoDTO> horarios

) {
}
