package projetoBarbearia.WillBaber.domain.gestor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;
import projetoBarbearia.WillBaber.domain.users.Users;


@Getter
@Setter
@Entity
@Table(name = "gestor")
public class Gestor extends Users {

    public Gestor() {
    }

    public Gestor(String nome, String email, String senha, String cpf, String numero){
        super(nome, email, senha, cpf, numero);
    }

    @Override
    public TipoUsers getTipo() {
        return TipoUsers.GESTOR;
    }
}
