package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// assertions static import
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("FAMILY 등급은 10% 할인이 적용되어야 한다.")
    void family_o(){
        //given
        Member member = new Member(1L,"memberFamily", Grade.FAMILY);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Family 등급이 아니면 할인이 적용되지 않아야 한다")
    void family_x(){
        // given
        Member member = new Member(2L,"memberAcquaintance",Grade.ACQUAINTANCE);
        // when
        int discount = discountPolicy.discount(member,10000);
        // then
        assertThat(discount).isEqualTo(0);

    }
}