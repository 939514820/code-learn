package com.example.limit.annotation;

public @interface Breaker {
    long timeout() default 3000;

    String returnJson() default "";
}
