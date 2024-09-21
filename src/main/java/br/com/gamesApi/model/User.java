package br.com.gamesApi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "tb_user_games")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nickname_user")
    private String nickname;
    @Column(name = "email_user")
    private String email;
    @Column(name = "user_password")
    private String senha;
    @Column(name = "url_image")
    private String url;
}
