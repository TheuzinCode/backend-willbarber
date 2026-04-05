package projetoBarbearia.WillBaber.domain.barbeiro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.horarioTrabalho.HorarioTrabalho;
import projetoBarbearia.WillBaber.domain.users.Users;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Barbeiro")
public class Barbeiro extends Users {

    public Barbeiro() {
    }

    public Barbeiro(String nome, String email, String senha, String cpf) {
        super(nome, email, senha, cpf);
    }



    @Override
    public String getTipo() {
        return "BARBEIRO";
    }

    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL)
    private List<HorarioTrabalho> horarios;

    public void setHorarios(List<HorarioTrabalho> horarios) {
        this.horarios = horarios;
        for (HorarioTrabalho h : horarios) {
            h.setBarbeiro(this);
        }
    }

}
