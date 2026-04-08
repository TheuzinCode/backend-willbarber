package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.Factory.UsersFactory;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/willbarber")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/criarUsuario")
    public ResponseEntity<Users>criarUsuario(@RequestBody UsersDTO usersDTO){
        try{
            var entity = UsersFactory.criarUsuario(usersDTO);
            usuarioService.criarUsuario(entity);
            ResponseEntity.status(201).build();
        }catch (Exception e){
            ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsersDTO>>ListarTodos(){
        return ResponseEntity.ok(usuarioService.listarAll());
    }
}
