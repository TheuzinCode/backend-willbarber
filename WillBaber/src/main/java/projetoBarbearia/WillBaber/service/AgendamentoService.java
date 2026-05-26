package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoRecompensaDTO;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseGestor;
import projetoBarbearia.WillBaber.domain.barbeiro.Barbeiro;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.domain.servico.Servico;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;
import projetoBarbearia.WillBaber.domain.tipoPagamento.TipoPagamento;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private BarbeiroRepository barbeiroRepository;
    private HorarioTabalhoRepository horarioTrabalhoRepository;
    private ServicoRepository servicoRepository;


    //SALVAR O AGENDAMENTO
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

        Servico servico = servicoRepository.findById(
                agendamento.getServico().getId()
        ).orElseThrow(() ->
                new BusinessException("Serviço não encontrado"));

        agendamento.setServico(servico);

        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        agendamento.setTipoPagamento(TipoPagamento.NORMAL);

        clienteRepository.save(cliente);


        return agendamentoRepository.save(agendamento);
    }


    //ATUALIZAR STATUS DO AGENDAMENTO
    public void atualizarStatus(Long id, StatusAgendamento statusAgendamento) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("agendamento não encontrado"));

        if (statusAgendamento == null) {
            throw new BusinessException("status do agendamento vazio");
        }

        if (statusAgendamento == StatusAgendamento.FINALIZADO) {

            int pontosServico = agendamento.getServico().getPontos();
            agendamento.getCliente().setPontos(
                    agendamento.getCliente().getPontos() + pontosServico);
        }
        agendamento.setStatus(statusAgendamento);
        agendamentoRepository.save(agendamento);
    }


    //LISTAR TODOS AGENDAMENTOS
    public List<AgendamentoResponseGestor> listarTodosAgendamentos(){
        List <Agendamento> agendamentos = agendamentoRepository.findAllByOrderByIdDesc();

        return agendamentos.stream()
                .map(agendamento -> new AgendamentoResponseGestor(
                        agendamento.getId(),
                        agendamento.getCliente().getNome(),
                        agendamento.getBarbeiro().getNome(),
                        agendamento.getServico().getNomeServico(),
                        agendamento.getPreco(),
                        agendamento.getDataHora(),
                        agendamento.getStatus(),
                        agendamento.getPontos()
                )).toList();
    }

}
