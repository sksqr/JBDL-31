package io.bootify.l13_visitor_app.repos;

import io.bootify.l13_visitor_app.domain.Flat;
import io.bootify.l13_visitor_app.domain.Visit;
import io.bootify.l13_visitor_app.model.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VisitRepository extends JpaRepository<Visit, Long> {

    public List<Visit> findByFlatAndStatus(Flat flat, VisitStatus status);
}
