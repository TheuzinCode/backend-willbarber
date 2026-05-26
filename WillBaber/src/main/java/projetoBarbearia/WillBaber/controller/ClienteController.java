package projetoBarbearia.WillBaber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseCliente;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteAtualizarDTO;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteResponseDTO;
import projetoBarbearia.WillBaber.service.ClienteService;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/willbarber")
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/agendamentos/{id}/meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarAgendamentosCliente(
            @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarAgendados(id));
    }

    @GetMapping("/agendamentos/{id}/meus-agendamentos-concluidos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarHistorico(
            @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarConcluidos(id));
    }

    @GetMapping("/perfil/{id}")
    private ResponseEntity<ClienteResponseDTO> perfil(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }

    @GetMapping("/agendamentos/{id}/todos-meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseCliente>> listarTodosAgendamentosCLiente(
            @PathVariable Long id) {
        return ResponseEntity.ok(clienteService.listarTodos(id));
    }

    @PutMapping("/perfil/{id}/atualizar")
    public ResponseEntity<?> atualizarPerfilUsuario(@PathVariable Long id, @RequestBody ClienteAtualizarDTO clienteAtualizarDTO){
        var entity = clienteService.atualizarCLiente(id, clienteAtualizarDTO);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("/listar-todos-Clientes")
    public ResponseEntity<?> listarTodosCliente (){
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }


    //TODO
    //PONTOS
    //PERFIL

}
