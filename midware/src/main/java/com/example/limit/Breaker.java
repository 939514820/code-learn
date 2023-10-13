package com.example.limit;

public @interface Breaker {
    long timeout() default 3000;

    String returnJson() default "";
}
