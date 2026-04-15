package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

}
