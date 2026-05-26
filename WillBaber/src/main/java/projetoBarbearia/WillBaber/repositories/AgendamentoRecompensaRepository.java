package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.agenda.AgendamentoRecompensa;

import java.time.LocalDateTime;
import java.util.List;


public interface AgendamentoRecompensaRepository extends JpaRepository<AgendamentoRecompensa, Long> {

    boolean existsByBarbeiroIdAndDataHora(
            Long barbeiroId,
            LocalDateTime dataHora
    );

    List<AgendamentoRecompensa> findByBarbeiroIdAndDataHoraBetween(
            Long barbeiroId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
