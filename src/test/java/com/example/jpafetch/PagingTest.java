package com.example.jpafetch;

import com.example.jpafetch.entity.Member;
import com.example.jpafetch.entity.Team;
import com.example.jpafetch.repository.MemberRepository;
import com.example.jpafetch.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class PagingTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("1대N 관계에서 1인 Team을 fetch join과 페이징을 해서 가져온다. ")
    void getAllTeamsJoinFetchWithPaging() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Team> teams = teamRepository.findAllJoinFetchWithPaging(pageRequest);

        for (Team team : teams) {
            for (Member member : team.getMembers()) {
                System.out.println("Team : " + team.getName() + " / Member : " + member.getName());
            }
        }
    }

    @Test
    @DisplayName("1대N 관계에서 N인 Member을 fetch join과 페이징을 해서 가져온다.")
    void getAllMembersJoinFetchWithPaging() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.findAllJoinFetchWithPaging(pageRequest);

        for (Member member : members) {
            System.out.println(
                "Team : " + member.getTeam().getName() + " / Member : " + member.getName());
        }
    }


}
