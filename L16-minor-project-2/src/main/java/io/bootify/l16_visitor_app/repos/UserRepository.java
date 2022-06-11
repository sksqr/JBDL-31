package io.bootify.l16_visitor_app.repos;

import io.bootify.l16_visitor_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
