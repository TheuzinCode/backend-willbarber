package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.recompensa.Recompensa;

public interface RecompensasRepository extends JpaRepository<Recompensa, Long> {
}
