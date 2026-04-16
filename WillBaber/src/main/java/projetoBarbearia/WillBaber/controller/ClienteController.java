package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseCliente;
import projetoBarbearia.WillBaber.service.ClienteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/willbarber/agendamentos")
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/{id}/meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarAgendamentosCliente(
            @PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarTodos(id));
    }

    //TODO
    //AGEDAMENTO ATIVOS
    //TODOS AGENDAMENTOS
    //PONTOS
    //PERFIL

}
