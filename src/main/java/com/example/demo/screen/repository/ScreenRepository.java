package com.example.demo.screen.repository;

import com.example.demo.screen.domain.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
  Optional<Screen> findByName(String name);
}