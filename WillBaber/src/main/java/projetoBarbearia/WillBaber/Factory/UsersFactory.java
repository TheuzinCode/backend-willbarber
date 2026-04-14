package projetoBarbearia.WillBaber.Factory;

import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.gestor.Gestor;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;

import java.util.List;

public class UsersFactory {

    public static Users criarUsuario(UsersDTO dto){

        switch (dto.tipo()){
            case CLIENTE:
                return new Cliente(dto.nome(), dto.email(), dto.senha(), dto.cpf(), 0);

            case BARBEIRO:
                 Barbeiro barbeiro = new Barbeiro(dto.nome(),dto.email(), dto.senha(), dto.cpf());
                if (dto.horarios() != null) {
                    List<HorarioTrabalho> horarios = dto.horarios().stream()
                            .map(h -> {
                                HorarioTrabalho ht = new HorarioTrabalho();
                                ht.setDiaSemana(h.diaSemana());
                                ht.setHorarioInicio(h.horaInicio());
                                ht.setHorarioFim(h.horaFim());
                                ht.setBarbeiro(barbeiro);
                                return ht;
                            })
                            .toList();

                    barbeiro.setHorarios(horarios);
                }
                    return barbeiro;


            case GESTOR:
                return new Gestor(dto.nome(), dto.email(), dto.senha(), dto.cpf());

            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
