package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginRequestDTO;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginResponseDTO;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LoginService {

    private UsuarioRepository usuarioRepository;

    private ClienteRepository clienteRepository;

    public LoginResponseDTO verificarLogin(LoginRequestDTO loginDTO){
        Users optLogin = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new BusinessException("EMAIL NÃO ENCONTRADO") );

        var cliente = clienteRepository.findById(optLogin.getId()).orElseThrow(() -> new BusinessException("CLIENTE NÃO ENCONTRADO"));


        if (!optLogin.getSenha().equals(loginDTO.senha())){
            throw new BusinessException("USUARIO OU SENHA INCORRETA");
        }

        LoginResponseDTO  loginResponseDTO = new LoginResponseDTO(optLogin.getId(), optLogin.getNome(), optLogin.getEmail(), cliente.getPontos());

        return  loginResponseDTO;
    }


}
