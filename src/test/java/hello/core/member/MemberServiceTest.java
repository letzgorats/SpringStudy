package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();--> appConfig 의 등장으로 이런식으로 코드는 NO
    MemberService memberService;

    // test 실행 되기 전에 무조건 실행되는 BeforEach
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join(){

        // given -- 주어진 것
        Member member = new Member(1L,"memberA",Grade.FAMILY);
        // when  -- 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then  -- 이렇게 된다.
        // Spring 테스트에서 Assertions을 자주 이용하므로 추가 학습후 테스트 주도개발에 잘 사용해야 한다.
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
