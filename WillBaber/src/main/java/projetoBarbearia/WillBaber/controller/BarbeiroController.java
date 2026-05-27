package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseDTO;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseGestorDTO;
import projetoBarbearia.WillBaber.domain.users.dto.UsersDTO;
import projetoBarbearia.WillBaber.service.BarbeiroService;


import java.io.IOException;
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

    @PutMapping("/barbeiros/editar-barbeiros/{id}")
    public ResponseEntity<BarbeiroResponseGestorDTO>  editarBarbeiro(
            @PathVariable Long id,

            @RequestPart("users") BarbeiroResponseGestorDTO barbeiroResponseGestorDTO,

            @RequestPart(value = "imagem", required = false)
            MultipartFile imagem
    ) throws IOException {
        var entity = barbeiroService.editarBarbeiro(id, barbeiroResponseGestorDTO, imagem);

        return ResponseEntity.ok(entity);
    }

    @GetMapping("/barbeiro/meu-perfil/{id}")
    public ResponseEntity<?> procurarPorId (@PathVariable Long id){
        var enttity = barbeiroService.buscarPorId(id);
        return ResponseEntity.ok(enttity);


    }






    //TODO
    //LISTAR TODOS AGENDAMENTOS
    //EDITAR PERFIL
    //AGENDA DA SEMANA
}
