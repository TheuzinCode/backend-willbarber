package projetoBarbearia.WillBaber.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.servico.Servico;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoRequestDTO;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoResponseDTO;
import projetoBarbearia.WillBaber.service.ServicoService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicos")
@AllArgsConstructor
public class ServicoController {

    private ServicoService servicoService;


    @PostMapping("/novo-servico")
    public ResponseEntity<Servico> novoServico(@RequestBody ServicoRequestDTO servicoRequestDTO){
        var entity = ServicoRequestDTO.tranformarServico(servicoRequestDTO);
        servicoService.salvarServico(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarTodos (){
        return ResponseEntity.ok(servicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO>listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicoService.bucarPorId(id));

    }

}
