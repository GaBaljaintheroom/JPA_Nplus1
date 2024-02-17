package com.example.jpafetch;

import com.example.jpafetch.dto.TeamsDto;
import com.example.jpafetch.entity.Member;
import com.example.jpafetch.entity.Sponsor;
import com.example.jpafetch.entity.Team;
import com.example.jpafetch.repository.TeamQueryRepository;
import com.example.jpafetch.repository.TeamRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoubleFetchJoinTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamQueryRepository teamQueryRepository;

    @Test
    @DisplayName("Team 하나를 조회하는데 FetchJoin을 이용하여 Member와 Sponsor를 출력한다.")
    void getTeamWithMemberAndSponsorByFetchJoin() {
        Team team = teamRepository.findTeamWithMembersAndSponsorsByJoinFetch(1L)
            .orElseThrow(NullPointerException::new);

        System.out.println(team.getName());
        List<Member> members = team.getMembers();
        for (Member member : members) {
            System.out.println(member.getName());
        }

        List<Sponsor> sponsors = team.getSponsors();
        for (Sponsor sponsor : sponsors) {
            System.out.println(sponsor.getName());
        }
    }

    @Test
    @DisplayName("Team을 조회하는데 Left Join을 이용하여 각 팀의 Member와 Sponsor를 출력한다.")
    void getTeamWithMemberAndSponsorByLeftJoin() {
        TeamsDto dto = teamRepository.findTeamWithMembersAndSponsorsByLeftJoin(1L);

        System.out.println("Team " + dto.teamName() +
            " Member : " + dto.memberName() +
            " Sponsor : " + dto.sponsorName()
        );
    }

    @Test
    @DisplayName("Team을 조회하는데 Left Join과 group_concat을 이용하여 각 팀의 Member와 Sponsor를 출력한다.")
    void getTeamWithMemberAndSponsorByLeftJoinAndGroupConcat() {
        TeamsDto dto = teamQueryRepository.findTeamWithMembersAndSponsorsByLeftJoinAndGroupConcat(
            1L).orElseThrow(NullPointerException::new);

        System.out.println("Team " + dto.teamName() +
            " Member : " + dto.memberName() +
            " Sponsor : " + dto.sponsorName()
        );
    }
}
