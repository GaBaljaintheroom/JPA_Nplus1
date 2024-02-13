package com.example.jpafetch.repository;

import com.example.jpafetch.entity.Team;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {


    @Query("select distinct t from Team t join fetch t.members")
    List<Team> findAllJoinFetch();

    @EntityGraph(attributePaths = "members")
    @Query("select distinct t from Team t")
    List<Team> findAllByEntityGraph();

    @Query("select distinct t from Team t join fetch t.members")
    Page<Team> findAllJoinFetchWithPaging(Pageable pageable);

}