package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.agenda.AgendamentoRecompensa;

import java.time.LocalDateTime;


public interface AgendamentoRecompensaRepository extends JpaRepository<AgendamentoRecompensa, Long> {

    boolean existsByBarbeiroIdAndDataHora(
            Long barbeiroId,
            LocalDateTime dataHora
    );
}
