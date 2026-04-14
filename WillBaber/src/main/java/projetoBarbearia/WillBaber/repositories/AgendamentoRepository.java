package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Long> {


    List<Agendamento> findByBarbeiroIdAndDataHoraBetween(
            Long barbeiroId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
