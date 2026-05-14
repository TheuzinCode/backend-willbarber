package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseDTO;
import projetoBarbearia.WillBaber.service.BarbeiroService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/willbarber")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/agendamento/horarios-disponiveis")
    public List<LocalDateTime> listarHorariosBarbeiro(
            @RequestParam Long barbeiroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return barbeiroService.listarHorariosDisponiveis(barbeiroId, data);
    }

    @GetMapping("/agendamento/listar-todos-barbeiros")
    public ResponseEntity<List<BarbeiroResponseDTO>> listarTodosBarbeiro(){
        return ResponseEntity.ok(barbeiroService.listarAll());
    }



    //TODO
    //LISTAR TODOS AGENDAMENTOS
    //EDITAR PERFIL
    //AGENDA DA SEMANA
}
