package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        // 인터페이스 말고 구체 타입(MemberServiceImpl)으로 조회해도 조회는 된다. 하지만 "구체 타입으로 조회하면 변경시 유연성이 떨어진다."
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패 테스트 케이스도 항상 만들어야 한다.
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findByNameX(){
        // 없는 이름으로 조회하면, 에러 발생
//        MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        // 여기서 assertThrows는 Junit에 있는 라이브러리
        // -> 오른쪽에 있는 로직을 실행하면 왼쪽에 있는 에러(NoSuchBeanDefinitionException)가 터져야 한다는 뜻
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }
}
