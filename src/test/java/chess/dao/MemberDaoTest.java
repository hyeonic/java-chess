package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDaoTest {

    @DisplayName("connection 테스트")
    @Test
    void connection() {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();

        assertThat(connection).isNotNull();
    }

    @DisplayName("save")
    @Test
    void save() {
        final MemberDao memberDao = new MemberDao();

        memberDao.save(new Member("mat", "최기현"));
    }

    @DisplayName("find")
    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();

        final Member member = memberDao.findById("mat");
        System.out.println(member);

        assertThat(member.getName()).isEqualTo("최기현");
    }

    @DisplayName("find")
    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();

        final Member member = memberDao.findWithRoleById("mat");
        System.out.println(member);

        assertThat(member.getName()).isEqualTo("최기현");
    }

    @DisplayName("findAll")
    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();

        final List<Member> members = memberDao.findAll();
        members.forEach(System.out::println);

        assertThat(members).isNotEmpty();
    }
}