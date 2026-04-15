package projetoBarbearia.WillBaber.domain.agenda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.servico.Servico;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "agendamento",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"barbeiro_id", "dataHora"})
        }
)
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;

    @CreationTimestamp
    private Instant creationTimestamp;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Agendamento(LocalDateTime dataHora, BigDecimal preco, StatusAgendamento status, Barbeiro barbeiro, Cliente cliente, Servico servico) {
        this.dataHora = dataHora;
        this.preco = preco;
        this.status = status;
        this.barbeiro = barbeiro;
        this.cliente = cliente;
        this.servico = servico;
    }

    public Agendamento() {
    }
}
