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
        telemetryConfiguration.setConnectionString("InstrumentationKey=37402c98-17e2-4f2c-a6ac-0355e698ff34;IngestionEndpoint=https://westeurope-5.in.applicationinsights.azure.com/");
        telemetryConfiguration.setRoleName(roleName);

        return telemetryConfiguration;
    }



}
