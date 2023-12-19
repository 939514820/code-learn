package com.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/promethues/")
@Slf4j
public class PrometheusTestController1 {

    // Counter是计数器从0增加
    private final Counter totalCallsCounter;
    private final Counter successfulCallsCounter;

    // Gauge是仪表数，一个即时数值
    private final Gauge successDoubleRateGauge;

    @Autowired
    public PrometheusTestController1(MeterRegistry meterRegistry) {
        totalCallsCounter = Counter.builder("custom_total_calls")
                .description("Custom total calls counter")
                .register(meterRegistry);

        successfulCallsCounter = Counter.builder("custom_successful_calls")
                .description("Custom successful calls counter")
                .register(meterRegistry);

        successDoubleRateGauge = Gauge.builder("custom_double_success_rate_gague", this, PrometheusTestController1::calculateSuccessRate)
                .description("Custom success rate gauge")
                .register(meterRegistry);
    }

    public void recordCall(boolean isSuccess) {
        totalCallsCounter.increment();
        if (isSuccess) {
            successfulCallsCounter.increment();
        }
    }

    private double calculateSuccessRate() {
        double totalCalls = totalCallsCounter.count();
        double successfulCalls = successfulCallsCounter.count();
        if (totalCalls == 0) {
            return 0;
        }
        return (successfulCalls / totalCalls) * 100;
    }


    @GetMapping("getRate")
    public void getRate(@RequestParam Integer flag) {
        System.out.println(Thread.currentThread().getName());
        if (flag == 1) {
            recordCall(true);
        } else {
            recordCall(false);
        }
    }

}

