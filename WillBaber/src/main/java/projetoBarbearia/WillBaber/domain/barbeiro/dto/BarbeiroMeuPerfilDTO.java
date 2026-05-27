package projetoBarbearia.WillBaber.domain.barbeiro.dto;

import projetoBarbearia.WillBaber.domain.horarioTrabalho.dto.HorarioTrabalhoDTO;

import java.util.List;

public record BarbeiroMeuPerfilDTO(
        Long id,
        String nome,
        String descricao,
        String telefone,
        String cpf,
        String email,
        byte[] imagem,
        List<HorarioTrabalhoDTO> horarios
) {
}
