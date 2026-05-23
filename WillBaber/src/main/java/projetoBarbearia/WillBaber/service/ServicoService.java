package projetoBarbearia.WillBaber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.servico.Servico;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoRequestDTO;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoResponseDTO;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.ServicoRepository;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico salvarServico(Servico servico){
        return servicoRepository.save(servico);
    }

    public List<ServicoResponseDTO> listar() {
        return servicoRepository.findAllByOrderByIdAsc()
                .stream()
                .map(ServicoRequestDTO::toDTO)
                .toList();
    }

    public ServicoResponseDTO bucarPorId(Long id){
        var optServico = servicoRepository.findById(id).orElseThrow(() -> new BusinessException("Servico não encontrado"));
        return ServicoRequestDTO.toDTO(optServico);
    }

    public ServicoResponseDTO editarServico (Long id, ServicoRequestDTO servicoRequestDTO ){

        var servicoOpt = servicoRepository.findById(id).orElseThrow(() -> new BusinessException(" Servico não encontrado no banco de dados"));


        Servico servico = servicoOpt;

        servico.setNomeServico(servicoRequestDTO.nomeServico());
        servico.setPreco(servicoRequestDTO.preco());
        servico.setDescricao(servicoRequestDTO.descricao());
        servico.setTempoServico(servicoRequestDTO.tempoServico());
        servico.setPontos(servicoRequestDTO.pontos());

        servicoRepository.save(servico);

        return new ServicoResponseDTO(
                servico.getId(),
                servico.getNomeServico(),
                servico.getPreco(),
                servico.getDescricao(),
                servico.getTempoServico(),
                servico.getPontos()
        );



    }



}
