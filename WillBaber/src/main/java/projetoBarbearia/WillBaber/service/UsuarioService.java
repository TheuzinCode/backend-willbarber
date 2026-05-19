package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projetoBarbearia.WillBaber.Factory.UsersFactory;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.exception.CpfJaCadastradoException;
import projetoBarbearia.WillBaber.exception.EmailJaCadastradoException;
import projetoBarbearia.WillBaber.repositories.UsuarioRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public Users criarUsuario(Users users) {

        Optional<Users> existeCpf = usuarioRepository.findByCpf(users.getCpf());
        Optional<Users> existeEmail = usuarioRepository.findByEmail(users.getEmail());

        if (existeCpf.isPresent()) {
            System.out.println("CPF CADASTRADO");
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        if (!existeEmail.isEmpty()) {
            System.out.println("EMAIL CADASTRADO");
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        return usuarioRepository.save(users);
    }

    public Users criarUsuario2(
            UsersDTO usersDTO,
            MultipartFile imagem
    ) throws IOException {

        Users entity = UsersFactory.criarUsuario(usersDTO);

        Optional<Users> existeCpf =
                usuarioRepository.findByCpf(entity.getCpf());

        Optional<Users> existeEmail =
                usuarioRepository.findByEmail(entity.getEmail());

        if (existeCpf.isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        if (existeEmail.isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        if (
                entity instanceof Barbeiro barbeiro &&
                        imagem != null &&
                        !imagem.isEmpty()
        ) {

            barbeiro.setImagem(imagem.getBytes());
        }

        return usuarioRepository.save(entity);
    }

    public List<UsersDTO> listarAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsersDTO::toDTO)
                .toList();
    }

    public Optional<Users> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Users salvarUsuario(Users users) {
        return usuarioRepository.save(users);
    }


}
