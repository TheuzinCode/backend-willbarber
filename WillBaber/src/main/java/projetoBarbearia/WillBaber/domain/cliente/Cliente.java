package projetoBarbearia.WillBaber.domain.cliente;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.users.Users;


@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente extends Users {


    public Cliente(String nome, String email, String senha, String cpf) {
        super(nome, email, senha, cpf);
    }

    @Override
    public String getTipo() {
        return "CLIENTE";
    }
}
