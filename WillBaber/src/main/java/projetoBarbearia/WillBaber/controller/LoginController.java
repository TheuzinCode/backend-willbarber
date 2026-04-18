package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginRequestDTO;
import projetoBarbearia.WillBaber.domain.Login.dto.LoginResponseDTO;
import projetoBarbearia.WillBaber.service.LoginService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/willbarber")
public class LoginController {

    private LoginService loginService;

    @PostMapping("/entrar")
    public ResponseEntity<?> realizarLogin(@RequestBody LoginRequestDTO loginDTO){
        var validarLogin = loginService.verificarLogin(loginDTO);
        return ResponseEntity.ok(new LoginResponseDTO(
                validarLogin.getId(),
                validarLogin.getNome(),
                validarLogin.getEmail()));
    }

}
