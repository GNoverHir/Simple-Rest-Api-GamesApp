package br.com.gamesApi.controller;

import br.com.gamesApi.dto.LoginRequest;
import br.com.gamesApi.dto.UserRequest;
import br.com.gamesApi.dto.UserResponse;
import br.com.gamesApi.model.User;
import br.com.gamesApi.repository.UserRepository;
import br.com.gamesApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequest dados){
        User user = userService.userToDTO(dados);
        User userSalvo = userRepository.save(user);
        UserResponse userResponse = userService.userResponse(userSalvo);
        return new ResponseEntity<>(userSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> readByEmail(@PathVariable String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();

        if (!user.getSenha().equals(loginRequest.getSenha())) {
            return new ResponseEntity<>("Senha incorreta", HttpStatus.UNAUTHORIZED);
        }

        UserResponse userResponse = userService.userResponse(user);
        return new ResponseEntity<>(userResponse.toString(), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updatePartial(@PathVariable String email, @RequestBody User userAtualizado) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userAtualizado.getUrl() != null) {
                user.setUrl(userAtualizado.getUrl());
            }
            if (userAtualizado.getEmail() != null) {
                user.setEmail(userAtualizado.getEmail());
            }
            if (userAtualizado.getNickname() != null) {
                user.setNickname(userAtualizado.getNickname());
            }

            User clienteAtualizadoFinal = userRepository.save(user);
            return new ResponseEntity<>(clienteAtualizadoFinal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
