package projetoBarbearia.WillBaber.domain.recompensa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recompenasas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da recompensa é obrigatório.")
    private String nomeRecompensa;

    @NotBlank(message = "A descricao da recompensa é obrigatório.")
    private String descricao;

    @NotNull(message = "A quantidade de pontos é obrigatória.")
    @PositiveOrZero(message = "Os pontos não podem ser negativos.")
    private Integer pontos;
}
