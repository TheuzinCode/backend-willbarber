package projetoBarbearia.WillBaber.domain.servico.dto;

import projetoBarbearia.WillBaber.domain.servico.Servico;

import java.math.BigDecimal;
import java.time.LocalTime;

public record ServicoRequestDTO(
        String nomeServico,
        BigDecimal preco,
        String descricao,
        LocalTime tempoServico,
        Integer pontos
) {

    public static ServicoResponseDTO toDTO(Servico servico) {
        return new ServicoResponseDTO(
                servico.getId(),
                servico.getNomeServico(),
                servico.getPreco(),
                servico.getDescricao(),
                servico.getTempoServico(),
                servico.getPontos()
        );
    }

    public static Servico tranformarServico (ServicoRequestDTO servicoRequestDTO){

            Servico servico = new Servico();

            servico.setNomeServico(servicoRequestDTO.nomeServico);
            servico.setPreco(servicoRequestDTO.preco);
            servico.setDescricao(servicoRequestDTO.descricao);
            servico.setTempoServico(servicoRequestDTO.tempoServico);
            servico.setPontos(servicoRequestDTO.pontos);

        return servico;

    }
}
