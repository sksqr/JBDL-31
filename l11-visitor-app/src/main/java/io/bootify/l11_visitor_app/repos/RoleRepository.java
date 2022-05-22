package io.bootify.l11_visitor_app.repos;

import io.bootify.l11_visitor_app.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
