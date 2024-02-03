package com.example.jpafetch;

import com.example.jpafetch.entity.Member;
import com.example.jpafetch.entity.Team;
import com.example.jpafetch.repository.MemberRepository;
import com.example.jpafetch.repository.TeamRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("OneToMany 지연 로딩일 경우 한 팀 중 멤버 이름 가져오기.")
    void getAllTeamsWhenLazy() {
        List<Team> allTeam = teamRepository.findAll();

        List<Member> members = allTeam.get(0).getMembers();

        for (Member member : members) {
            System.out.println(member.getName());
        }
    }


}
