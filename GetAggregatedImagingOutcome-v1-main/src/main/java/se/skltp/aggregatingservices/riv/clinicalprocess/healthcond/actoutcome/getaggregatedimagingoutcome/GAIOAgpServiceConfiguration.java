package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderService;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregatedimagingoutcome.v1")
public class GAIOAgpServiceConfiguration extends se.skltp.aggregatingservices.configuration.AgpServiceConfiguration {

  public static final String SCHEMA_PATH = "classpath:/schemas/TD_CLINICALPROCESS_HEALTHCOND_ACTOUTCOME_3.1/interactions/GetImagingOutcomeInteraction/GetImagingOutcomeInteraction_1.0_RIVTABP21.wsdl";

  public GAIOAgpServiceConfiguration() {

    setServiceName("GetAggregatedImagingOutcome-v1");
    setTargetNamespace("urn:riv:clinicalprocess:healthcond:actoutcome:GetImagingOutcome:1:rivtabp21");

    // Set inbound defaults
    setInboundServiceURL("http://0.0.0.0:9012/GetAggregatedImagingOutcome/service/v1");
    setInboundServiceWsdl(SCHEMA_PATH);
    setInboundServiceClass(GetImagingOutcomeResponderInterface.class.getName());
    setInboundPortName(GetImagingOutcomeResponderService.GetImagingOutcomeResponderPort.toString());

    // Set outbound defaults
    setOutboundServiceWsdl(SCHEMA_PATH);
    setOutboundServiceClass(GetImagingOutcomeResponderInterface.class.getName());
    setOutboundPortName(GetImagingOutcomeResponderService.GetImagingOutcomeResponderPort.toString());

    // FindContent
    setEiServiceDomain("riv:clinicalprocess:healthcond:actoutcome");
    setEiCategorization("und-bdi-ure");

    // TAK
    setTakContract("urn:riv:clinicalprocess:healthcond:actoutcome:GetImagingOutcomeResponder:1");

    // Set service factory
    setServiceFactoryClass(GAIOAgpServiceFactoryImpl.class.getName());
    }


}
