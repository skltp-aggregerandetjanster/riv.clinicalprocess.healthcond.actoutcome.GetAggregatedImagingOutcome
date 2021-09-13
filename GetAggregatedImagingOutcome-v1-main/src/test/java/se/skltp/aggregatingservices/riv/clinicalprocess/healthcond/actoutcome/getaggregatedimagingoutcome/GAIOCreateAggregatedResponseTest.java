package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;


import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateAggregatedResponseTest;
import se.skltp.aggregatingservices.data.TestDataGenerator;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GAIOCreateAggregatedResponseTest extends CreateAggregatedResponseTest {

  private static GAIOAgpServiceConfiguration configuration = new GAIOAgpServiceConfiguration();
  private static AgpServiceFactory<GetImagingOutcomeResponseType> agpServiceFactory = new GAIOAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GAIOCreateAggregatedResponseTest() {
      super(testDataGenerator, agpServiceFactory, configuration);
  }

  @Override
  public int getResponseSize(Object response) {
        GetImagingOutcomeResponseType responseType = (GetImagingOutcomeResponseType)response;
    return responseType.getImagingOutcome().size();
  }
}