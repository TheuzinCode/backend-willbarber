package projetoBarbearia.WillBaber.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseCliente;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseGestor;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseGestorDTO;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteResponseDTO;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoResponseDTO;
import projetoBarbearia.WillBaber.service.GestorService;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/willbarber/gestor")
public class GestorController {

    private GestorService gestorService;


    @GetMapping("/listar-todos-cliente")
    public ResponseEntity<List<ClienteResponseDTO>> listarTodosCliente(){
        return ResponseEntity.ok(gestorService.listarTodosClientes());
    }

    @GetMapping("/listar-todos-agendamentos")
    public ResponseEntity<List<AgendamentoResponseGestor>> listarTodosAgedamentos(){
        return ResponseEntity.ok(gestorService.listarTodosAgendamentos());
    }

    @GetMapping("/listar-todos-servicos")
    public ResponseEntity<List<ServicoResponseDTO>> listarTodosServicos(){
        return ResponseEntity.ok(gestorService.listarTodosServicos());
    }

    @GetMapping("listar-todos-barbeiros")
    public ResponseEntity<List<BarbeiroResponseGestorDTO>> listarTodosBarbeiros(){
        return ResponseEntity.ok(gestorService.listarTodosBarbeiros());
    }

    @GetMapping("/meu-perfil/{id}")
    public ResponseEntity<?> meuPerfilGestor(@PathVariable Long id){
        var entity = gestorService.meuPerfilGestor(id);
        return ResponseEntity.ok(entity);

    }



}
