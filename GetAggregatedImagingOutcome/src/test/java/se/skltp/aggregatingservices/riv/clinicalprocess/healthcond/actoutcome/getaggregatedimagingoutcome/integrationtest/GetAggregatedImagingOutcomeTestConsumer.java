package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome.integrationtest;

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcome.v1.rivtabp21.GetImagingOutcomeResponderInterface;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome.GetAggregatedImagingOutcomeMuleServer;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;

public class GetAggregatedImagingOutcomeTestConsumer extends AbstractTestConsumer<GetImagingOutcomeResponderInterface> {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedImagingOutcomeTestConsumer.class);

	public static void main(String[] args) {
		String serviceAddress = GetAggregatedImagingOutcomeMuleServer.getAddress("SERVICE_INBOUND_URL");
		String personnummer = TEST_RR_ID_ONE_HIT;

		GetAggregatedImagingOutcomeTestConsumer consumer = new GetAggregatedImagingOutcomeTestConsumer(serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID);
		Holder<GetImagingOutcomeResponseType> responseHolder = new Holder<GetImagingOutcomeResponseType>();
		Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

		consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		log.info("Returned #timeslots = " + responseHolder.value.getRequestActivity().size());

		*/
        // TODO: CHANGE GENERATED SAMPLE CODE - END

	}

	public GetAggregatedImagingOutcomeTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId) {
	    
		// Setup a web service proxy for communication using HTTPS with Mutual Authentication
		super(GetImagingOutcomeResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId);
	}

	public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetImagingOutcomeResponseType> responseHolder) {

		log.debug("Calling GetImagingOutcome-soap-service with Registered Resident Id = {}", registeredResidentId);
		
		GetImagingOutcomeType request = new GetImagingOutcomeType();


        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		request.setSubjectOfCareId(registeredResidentId);

        */
        // TODO: CHANGE GENERATED SAMPLE CODE - END

		GetImagingOutcomeResponseType response = _service.getImagingOutcome(logicalAddress, request);
		responseHolder.value = response;
		
		processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
	}
}