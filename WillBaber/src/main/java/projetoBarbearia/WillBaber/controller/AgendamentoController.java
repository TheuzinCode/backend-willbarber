package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.service.AgendamentoService;

@RestController
@RequestMapping("/willbarber/agendamento")
@AllArgsConstructor
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @PostMapping("/novoagendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento){
        System.out.println("🔥 Criando agendamento...");
        Agendamento novoAgendamento = agendamentoService.salvarAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }
}
