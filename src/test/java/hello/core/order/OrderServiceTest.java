package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;


class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();--> appConfig 의 등장으로 이런식으로 코드는 NO
//    OrderService orderService = new OrderServiceImpl();--> appConfig 의 등장으로 이런식으로 코드는 NO

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        // long 으로 하면 null 값이 안 들어가서 Long wrapper 타입으로 쓰는 것이다.
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.FAMILY);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}