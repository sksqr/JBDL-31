package com.example.L10springjpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {
}
