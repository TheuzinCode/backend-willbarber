package projetoBarbearia.WillBaber.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.AgendamentoRecompensa;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.recompensa.Recompensa;
import projetoBarbearia.WillBaber.domain.recompensa.dto.NovaRecompensaDTO;
import projetoBarbearia.WillBaber.domain.recompensa.dto.NovaRecompensaResponseDTO;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;
import projetoBarbearia.WillBaber.repositories.RecompensasRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RecompensaService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private BarbeiroRepository barbeiroRepository;
    private RecompensasRepository recompensasRepository;

    public NovaRecompensaResponseDTO salvarNovaRecompensa(NovaRecompensaDTO novaRecompensaDTO){

        Recompensa recompensa = new Recompensa();

        recompensa.setNomeRecompensa(novaRecompensaDTO.nomeRecompensa());
        recompensa.setDescricao(novaRecompensaDTO.descricao());
        recompensa.setPontos(novaRecompensaDTO.pontos());

        Recompensa recompensa1va =  recompensasRepository.save(recompensa);

        return new NovaRecompensaResponseDTO(
                recompensa1va.getId(),
                recompensa1va.getNomeRecompensa(),
                recompensa1va.getDescricao(),
                recompensa1va.getPontos()
        );
    }

    public List<NovaRecompensaResponseDTO> listarTodasRecompensas(){

        List<Recompensa> recompensas =
                recompensasRepository.findAll();

        if (recompensas.isEmpty()) {
            throw new BusinessException(
                    "Nenhuma recompensa encontrada"
            );
        }

        return recompensas.stream().map(
                recompensa -> new NovaRecompensaResponseDTO(
                        recompensa.getId(),
                        recompensa.getNomeRecompensa(),
                        recompensa.getDescricao(),
                        recompensa.getPontos()
                )
        ).toList();
    }
}
