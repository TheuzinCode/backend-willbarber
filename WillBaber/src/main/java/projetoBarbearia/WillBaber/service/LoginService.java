package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginRequestDTO;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LoginService {

    private UsuarioRepository usuarioRepository;

    public Users verificarLogin(LoginRequestDTO loginDTO){
        Users optLogin = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new BusinessException("EMAIL NÃO ENCONTRADO") );

        if (!optLogin.getSenha().equals(loginDTO.senha())){
            throw new BusinessException("USUARIO OU SENHA INCORRETA");
        }

        return optLogin;
    }


}
