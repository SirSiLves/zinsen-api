package me.ruosch.zinsen.core;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.TelemetryConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelemetryClientConfig {

    @Value("${azure.application-insights.name}")
    private String roleName;

    @Value("${azure.application-insights.instrumentation-key}")
    private String instrumentationKey;

    @Bean
    public TelemetryClient telemetryClient() {
        return new TelemetryClient();
    }

    @Bean
    public TelemetryConfiguration telemetryConfiguration() {
        TelemetryConfiguration telemetryConfiguration = TelemetryConfiguration.getActive();
        telemetryConfiguration.setInstrumentationKey(instrumentationKey);
        telemetryConfiguration.setRoleName(roleName);

        return telemetryConfiguration;
    }



}
