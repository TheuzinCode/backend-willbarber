package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.exception.CpfJaCadastradoException;
import projetoBarbearia.WillBaber.exception.EmailJaCadastradoException;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public Users criarUsuario(Users users){

        Optional<Users> existeCpf =  usuarioRepository.findByCpf(users.getCpf());
        List<Users> existeEmail = usuarioRepository.findByEmail(users.getEmail());

        if (existeCpf.isPresent()) {
            System.out.println("CPF CADASTRADO");
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        if(!existeEmail.isEmpty()){
            System.out.println("EMAIL CADASTRADO");
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        return usuarioRepository.save(users);
    }

    public List<UsersDTO> listarAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsersDTO::toDTO)
                .toList();
    }


}
