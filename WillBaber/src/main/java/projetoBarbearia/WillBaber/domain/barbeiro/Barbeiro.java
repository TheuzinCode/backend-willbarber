package projetoBarbearia.WillBaber.domain.barbeiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;
import projetoBarbearia.WillBaber.domain.users.Users;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Barbeiro")
public class Barbeiro extends Users {


    private String descricao;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "BYTEA")
    private byte[] imagem;


    public Barbeiro() {
    }

    public Barbeiro(String nome, String email, String senha, String cpf, String numero) {
        super(nome, email, senha, cpf, numero);
    }

    @Override
    public TipoUsers getTipo() {
        return TipoUsers.BARBEIRO;
    }

    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HorarioTrabalho> horarios;

    public void setHorarios(List<HorarioTrabalho> horarios) {
        this.horarios = horarios;
        for (HorarioTrabalho h : horarios) {
            h.setBarbeiro(this);
        }
    }

}
