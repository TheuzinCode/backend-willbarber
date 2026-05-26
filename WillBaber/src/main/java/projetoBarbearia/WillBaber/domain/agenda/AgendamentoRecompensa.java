package projetoBarbearia.WillBaber.domain.agenda;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.recompensa.Recompensa;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;
import projetoBarbearia.WillBaber.domain.tipoPagamento.TipoPagamento;

import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento_recompensa")
@Entity
public class AgendamentoRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @CreationTimestamp
    private Instant creationTimestamp;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensa;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento = TipoPagamento.RECOMPENSA;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusRecompensa = StatusAgendamento.AGENDADO;

}
