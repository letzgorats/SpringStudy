package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// DIP 위반 문제를 Interface 뿐만 아니라 구현체에도 의존하는 DIP 문제를 해결했는데, 변경했기 때문에 OCP 문제가 생김.
// 이를 해결하기 위해 AppConfig 클래스 생성 -> 애플리케이션에 대한 환경 구성에 대한 것을 여기에 다 하는 것이다.-> OCP 해결
// AppConfig 는 외부 기획자라고 생각하면 된다.

@Configuration
public class AppConfig {

    @Bean
    // memberService 역할
    public MemberService memberService() {
        // 생성자 주입을 통해 객체 지향 완성
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    // memberRepository 역할
    private static MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    // order 서비스 역할
    public OrderService orderService(){

        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

    @Bean
    // discountPolicy 역할
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        // 정액 정책 --> 정률 정책으로 변경
        return new RateDiscountPolicy();
    }

}
