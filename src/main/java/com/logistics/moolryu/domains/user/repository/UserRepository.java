package com.logistics.moolryu.domains.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logistics.moolryu.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
}
