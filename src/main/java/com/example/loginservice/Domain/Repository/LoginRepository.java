package com.example.loginservice.Domain.Repository;

import com.example.loginservice.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Member, Long> {
}
