package projetoBarbearia.WillBaber.domain.users.dto;

import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.dto.HorarioTrabalhoDTO;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;
import projetoBarbearia.WillBaber.domain.users.Users;

import java.util.List;

public record UsersDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        String numero,
        TipoUsers tipo,
        List<HorarioTrabalhoDTO> horarios

) {
    public static UsersDTO toDTO(Users users){
        List<HorarioTrabalhoDTO> horarioDTO = List.of();

        if(users instanceof Barbeiro barbeiro){
            horarioDTO = barbeiro.getHorarios()
                    .stream()
                    .map(HorarioTrabalhoDTO::new)
                    .toList();
        }
        return new UsersDTO(
                users.getNome(),
                users.getEmail(),
                users.getSenha(),
                users.getCpf(),
                users.getNumero(),
                users.getTipo(),
                horarioDTO
        );
    }
}
