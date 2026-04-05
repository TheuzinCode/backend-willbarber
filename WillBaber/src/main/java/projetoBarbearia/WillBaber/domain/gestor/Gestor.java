package projetoBarbearia.WillBaber.domain.gestor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.users.Users;


@Getter
@Setter
@Entity
@Table(name = "gestor")
public class Gestor extends Users {



    public Gestor(String nome, String email, String senha, String cpf){
        super(nome, email, senha, cpf);
    }

    @Override
    public String getTipo() {
        return "GESTOR";
    }
}
