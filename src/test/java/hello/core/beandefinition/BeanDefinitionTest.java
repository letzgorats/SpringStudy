package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class BeanDefinitionTest {

    // java code - annotation : bean 을 뽑아보면 직접적으로 드러나는 게 아니라 factoryBeanName과 실제 메서드 factoryMethodName 을 통해서 알 수 있다.
    AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(AppConfig.class);


    // xml : xml 에서는 bean 이 직접적으로 클래스가 드러난다.
    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    // 예전에 xml 을 사용했을 때는 이랬고, 요즘은 뭐 거의 다 annotation을 사용하니까 그냥 차이 정도만 참고하자.

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + "beanDefinition = " + beanDefinition);
            }
        }
    }
}
