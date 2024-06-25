package br.xksoberbado;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import java.time.LocalDateTime;

/**
 * Azure Functions with Timer trigger.
 */

public class TimerTriggerExample {

    @FunctionName("TimerTriggerExample")
    public void run(@TimerTrigger(name = "timerInfo", schedule = "0 * * * * *") final String timerInfo,
                    final ExecutionContext context) {
        context.getLogger().info("ENV: " + System.getenv("MY_ENV"));
        context.getLogger().info("Executing at: " + LocalDateTime.now());
    }
}
