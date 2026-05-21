package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.gestor.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long> {
}
