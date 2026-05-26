package projetoBarbearia.WillBaber.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.AgendamentoRecompensa;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.recompensa.Recompensa;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;
import projetoBarbearia.WillBaber.domain.tipoPagamento.TipoPagamento;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.*;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AgendamentoRecompensaService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private BarbeiroRepository barbeiroRepository;
    private RecompensasRepository recompensasRepository;
    private AgendamentoRecompensaRepository agendamentoRecompensaRepository;

    public AgendamentoRecompensa salvarAgendamentoRecompensa(AgendamentoRecompensa agendamentoRecompensa) {

        if (agendamentoRecompensa.getRecompensa() == null) {

            throw new BusinessException(
                    "Recompensa obrigatória"
            );
        }

        Cliente cliente = clienteRepository.findById(agendamentoRecompensa.getCliente().getId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoRecompensa.getBarbeiro().getId())
                .orElseThrow(() -> new BusinessException("Barbeiro não encontrado"));

        boolean existe = agendamentoRepository
                .existsByBarbeiroIdAndDataHora(barbeiro.getId(), agendamentoRecompensa.getDataHora());

        boolean existeRecompensa = agendamentoRecompensaRepository
                .existsByBarbeiroIdAndDataHora(
                        barbeiro.getId(),
                        agendamentoRecompensa.getDataHora()
                );

        if (existe || existeRecompensa) {
            throw new BusinessException("Horário já está ocupado");
        }

        Recompensa recompensa = recompensasRepository.findById(agendamentoRecompensa.getRecompensa().getId())
                .orElseThrow(() -> new BusinessException("RECOMPENSA NÃO ENCONTRADA"));

        agendamentoRecompensa.setRecompensa(recompensa);
        agendamentoRecompensa.setBarbeiro(barbeiro);
        agendamentoRecompensa.setCliente(cliente);
        agendamentoRecompensa.setTipoPagamento(
                TipoPagamento.RECOMPENSA
        );

        agendamentoRecompensa.setStatus(
                StatusAgendamento.AGENDADO
        );

        if (cliente.getPontos() > recompensa.getPontos()) {
            System.out.println(
                    "Pontos cliente: " +
                            cliente.getPontos()
            );

            System.out.println(
                    "Pontos recompensa: " +
                            recompensa.getPontos()
            );
            throw new BusinessException(
                    "Cliente não possui pontos suficientes"
            );
        }
        cliente.setPontos(cliente.getPontos() - recompensa.getPontos());

        clienteRepository.save(cliente);

        return agendamentoRecompensaRepository.save(agendamentoRecompensa);

    }
}
