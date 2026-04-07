package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.Factory.UsersFactory;
import projetoBarbearia.WillBaber.domain.users.Users;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.service.UsuarioService;

@RestController
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("criarUsuario")
    public ResponseEntity<Users>criarUsuario(@RequestBody UsersDTO usersDTO){
        try{
            var entity = UsersFactory.criarUsuario(usersDTO);
            usuarioService.criarUsuario(entity);
        }catch (Exception e){
            System.out.println("erro ao criar um usuario" + e);
        }
        return ResponseEntity.ok().build();
    }

}
