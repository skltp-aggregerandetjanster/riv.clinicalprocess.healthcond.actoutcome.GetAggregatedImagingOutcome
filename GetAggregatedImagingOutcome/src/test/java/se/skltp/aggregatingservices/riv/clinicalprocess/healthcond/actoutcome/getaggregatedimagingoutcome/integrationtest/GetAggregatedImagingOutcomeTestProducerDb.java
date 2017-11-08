/**
 * Copyright (c) 2014 Inera AB, <http://inera.se/>
 *
 * This file is part of SKLTP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome.integrationtest;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.util.ThreadSafeSimpleDateFormat;

import riv.clinicalprocess.healthcond.actoutcome.enums.v3.TypeOfResultCodeEnum;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
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
import se.skltp.agp.test.producer.TestProducerDb;

public class GetAggregatedImagingOutcomeTestProducerDb extends TestProducerDb {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedImagingOutcomeTestProducerDb.class);
	private static final ThreadSafeSimpleDateFormat df = new ThreadSafeSimpleDateFormat("yyyyMMddhhmmss");

	@Override
	public Object createResponse(Object... responseItems) {
		log.debug("Creates a response with {} items", responseItems);
		final GetImagingOutcomeResponseType response = new GetImagingOutcomeResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getImagingOutcome().add((ImagingOutcomeType) responseItems[i]);
		}
		return response;
	}
	
	@Override
	public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {
		if (log.isDebugEnabled()) {
			log.debug("Created one response item for logical-address {}, registeredResidentId {} and businessObjectId {}",
				new Object[] {logicalAddress, registeredResidentId, businessObjectId});
		}
		
		final ImagingOutcomeType type = new ImagingOutcomeType();
		type.setImagingOutcomeHeader(header(registeredResidentId, logicalAddress));
		type.setImagingOutcomeBody(body());
		
		return type;
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
		type.setResultTime(df.format(new Date()));
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
		type.setEnd(df.format(new Date()));
		type.setStart(df.format(new Date()));
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
		type.setAuthorTime(df.format(new Date()));
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