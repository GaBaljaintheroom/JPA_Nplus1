package com.example.jpafetch.repository;

import com.example.jpafetch.dto.TeamsDto;
import com.example.jpafetch.entity.Team;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {


    @Query("select distinct t from Team t join fetch t.members")
    List<Team> findAllJoinFetch();

    @EntityGraph(attributePaths = "members")
    @Query("select distinct t from Team t")
    List<Team> findAllByEntityGraph();

    @Query("select distinct t from Team t join fetch t.members")
    Page<Team> findAllJoinFetchWithPaging(Pageable pageable);

    @Query("select distinct t from Team t join fetch t.members join fetch t.sponsors where t.id = :teamId")
    Optional<Team> findTeamWithMembersAndSponsorsByJoinFetch(@Param("teamId") Long teamId);

    @Query("SELECT new com.example.jpafetch.dto.TeamsDto(t.name, m.name, s.name) " +
        "FROM Team as t LEFT JOIN t.members as m LEFT JOIN t.sponsors as s where t.id =:teamId")
    TeamsDto findTeamWithMembersAndSponsorsByLeftJoin(@Param("teamId") Long teamId);
}