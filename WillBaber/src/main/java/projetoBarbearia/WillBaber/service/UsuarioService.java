package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Users criarUsuario(Users users){
        return usuarioRepository.save(users);
    }

    public List<UsersDTO> listarAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsersDTO::toDTO)
                .toList();
    }


}
