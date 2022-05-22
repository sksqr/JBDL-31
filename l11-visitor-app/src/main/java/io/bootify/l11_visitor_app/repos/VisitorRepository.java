package io.bootify.l11_visitor_app.repos;

import io.bootify.l11_visitor_app.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
