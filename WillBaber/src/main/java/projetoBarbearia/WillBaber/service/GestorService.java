package projetoBarbearia.WillBaber.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projetoBarbearia.WillBaber.domain.agenda.Agendamento;
import projetoBarbearia.WillBaber.domain.agenda.dto.AgendamentoResponseGestor;
import projetoBarbearia.WillBaber.domain.barbeiro.dto.BarbeiroResponseGestorDTO;
import projetoBarbearia.WillBaber.domain.cliente.Cliente;
import projetoBarbearia.WillBaber.domain.cliente.dto.ClienteResponseDTO;
import projetoBarbearia.WillBaber.domain.servico.dto.ServicoResponseDTO;
import projetoBarbearia.WillBaber.repositories.AgendamentoRepository;
import projetoBarbearia.WillBaber.repositories.ClienteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GestorService {

    private ClienteService clienteService;
    private AgendamentoService agendamentoService;
    private ServicoService servicoService;
    private BarbeiroService barbeiroService;

    public List<ClienteResponseDTO> listarTodosClientes(){
        return clienteService.listarTodosClientes();
    }


    public List<AgendamentoResponseGestor> listarTodosAgendamentos(){
        return  agendamentoService.listarTodosAgendamentos();
    }

    public List<ServicoResponseDTO> listarTodosServicos(){
        return servicoService.listar();
    }

    public List<BarbeiroResponseGestorDTO> listarTodosBarbeiros(){
        return barbeiroService.listarTodosBarbeiros();
    }




}
