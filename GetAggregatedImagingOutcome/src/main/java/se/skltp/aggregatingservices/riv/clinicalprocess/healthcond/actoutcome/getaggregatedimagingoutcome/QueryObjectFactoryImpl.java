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
	 */
	public QueryObject createQueryObject(Node node) {
		
		GetImagingOutcomeType request = (GetImagingOutcomeType)ju.unmarshal(node);
		

        // TODO: CHANGE GENERATED SAMPLE CODE - START
        if (1==1) throw new UnsupportedOperationException("Not yet implemented");
        /*

		if (log.isDebugEnabled()) log.debug("Transformed payload for pid: {}", request.getSubjectOfCareId());

		FindContentType fc = new FindContentType();		
		fc.setRegisteredResidentIdentification(request.getSubjectOfCareId());
		fc.setServiceDomain(eiServiceDomain);
		
		QueryObject qo = new QueryObject(fc, request);
		return qo;

        */
		return null;
        // TODO: CHANGE GENERATED SAMPLE CODE - END

	}
}