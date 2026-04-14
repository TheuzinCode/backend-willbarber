package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.servico.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
