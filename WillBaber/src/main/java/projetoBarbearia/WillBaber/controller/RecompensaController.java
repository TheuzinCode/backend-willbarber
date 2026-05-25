package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.recompensa.dto.NovaRecompensaDTO;
import projetoBarbearia.WillBaber.domain.recompensa.dto.NovaRecompensaResponseDTO;
import projetoBarbearia.WillBaber.service.RecompensaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Willbarber")
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @PostMapping("/recompensa/nova-recompensa")
    public ResponseEntity<?> novaRecompensa(@RequestBody NovaRecompensaDTO novaRecompensaDTO){
        var entity = recompensaService.salvarNovaRecompensa(novaRecompensaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/recompensas")
    ResponseEntity<List<NovaRecompensaResponseDTO>> listarRecompensas(){
        return ResponseEntity.ok(recompensaService.listarTodasRecompensas());
    }

}
