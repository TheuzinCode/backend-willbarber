package projetoBarbearia.WillBaber.domain.cliente;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projetoBarbearia.WillBaber.domain.users.TipoUsers;
import projetoBarbearia.WillBaber.domain.users.Users;


@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente extends Users {

    @JsonAlias({"Pontos", "pontos"})
    private Integer pontos;

    public Cliente(String nome, String email, String senha, String cpf, String numero ,Integer pontos) {
        this.pontos= pontos;
        super(nome, email, senha, cpf, numero);
    }



    public Cliente(){

    }

    @Override
    public TipoUsers getTipo() {
        return TipoUsers.CLIENTE;
    }
}
