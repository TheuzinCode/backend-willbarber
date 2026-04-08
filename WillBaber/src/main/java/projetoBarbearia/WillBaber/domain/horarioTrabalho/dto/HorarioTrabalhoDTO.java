package projetoBarbearia.WillBaber.domain.horarioTrabalho.dto;

import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioTrabalhoDTO(
        DayOfWeek diaSemana,
        LocalTime horaInicio,
        LocalTime horaFim
) {
    public HorarioTrabalhoDTO(HorarioTrabalho h) {
        this(
                h.getDiaSemana(),
                h.getHorarioInicio(),
                h.getHorarioFim()
        );
    }
}
