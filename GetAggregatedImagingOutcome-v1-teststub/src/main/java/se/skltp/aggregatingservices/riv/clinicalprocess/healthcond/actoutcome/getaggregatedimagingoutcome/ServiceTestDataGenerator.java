package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import lombok.extern.log4j.Log4j2;
import riv.clinicalprocess.healthcond.actoutcome.enums.v3.TypeOfResultCodeEnum;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeType;
import riv.clinicalprocess.healthcond.actoutcome.v3.CVType;
import riv.clinicalprocess.healthcond.actoutcome.v3.HealthcareProfessionalType;
import riv.clinicalprocess.healthcond.actoutcome.v3.IIType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ImageRecordingType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ImagingBodyType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ImagingOutcomeType;
import riv.clinicalprocess.healthcond.actoutcome.v3.OrgUnitType;
import riv.clinicalprocess.healthcond.actoutcome.v3.PatientSummaryHeaderType;
import riv.clinicalprocess.healthcond.actoutcome.v3.PersonIdType;
import riv.clinicalprocess.healthcond.actoutcome.v3.TimePeriodType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Service;
import se.skltp.aggregatingservices.data.TestDataGenerator;

@Log4j2
@Service
public class ServiceTestDataGenerator extends TestDataGenerator {

	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");

	@Override
	public String getPatientId(MessageContentsList messageContentsList) {
		GetImagingOutcomeType request = (GetImagingOutcomeType) messageContentsList.get(1);
		String patientId = request.getPatientId().getId();
		return patientId;
	}

	@Override
	public Object createResponse(Object... responseItems) {
		log.info("Creating a response with {} items", responseItems.length);
		GetImagingOutcomeResponseType response = new GetImagingOutcomeResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getImagingOutcome().add((ImagingOutcomeType) responseItems[i]);
		}

		log.info("response.toString:" + response.toString());

		return response;
	}

	@Override
	public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {
		log.debug("Created ResponseItem for logical-address {}, registeredResidentId {} and businessObjectId {}",
				new Object[]{logicalAddress, registeredResidentId, businessObjectId});

		final ImagingOutcomeType type = new ImagingOutcomeType();
		type.setImagingOutcomeHeader(header(registeredResidentId, logicalAddress));
		type.setImagingOutcomeBody(body());
		
		return type;
	}

	public Object createRequest(String patientId, String sourceSystemHSAId){
		GetImagingOutcomeType request = new GetImagingOutcomeType();
		final PersonIdType personId = new PersonIdType();
		personId.setType("1.2.752.129.2.1.3.1");
		personId.setId(patientId);
		request.setPatientId(personId);
		request.setSourceSystemHSAId(sourceSystemHSAId);

		return request;
	}
	
	protected PatientSummaryHeaderType header(final String registeredResidentId, final String logicalAddress) {
		final PatientSummaryHeaderType type = new PatientSummaryHeaderType();
		type.setDocumentId(UUID.randomUUID().toString());
		type.setSourceSystemHSAId(logicalAddress);
		type.setPatientId(personId(registeredResidentId));
		type.setAccountableHealthcareProfessional(hp());
		type.setApprovedForPatient(true);
		return type;
	}
	
	protected ImagingBodyType body() {
		final ImagingBodyType type = new ImagingBodyType();
		type.setTypeOfResult(TypeOfResultCodeEnum.DEF);
		type.setResultTime(df.format(LocalDateTime.now()));
		type.setResultReport("Result report");
		type.getImageRecording().add(recording());
		return type;
	}
	
	protected ImageRecordingType recording() {
		final ImageRecordingType type = new ImageRecordingType();
		type.setRecordingId(iiType());
		type.setExaminationActivity(cvType("E","1.273.123.1.1.1"));
		type.setExaminationTimePeriod(timePeriod());
		return type;
	}
	
	protected TimePeriodType timePeriod() {
		final TimePeriodType type = new TimePeriodType();
		type.setEnd(df.format(LocalDateTime.now()));
		type.setStart(df.format(LocalDateTime.now()));
		return type;
	}
	
	protected CVType cvType(final String code, final String codeSystem) {
		final CVType type = new CVType();
		type.setCode(code);
		type.setCodeSystem(codeSystem);
		return type;
	}
	
	protected IIType iiType() {
		final IIType ii = new IIType();
		ii.setExtension("EDF123");
		ii.setRoot("1.2.752.129.2.2.1.4");
		return ii;
	}
	
	protected PersonIdType personId(final String registeredResidentId) {
		final PersonIdType type = new PersonIdType();
		type.setId(registeredResidentId);
		type.setType("1.2.752.129.2.1.3.1");
		return type;
	}
	
	protected HealthcareProfessionalType hp() {
		final HealthcareProfessionalType type = new HealthcareProfessionalType();
		type.setAuthorTime(df.format(LocalDateTime.now()));
		type.setHealthcareProfessionalCareGiverHSAId("CareGiverHSAId");
		type.setHealthcareProfessionalCareUnitHSAId("CareUnitHSAId");
		type.setHealthcareProfessionalHSAId("HSAId");
		type.setHealthcareProfessionalName("Test Testsson");
		type.setHealthcareProfessionalOrgUnit(orgUnit());
		return type;
	}
	
	protected OrgUnitType orgUnit() {
		final OrgUnitType type = new OrgUnitType();
		type.setOrgUnitAddress("Sjukhusvagen 32");
		type.setOrgUnitEmail("test@test.se");
		type.setOrgUnitHSAId("HSAId");
		type.setOrgUnitLocation("Stocklhom");
		type.setOrgUnitName("Sjukhuset");
		type.setOrgUnitTelecom("080000000");
		return type;
	}
}
