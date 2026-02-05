package com.kdn.kdnelectrical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.entity.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	long countByRole(Role role);

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);
}
