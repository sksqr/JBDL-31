package io.bootify.l12_visitor_app.repos;

import io.bootify.l12_visitor_app.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatRepository extends JpaRepository<Flat, Long> {
}
