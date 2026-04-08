package projetoBarbearia.WillBaber.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import projetoBarbearia.WillBaber.domain.users.Users;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByCpf(String cpf);

    List<Users> findByEmail(String email);
}
