package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginRequestDTO;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginResponseDTO;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;
import projetoBarbearia.WillBaber.repositories.GestorRepository;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LoginService {

    private UsuarioRepository usuarioRepository;

    private ClienteRepository clienteRepository;

    private BarbeiroRepository barbeiroRepository;

    private GestorRepository gestorRepository;

    private PasswordEncoder passwordEncoder;



    public LoginResponseDTO verificarLogin(LoginRequestDTO loginDTO){
        Users optLogin = usuarioRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new BusinessException("EMAIL NÃO ENCONTRADO") );

        boolean valid = passwordEncoder.matches(
                loginDTO.senha(),
                optLogin.getSenha());

        if (!valid){
            throw new BusinessException(
                    "USUARIO OU SENHA INCORRETA"
            );
        }

        if (optLogin.getTipo() == TipoUsers.CLIENTE){
            var cliente = clienteRepository.findById(optLogin.getId()).orElseThrow(() -> new BusinessException("CLIENTE NÃO ENCONTRADO"));
            LoginResponseDTO  loginResponseDTO = new LoginResponseDTO(optLogin.getId(), optLogin.getNome(), optLogin.getEmail(), cliente.getPontos(), cliente.getTipo());
            return loginResponseDTO;
        }

        if (optLogin.getTipo()== TipoUsers.BARBEIRO){
            var barbeiro = barbeiroRepository.findById(optLogin.getId()).orElseThrow(() -> new BusinessException("BARBEIRO NÃO ENCONTRADO"));
            LoginResponseDTO  loginResponseDTO = new LoginResponseDTO(optLogin.getId(), optLogin.getNome(), optLogin.getEmail(), 0, barbeiro.getTipo());
            return loginResponseDTO;
        }

        if (optLogin.getTipo() == TipoUsers.GESTOR){
            var gestor = gestorRepository.findById(optLogin.getId()).orElseThrow(() -> new BusinessException("GESTOR NÃO ENCONTRADO"));
             LoginResponseDTO  loginResponseDTO = new LoginResponseDTO(optLogin.getId(), optLogin.getNome(), optLogin.getEmail(), 0, gestor.getTipo());
            return loginResponseDTO;
        }
         throw new BusinessException("TIPO DE USUARIO INVALIDO");
    }


}
