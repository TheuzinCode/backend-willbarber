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
        return servicoRepository.findAll()
                .stream()
                .map(ServicoRequestDTO::toDTO)
                .toList();
    }

    public ServicoResponseDTO bucarPorId(Long id){
        var optServico = servicoRepository.findById(id).orElseThrow(() -> new BusinessException("Servico não encontrado"));
        return ServicoRequestDTO.toDTO(optServico);
    }
}
