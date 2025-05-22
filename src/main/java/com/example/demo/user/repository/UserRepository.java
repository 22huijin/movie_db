package com.example.demo.user.repository;

import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);
    List<User> findByJoinDate(LocalDate joinDate);
    List<User> findByBirthDate(LocalDate birthDate);

    @Query("SELECT u FROM User u JOIN FETCH u.membershipType WHERE u.joinDate = :joinDate")
    List<User> findByJoinDateWithMembership(@Param("joinDate") LocalDate joinDate);

    @Query("SELECT u FROM User u JOIN FETCH u.membershipType WHERE u.birthDate = :birthDate")
    List<User> findByBirthDateWithMembership(@Param("birthDate") LocalDate birthDate);
}