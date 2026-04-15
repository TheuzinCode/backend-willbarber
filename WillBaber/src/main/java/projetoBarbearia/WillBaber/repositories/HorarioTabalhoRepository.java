package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;

import java.util.List;
import java.util.Optional;

public interface HorarioTabalhoRepository extends JpaRepository<HorarioTrabalho, Long> {

    Optional<List<HorarioTrabalho>> findByBarbeiro(Long aLong);
}
