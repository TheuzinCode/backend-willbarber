package projetoBarbearia.WillBaber.domain.agenda;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "agendamento")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Barbeiro barbeiro;
}
