package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.Factory.UsersFactory;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/willbarber")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/criarUsuario")
    public ResponseEntity<Users>criarUsuario(@RequestBody UsersDTO usersDTO){
        var entity = UsersFactory.criarUsuario(usersDTO);
        usuarioService.criarUsuario(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsersDTO>>ListarTodos(){
        return ResponseEntity.ok(usuarioService.listarAll());
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<Users> buscarPorId(@PathVariable Long id){
        Optional<Users> entityUsuario = usuarioService.buscarUsuarioPorId(id);

        if (entityUsuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(entityUsuario.get());
    }
}
