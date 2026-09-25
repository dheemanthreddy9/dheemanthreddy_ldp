package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByAgeGreaterThan(Integer age);

    List<User> findByNameContainingIgnoreCase(String keyword);
}
