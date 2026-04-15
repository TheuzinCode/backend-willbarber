package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;
import projetoBarbearia.WillBaber.service.AgendamentoService;

@RestController
@RequestMapping("/willbarber/agendamento")
@AllArgsConstructor
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @PostMapping("/novoagendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento){
        Agendamento novoAgendamento = agendamentoService.salvarAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }




    @PutMapping("agendamentos/{id}")
    public ResponseEntity<?> alterarStatusAgendamento(@PathVariable Long id, @RequestBody StatusAgendamento statusAgendamento){
        agendamentoService.atualizarStatus(id, statusAgendamento);
        return ResponseEntity.ok().build();
    }
}
