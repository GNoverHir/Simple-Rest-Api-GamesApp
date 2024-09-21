package br.com.gamesApi.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String url;
    private String email;
    private String nickname;
    private int id;
}
