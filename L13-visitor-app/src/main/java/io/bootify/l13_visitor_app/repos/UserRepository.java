package io.bootify.l13_visitor_app.repos;

import io.bootify.l13_visitor_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
