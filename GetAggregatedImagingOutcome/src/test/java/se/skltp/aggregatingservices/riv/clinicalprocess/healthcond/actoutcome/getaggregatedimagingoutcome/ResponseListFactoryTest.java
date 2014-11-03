package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.mockito.Mockito;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;

import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ImagingBodyType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ImagingOutcomeType;
import riv.clinicalprocess.healthcond.actoutcome.v3.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.actoutcome.v3.PersonIdType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.ResponseListFactory;


public class ResponseListFactoryTest {

	private final static String SUBJECT_OF_CARE = UUID.randomUUID().toString();
	private final static int NUMBER_OF_RESPONSES = 5;
	
	private final ResponseListFactoryImpl testObject = new ResponseListFactoryImpl();
	private final List<Object> responseList = new ArrayList<Object>();
	
	private final QueryObject queryObject = Mockito.mock(QueryObject.class);
	private final FindContentType findContentType = Mockito.mock(FindContentType.class);
	
	@Before
	public void setup() {
		Mockito.when(findContentType.getRegisteredResidentIdentification()).thenReturn(SUBJECT_OF_CARE);
		Mockito.when(queryObject.getFindContent()).thenReturn(findContentType);
		
		for(int i = 0; i < NUMBER_OF_RESPONSES; i++) {
			final GetImagingOutcomeResponseType resp = new GetImagingOutcomeResponseType();
			final ImagingOutcomeType ref = new ImagingOutcomeType();
			ref.setImagingOutcomeBody(new ImagingBodyType());
			ref.setImagingOutcomeHeader(new PatientSummaryHeaderType());
			ref.getImagingOutcomeHeader().setPatientId(new PersonIdType());
			ref.getImagingOutcomeHeader().getPatientId().setId(SUBJECT_OF_CARE);
			resp.getImagingOutcome().add(ref);
			responseList.add(resp);
		}
	}

	@Test
	public void testGetXmlFromAggregatedResponse() {
		final JaxbUtil jaxbUtil = new JaxbUtil(GetImagingOutcomeResponseType.class);

		final String after = testObject.getXmlFromAggregatedResponse(queryObject, responseList);
		final GetImagingOutcomeResponseType type = (GetImagingOutcomeResponseType) jaxbUtil.unmarshal(after);
		
		assertEquals(NUMBER_OF_RESPONSES, type.getImagingOutcome().size());
		for(ImagingOutcomeType ref : type.getImagingOutcome()) {
			assertEquals(SUBJECT_OF_CARE, ref.getImagingOutcomeHeader().getPatientId().getId());
		}
	}
	
}
