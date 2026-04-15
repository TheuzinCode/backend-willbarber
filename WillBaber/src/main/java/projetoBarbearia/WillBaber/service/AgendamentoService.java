package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;
import projetoBarbearia.WillBaber.repositories.HorarioTabalhoRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private BarbeiroRepository barbeiroRepository;
    private HorarioTabalhoRepository horarioTrabalhoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {

        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamento.getBarbeiro().getId())
                .orElseThrow(() -> new BusinessException("Barbeiro não encontrado"));

        boolean existe = agendamentoRepository
                .existsByBarbeiroIdAndDataHora(barbeiro.getId(), agendamento.getDataHora());

        if (existe) {
            throw new BusinessException("Horário já está ocupado");
        }

        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);

        return agendamentoRepository.save(agendamento);
    }

}
