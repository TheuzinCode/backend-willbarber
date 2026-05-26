package projetoBarbearia.WillBaber.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseCliente;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteAtualizarDTO;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteResponseDTO;
import projetoBarbearia.WillBaber.domain.cliente.dto.RankingClienteDTO;
import projetoBarbearia.WillBaber.domain.statusAgendamento.StatusAgendamento;
import projetoBarbearia.WillBaber.exception.BusinessException;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.BarbeiroRepository;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

    private AgendamentoRepository agendamentoRepository;
    private ClienteRepository clienteRepository;
    private BarbeiroRepository barbeiroRepository;


    public List<AgendamentoResponseCliente> listarTodos(Long id){

        List<Agendamento> agendamentos = agendamentoRepository.findByClienteId(id);

        return  agendamentos.stream()
                .map(agendamento -> new AgendamentoResponseCliente(
                        agendamento.getId(),
                        agendamento.getServico().getNomeServico(),
                        agendamento.getBarbeiro().getNome(),
                        agendamento.getServico().getPreco(),
                        agendamento.getDataHora(),
                        agendamento.getStatus(),
                        agendamento.getPontos()
                ))
                .toList();

    }

    public List<AgendamentoResponseCliente> listarAgendados(Long id){

        List<Agendamento> agendamentos =
                agendamentoRepository.findByClienteIdAndStatus(id, StatusAgendamento.AGENDADO);

        return agendamentos.stream()
                .map(agendamento -> new AgendamentoResponseCliente(
                        agendamento.getId(),
                        agendamento.getServico().getNomeServico(),
                        agendamento.getBarbeiro().getNome(),
                        agendamento.getServico().getPreco(),
                        agendamento.getDataHora(),
                        agendamento.getStatus(),
                        agendamento.getPontos()
                ))
                .toList();
    }

    public List<AgendamentoResponseCliente> listarConcluidos(Long id){

        List<Agendamento> agendamentos =
                agendamentoRepository.findByClienteIdAndStatus(id, StatusAgendamento.FINALIZADO);

        return agendamentos.stream()
                .map(agendamento -> new AgendamentoResponseCliente(
                        agendamento.getId(),
                        agendamento.getServico().getNomeServico(),
                        agendamento.getBarbeiro().getNome(),
                        agendamento.getServico().getPreco(),
                        agendamento.getDataHora(),
                        agendamento.getStatus(),
                        agendamento.getPontos()

                ))
                .toList();
    }

    public ClienteResponseDTO buscarCliente(Long id){

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new BusinessException("usuario não encontrado"));

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getNumero(),
                cliente.getCpf(),
                cliente.getSenha(),
                cliente.getPontos()
        ) ;
    }

    public List<ClienteResponseDTO> listarTodosClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getNumero(),
                        cliente.getCpf(),
                        cliente.getSenha(),
                        cliente.getPontos()
                )).toList();
    }

    public ClienteAtualizarDTO atualizarCLiente(Long id, ClienteAtualizarDTO clienteAtualizarDTO){
        var entity = clienteRepository.findById(id).orElseThrow(() -> new BusinessException("usuario não encontrado"));

        Cliente cliente = entity;

        cliente.setNome(clienteAtualizarDTO.nomeCompleto());
        cliente.setEmail(clienteAtualizarDTO.email());
        cliente.setNumero(clienteAtualizarDTO.telefone());
        cliente.setSenha(clienteAtualizarDTO.senha());


        clienteRepository.save(cliente);

        return new ClienteAtualizarDTO(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getNumero(),
                cliente.getSenha()
        );
    }

    public List<RankingClienteDTO>
    listarRankingClientes() {

        List<Agendamento> agendamentos =
                agendamentoRepository.findAll();

        Map<Cliente, Long> ranking =
                agendamentos.stream()

                        .collect(Collectors.groupingBy(
                                Agendamento::getCliente,
                                Collectors.counting()
                        ));

        return ranking.entrySet()
                .stream()

                .map(entry -> new RankingClienteDTO(

                        entry.getKey().getId(),

                        entry.getKey().getNome(),

                        entry.getValue()

                ))

                .sorted(
                        Comparator.comparing(
                                RankingClienteDTO::quantidadeCortes
                        ).reversed()
                )

                .toList();
    }


}
