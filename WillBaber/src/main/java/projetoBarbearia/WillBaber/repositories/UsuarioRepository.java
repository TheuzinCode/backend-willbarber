package projetoBarbearia.WillBaber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.users.Users;

public interface UsuarioRepository extends JpaRepository<Users, Long> {
}
