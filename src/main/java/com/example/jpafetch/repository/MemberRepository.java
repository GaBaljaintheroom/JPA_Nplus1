package com.example.jpafetch.repository;

import com.example.jpafetch.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select distinct m from Member m join fetch m.team")
    Page<Member> findAllJoinFetchWithPaging(Pageable pageable);

}
