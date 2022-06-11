package io.bootify.l16_visitor_app.repos;

import io.bootify.l16_visitor_app.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
