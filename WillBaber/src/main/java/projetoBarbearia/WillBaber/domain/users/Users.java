package projetoBarbearia.WillBaber.domain.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Users {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String cpf;

    @Enumerated(EnumType.STRING)
    protected TipoUsers tipo;

    public Users(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public Users() {

    }

}
