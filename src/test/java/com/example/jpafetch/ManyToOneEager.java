package com.example.jpafetch;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.jpafetch.entity.Member;
import com.example.jpafetch.entity.Team;
import com.example.jpafetch.repository.MemberRepository;
import com.example.jpafetch.repository.TeamRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ManyToOneEager {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("즉시 로딩 / 지연로딩 일 시 멤버 엔티티에서 팀 엔티티를 가져온다.")
    void getMemberFromTeam() {

        Member findMember = memberRepository.findById(1L).orElseThrow();

        System.out.println(findMember.getTeam().getClass());

        System.out.println(findMember.getTeam().getName());
    }

    @Test
    @DisplayName("즉시 로딩 / 지연 로딩 상관없이 팀 엔티티에서 관련된 멤버 엔티티를 가져오면 예외가 발생한다.")
    void getTeamFromMember() {

        Team findTeam = teamRepository.findById(1L).orElseThrow();

        List<Member> members = findTeam.getMembers();

        assertThatThrownBy(() -> {
            for (Member member : members) {
                System.out.println(member.getName());
            }
        });

    }

    @Test
    @DisplayName("OneToMany 즉시 로딩일 경우 모든 팀 가져오기.")
    void getAllTeamsWhenEager() {
        List<Team> allTeam = teamRepository.findAll();

        for (Team team : allTeam) {
            System.out.println(team.getName());
        }
    }

    @Test
    @DisplayName("OneToMany 지연 로딩일 경우 팀 - 멤버들 출력하기")
    @Transactional
    void getAllTeamsWithMembersWhenLazy() {
        List<Team> allTeams = teamRepository.findAll();

        for (Team team : allTeams) {
            for (Member member : team.getMembers()) {
                System.out.println("Team : " + team.getName() + " / Member : " + member.getName());
            }
        }
    }

    @Test
    @DisplayName("fetchJoin을 이용해서 팀의 멤버들 이름을 가져옴")
    @Transactional
    void getAllTeamsWithFetchJoin() {
        List<Team> allTeams = teamRepository.findAllJoinFetch();

        for (Team team : allTeams) {
            for (Member member : team.getMembers()) {
                System.out.println("Team : " + team.getName() + " / Member : " + member.getName());
            }
        }
    }

    @Test
    @DisplayName("EntityGraph을 이용해서 팀의 멤버들 이름을 가져옴")
    @Transactional
    void getAllTeamsWithEntityGraph() {
        List<Team> allTeams = teamRepository.findAllByEntityGraph();

        for (Team team : allTeams) {
            for (Member member : team.getMembers()) {
                System.out.println("Team : " + team.getName() + " / Member : " + member.getName());
            }
        }
    }
}
