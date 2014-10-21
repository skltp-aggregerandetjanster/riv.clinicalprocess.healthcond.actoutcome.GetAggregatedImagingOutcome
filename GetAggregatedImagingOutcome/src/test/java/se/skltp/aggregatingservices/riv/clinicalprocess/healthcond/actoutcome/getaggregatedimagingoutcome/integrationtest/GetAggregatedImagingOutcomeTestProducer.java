package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome.integrationtest;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetImagingOutcomeResponderService", portName = "GetImagingOutcomeResponderPort", targetNamespace = "urn:riv:clinicalprocess:healthcond:actoutcome:GetImagingOutcome:1:rivtabp21", name = "GetImagingOutcomeInteraction")
public class GetAggregatedImagingOutcomeTestProducer implements GetImagingOutcomeResponderInterface {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedImagingOutcomeTestProducer.class);

	private TestProducerDb testDb;
	public void setTestDb(TestProducerDb testDb) {
		this.testDb = testDb;
	}

	public GetImagingOutcomeResponseType getImagingOutcome(String logicalAddress, GetImagingOutcomeType request) {
		GetImagingOutcomeResponseType response = null;


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		log.info("### Virtual service for GetImagingOutcome call the source system with logical address: {} and patientId: {}", logicalAddress, request.getSubjectOfCareId());

		response = (GetImagingOutcomeResponseType)testDb.processRequest(logicalAddress, request.getSubjectOfCareId());
        if (response == null) {
        	// Return an empty response object instead of null if nothing is found
        	response = new GetImagingOutcomeResponseType();
        }

		log.info("### Virtual service got {} booknings in the reply from the source system with logical address: {} and patientId: {}", new Object[] {response.getRequestActivity().size(), logicalAddress, request.getSubjectOfCareId()});

        */
        // TODO: CHANGE GENERATED SAMPLE CODE - END


		// We are done
        return response;
	}
}