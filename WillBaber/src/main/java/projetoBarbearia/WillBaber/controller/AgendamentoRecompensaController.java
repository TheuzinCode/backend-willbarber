package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.agenda.AgendamentoRecompensa;
import projetoBarbearia.WillBaber.domain.recompensa.Recompensa;
import projetoBarbearia.WillBaber.service.AgendamentoRecompensaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/willbarber")
@AllArgsConstructor
public class AgendamentoRecompensaController {

    private AgendamentoRecompensaService agendamentoRecompensaService;

    @PostMapping("/agendamento/novoagendamento-recompensa")
    public ResponseEntity<AgendamentoRecompensa> criarAgendamentoRecompensa(@RequestBody AgendamentoRecompensa agendamentoRecompensa) {
        AgendamentoRecompensa novoAgendamento = agendamentoRecompensaService.salvarAgendamentoRecompensa(agendamentoRecompensa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }
}
