package com.example.jpafetch.repository;

import com.example.jpafetch.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
