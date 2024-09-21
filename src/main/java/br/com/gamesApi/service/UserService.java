package br.com.gamesApi.service;

import br.com.gamesApi.dto.UserRequest;
import br.com.gamesApi.dto.UserResponse;
import br.com.gamesApi.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User userToDTO(UserRequest userRequest){
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setSenha(userRequest.getSenha());
        user.setNickname(userRequest.getNickname());
        return user;
    }

    public UserResponse userResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setNickname(user.getNickname());
        userResponse.setId(user.getId());
        userResponse.setUrl(user.getUrl());
        return userResponse;
    }
}
