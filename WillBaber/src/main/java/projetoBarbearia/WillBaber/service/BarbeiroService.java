package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseDTO;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseGestorDTO;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.dto.HorarioTrabalhoDTO;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.HorarioTabalhoRepository;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                        barbeiro.getSenha(),
                        barbeiro.getCpf(),
                        barbeiro.getEmail(),
                        barbeiro.getImagem(),
                        barbeiro.getHorarios()
                                .stream()
                                .map(HorarioTrabalhoDTO::new)
                                .toList()
                )).toList();
    }

    public BarbeiroResponseGestorDTO editarBarbeiro(Long id,
                                                    BarbeiroResponseGestorDTO barbeiroResponseGestorDTO,
                                                    MultipartFile imagem)throws IOException {

        var barbeiroOpt = barbeiroRepository.findById(id).orElseThrow(() -> new BusinessException("ERRO AO ENCONTRAR BARBBEIRO"));


        Barbeiro barbeiro = barbeiroOpt;

        barbeiro.setNome(barbeiroResponseGestorDTO.nome());
        barbeiro.setDescricao(barbeiroResponseGestorDTO.descricao());
        barbeiro.setSenha(barbeiroResponseGestorDTO.senha());
        if (imagem != null && !imagem.isEmpty()) {
            barbeiro.setImagem(imagem.getBytes());
        }
        barbeiro.setEmail(barbeiroResponseGestorDTO.email());
        barbeiro.setNumero(barbeiroResponseGestorDTO.telefone());
        List<HorarioTrabalho> horarios = barbeiroResponseGestorDTO.horarios()
                .stream()
                .map(dto -> {

                    HorarioTrabalho horario = new HorarioTrabalho();

                    horario.setDiaSemana(dto.diaSemana());
                    horario.setHorarioInicio(dto.horaInicio());
                    horario.setHorarioFim(dto.horaFim());

                    horario.setBarbeiro(barbeiro);

                    return horario;
                })
                .collect(Collectors.toList());

        barbeiro.getHorarios().clear();

        barbeiro.getHorarios().addAll(horarios);

        barbeiroRepository.save(barbeiro);

        return new BarbeiroResponseGestorDTO(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getDescricao(),
                barbeiro.getNumero(),
                barbeiro.getSenha(),
                barbeiro.getCpf(),
                barbeiro.getEmail(),
                barbeiro.getImagem(),
                barbeiro.getHorarios()
                        .stream()
                        .map(HorarioTrabalhoDTO::new)
                        .toList()

        );
    }

}
