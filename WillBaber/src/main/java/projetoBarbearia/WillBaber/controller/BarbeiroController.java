package projetoBarbearia.WillBaber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import projetoBarbearia.WillBaber.service.AgendamentoSerivce;
import projetoBarbearia.WillBaber.service.BarbeiroService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/horarios-disponiveis")
    public List<LocalDateTime> listarHorariosBarbeiro(
            @RequestParam Long barbeiroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return barbeiroService.listarHorariosDisponiveis(barbeiroId, data);
    }
}
