package org.aery.practice.order.utils.mark.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalNotTestProfile.NotTestProfileCondition.class)
public @interface ConditionalNotTestProfile {

    class NotTestProfileCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Environment env = context.getEnvironment();
            String[] activeProfiles = env.getActiveProfiles();

            for (String activeProfile : activeProfiles) {
                if ("test".equals(activeProfile)) {
                    return ConditionOutcome.noMatch("active profiles contains 'test'");
                }
            }

            return ConditionOutcome.match("active profiles no contains 'test'");
        }

    }

}
