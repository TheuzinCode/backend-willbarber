package projetoBarbearia.WillBaber.domain.horarioTrabalho.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioTrabalhoDTO(
        DayOfWeek diaSemana,
        LocalTime horaInicio,
        LocalTime horaFim
) {
}
