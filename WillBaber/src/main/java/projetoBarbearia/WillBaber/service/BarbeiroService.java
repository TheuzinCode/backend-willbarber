package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseDTO;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<LocalDateTime> listarHorariosDisponiveis(Long barbeiroId, LocalDate data) {


        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(23, 59);

        List<Agendamento> agendamentos = agendamentoRepository
                .findByBarbeiroIdAndDataHoraBetween(barbeiroId, inicioDia, fimDia);

        List<LocalDateTime> ocupados = agendamentos.stream()
                .map(Agendamento::getDataHora)
                .toList();


        LocalDateTime horario = data.atTime(9, 0);
        LocalDateTime fimExpediente = data.atTime(18, 0);

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
                         barbeiro.getNome()
                 ))
                 .toList();
    }
}
