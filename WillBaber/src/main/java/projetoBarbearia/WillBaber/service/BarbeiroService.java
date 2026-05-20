package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseDTO;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseGestorDTO;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.HorarioTabalhoRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BarbeiroService {

    @Autowired
    private HorarioTabalhoRepository horarioTabalhoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<LocalDateTime> listarHorariosDisponiveis(Long barbeiroId, LocalDate data) {

        DayOfWeek diaSemana = data.getDayOfWeek();

        HorarioTrabalho horarioTrabalho = horarioTabalhoRepository.findByBarbeiroIdAndDiaSemana(barbeiroId, diaSemana);

        if (horarioTrabalho == null) {
            return List.of();
        }


        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(23, 59);


        List<Agendamento> agendamentos = agendamentoRepository
                .findByBarbeiroIdAndDataHoraBetween(barbeiroId, inicioDia, fimDia);

        List<LocalDateTime> ocupados = agendamentos.stream()
                .map(Agendamento::getDataHora)
                .toList();


        LocalDateTime horario = data.atTime(horarioTrabalho.getHorarioInicio());
        LocalDateTime fimExpediente = data.atTime(horarioTrabalho.getHorarioFim());

        List<LocalDateTime> disponiveis = new ArrayList<>();

        while (horario.isBefore(fimExpediente)) {

            if (!ocupados.contains(horario)) {
                disponiveis.add(horario);
            }

            horario = horario.plusMinutes(30);
        }

        return disponiveis;
    }

    public List<BarbeiroResponseDTO> listarAll(){
         return barbeiroRepository.findAll().stream()
                 .map(barbeiro -> new BarbeiroResponseDTO(
                         barbeiro.getId(),
                         barbeiro.getNome(),
                         barbeiro.getDescricao(),
                         barbeiro.getImagem()
                 ))
                 .toList();
    }

    public List<BarbeiroResponseGestorDTO> listarTodosBarbeiros(){
        return barbeiroRepository.findAll().stream().map(
                barbeiro -> new BarbeiroResponseGestorDTO(
                        barbeiro.getId(),
                        barbeiro.getNome(),
                        barbeiro.getDescricao(),
                        barbeiro.getNumero(),
                        barbeiro.getCpf(),
                        barbeiro.getEmail(),
                        barbeiro.getImagem()
                )).toList();
    }

}
