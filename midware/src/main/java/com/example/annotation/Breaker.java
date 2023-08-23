package com.example.annotation;

public @interface Breaker {
    long timeout() default 3000;

    String returnJson() default "";
}
