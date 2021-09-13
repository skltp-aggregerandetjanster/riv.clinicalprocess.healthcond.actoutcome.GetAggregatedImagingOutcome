package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import org.junit.jupiter.api.BeforeAll;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GAIOCreateRequestListTest extends CreateFindContentTest {

  private static GAIOAgpServiceConfiguration configuration = new GAIOAgpServiceConfiguration();
  private static AgpServiceFactory<GetImagingOutcomeResponseType> agpServiceFactory = new GAIOAgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();


  public GAIOCreateRequestListTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @BeforeAll
  public static void before() {
    configuration = new GAIOAgpServiceConfiguration();
    agpServiceFactory = new GAIOAgpServiceFactoryImpl();
    agpServiceFactory.setAgpServiceConfiguration(configuration);
  }
}