package com.tanx.music.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/5 下午2:24
 */
@Configuration
public class CurrentEnvironment implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return "prod".equals(conditionContext.getEnvironment().getProperty("spring.profiles.active"));
    }
}
