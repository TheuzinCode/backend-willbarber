package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Long> {


    List<Agendamento> findByBarbeiroIdAndDataHoraBetween(
            Long barbeiroId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    boolean existsByBarbeiroIdAndDataHora(Long id, LocalDateTime dataHora);

    List<Agendamento> findByClienteId(Long clienteId);

    List<Agendamento> findByClienteIdAndStatus(Long clienteId, StatusAgendamento status);

    List<Agendamento> findAllByOrderByIdDesc();

    List<Agendamento>
    findAllByOrderByDataHoraDesc();

    List<Agendamento>
    findByClienteIdOrderByDataHoraDesc(
            Long clienteId
    );

    List<Agendamento>
    findByBarbeiroIdAndDataHoraBetweenOrderByDataHoraDesc(
            Long clienteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

}
