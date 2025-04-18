package com.example.demo.user.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "회원 관련 테스트 API")
public class UserController {

  private final UserRepository userRepository;
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Operation(summary = "모든 유저 조회")
  @GetMapping
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Operation(summary = "유저 생성")
  @PostMapping
  public User create(@RequestBody User user) {
    return userRepository.save(user);
  }
}