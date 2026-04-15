package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
