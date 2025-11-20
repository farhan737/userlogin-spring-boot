package userlogin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import userlogin.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	boolean existsByEmail(String email);
	User findByEmail(String email);
}
