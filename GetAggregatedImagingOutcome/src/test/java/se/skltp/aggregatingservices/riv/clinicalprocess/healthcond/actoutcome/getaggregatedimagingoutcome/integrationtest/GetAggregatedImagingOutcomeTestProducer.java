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
		final Object response = testDb.processRequest(logicalAddress, request.getPatientId().getId());
		if(response == null) {
			return new GetImagingOutcomeResponseType();
		}
        return (GetImagingOutcomeResponseType) response;
	}
}