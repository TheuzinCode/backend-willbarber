package projetoBarbearia.WillBaber.domain.horarioTrabalho;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "horario_trabalho")
public class HorarioTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek diaSemana;

    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

}
