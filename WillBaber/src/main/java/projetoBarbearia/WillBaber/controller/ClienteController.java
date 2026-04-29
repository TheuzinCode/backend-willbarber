package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseCliente;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteResponseDTO;
import projetoBarbearia.WillBaber.service.ClienteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/willbarber")
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/agendamentos/{id}/meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarAgendamentosCliente(
            @PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarAgendados(id));
    }

    @GetMapping("/agendamentos/{id}/meus-agendamentos-concluidos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarHistorico(
            @PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarConcluidos(id));
    }

    @GetMapping("/perfil/{id}")
    private ResponseEntity<ClienteResponseDTO>perfil(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }



    //TODO
    //TODOS AGENDAMENTOS
    //PONTOS
    //PERFIL

}
