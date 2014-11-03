package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;
import org.w3c.dom.Node;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.QueryObjectFactory;

public class QueryObjectFactoryImpl implements QueryObjectFactory {

	private static final Logger log = LoggerFactory.getLogger(QueryObjectFactoryImpl.class);
	private static final JaxbUtil ju = new JaxbUtil(GetImagingOutcomeType.class);

	private String eiServiceDomain;
	public void setEiServiceDomain(String eiServiceDomain) {
		this.eiServiceDomain = eiServiceDomain;
	}

	@SuppressWarnings("unused")
	private String eiCategorization;
	public void setEiCategorization(String eiCategorization) {
		this.eiCategorization = eiCategorization;
	}

	/**
	 * Transformerar GetImagingOutcome request till EI FindContent request enligt:
	 * 
	 * 1. subjectOfCareId --> registeredResidentIdentification
	 * 2. "riv:clinicalprocess:healthcond:actoutcome" --> serviceDomain
	 * 3. "und-bdi-ure" --> categorization
	 * 4. SourceSystemHSAId --> LogicalAddress
	 * 5. SourceSystemHSAId --> SourceSystem
	 */
	public QueryObject createQueryObject(Node node) {
		final GetImagingOutcomeType request = (GetImagingOutcomeType)ju.unmarshal(node);
		
		if(log.isDebugEnabled() && request.getPatientId() != null) {
			log.debug("Transformed payload for pid: {}", request.getPatientId().getId());
		}
		
		final FindContentType fc = new FindContentType();
		if(request.getPatientId() != null) {
			fc.setRegisteredResidentIdentification(request.getPatientId().getId());
		}
		fc.setServiceDomain(eiServiceDomain);
		fc.setCategorization(eiCategorization);
		fc.setLogicalAddress(request.getSourceSystemHSAId());
		fc.setSourceSystem(request.getSourceSystemHSAId());
		
		return new QueryObject(fc, request);
	}
}
