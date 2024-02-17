package com.example.jpafetch.repository;

import static com.example.jpafetch.entity.QMember.member;
import static com.example.jpafetch.entity.QSponsor.sponsor;
import static com.example.jpafetch.entity.QTeam.team;

import com.example.jpafetch.dto.TeamsDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<TeamsDto> findTeamWithMembersAndSponsorsByLeftJoinAndGroupConcat(Long teamId) {
        return Optional.ofNullable(queryFactory
            .select(Projections.constructor(
                TeamsDto.class,
                team.name,
                groupConcatDistinct(member.name),
                groupConcatDistinct(sponsor.name)))
            .from(team)
            .leftJoin(member).on(team.id.eq(member.team.id))
            .leftJoin(sponsor).on(team.id.eq(sponsor.team.id))
            .where(team.id.eq(teamId))
            .fetchFirst()
        );
    }

    private StringExpression groupConcatDistinct(final StringExpression expression) {
        return Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", expression);
    }

}
