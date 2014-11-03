package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.ObjectFactory;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.ResponseListFactory;

public class ResponseListFactoryImpl implements ResponseListFactory {

	private static final Logger log = LoggerFactory.getLogger(ResponseListFactoryImpl.class);
	private static final JaxbUtil jaxbUtil = new JaxbUtil(GetImagingOutcomeResponseType.class, ProcessingStatusType.class);
	private static final ObjectFactory OF = new ObjectFactory();
	
	@Override
	public String getXmlFromAggregatedResponse(QueryObject queryObject, List<Object> aggregatedResponseList) {
		final GetImagingOutcomeResponseType aggregatedResponse = new GetImagingOutcomeResponseType();

	    for (Object object : aggregatedResponseList) {
	    	final GetImagingOutcomeResponseType response = (GetImagingOutcomeResponseType)object;
	    	aggregatedResponse.getImagingOutcome().addAll(response.getImagingOutcome());
		}

	    if (log.isInfoEnabled()) {
    		String subjectOfCareId = queryObject.getFindContent().getRegisteredResidentIdentification();
        	log.info("Returning {} aggregated remisstatus for subject of care id {}", aggregatedResponse.getImagingOutcome().size() ,subjectOfCareId);
        }

        // Since the class GetImagingOutcomeResponseType don't have an @XmlRootElement annotation
        // we need to use the ObjectFactory to add it.
        return jaxbUtil.marshal(OF.createGetImagingOutcomeResponse(aggregatedResponse));
	}
}