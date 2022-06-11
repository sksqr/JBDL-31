package io.bootify.l16_visitor_app.repos;

import io.bootify.l16_visitor_app.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    public Optional<Visitor> findOneByIdNumber(String idNumber);
}
