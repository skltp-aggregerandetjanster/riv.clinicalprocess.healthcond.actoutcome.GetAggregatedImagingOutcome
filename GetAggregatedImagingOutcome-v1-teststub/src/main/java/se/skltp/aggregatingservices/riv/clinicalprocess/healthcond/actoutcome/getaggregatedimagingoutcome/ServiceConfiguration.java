package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderService;
import se.skltp.aggregatingservices.config.TestProducerConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="getaggregatedimagingoutcome.v1.teststub")
public class ServiceConfiguration extends TestProducerConfiguration {

  public static final String SCHEMA_PATH = "classpath:/schemas/TD_CLINICALPROCESS_HEALTHCOND_ACTOUTCOME_3.1/interactions/GetImagingOutcomeInteraction/GetImagingOutcomeInteraction_1.0_RIVTABP21.wsdl";

  public ServiceConfiguration() {
    setProducerAddress("http://localhost:8083/vp");
    setServiceClass(GetImagingOutcomeResponderInterface.class.getName());
    setServiceNamespace("urn:riv:clinicalprocess:healthcond:actoutcome:GetImagingOutcomeResponder:1");
    setPortName(GetImagingOutcomeResponderService.GetImagingOutcomeResponderPort.toString());
    setWsdlPath(SCHEMA_PATH);
    setTestDataGeneratorClass(ServiceTestDataGenerator.class.getName());
    setServiceTimeout(27000);
  }

}
