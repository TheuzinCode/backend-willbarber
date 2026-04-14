package projetoBarbearia.WillBaber.domain.servico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeServico;
    private BigDecimal preco;
    private String descricao;
    private LocalTime tempoServico;
    private Integer pontos;

    public Servico(String nomeServico, BigDecimal preco, String descricao, LocalTime tempoServico, Integer pontos) {
        this.nomeServico = nomeServico;
        this.preco = preco;
        this.descricao = descricao;
        this.tempoServico = tempoServico;
        this.pontos = pontos;
    }

    public Servico() {
    }

}
