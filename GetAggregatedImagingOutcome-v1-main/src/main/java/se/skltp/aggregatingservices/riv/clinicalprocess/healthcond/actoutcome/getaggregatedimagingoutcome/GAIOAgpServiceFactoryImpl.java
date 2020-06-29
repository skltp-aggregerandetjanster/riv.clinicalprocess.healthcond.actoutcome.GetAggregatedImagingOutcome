package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import riv.clinicalprocess.healthcond.actoutcome.enums.v3.ResultCodeEnum;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeResponseType;
import riv.clinicalprocess.healthcond.actoutcome.getimagingoutcomeresponder.v1.GetImagingOutcomeType;
import riv.clinicalprocess.healthcond.actoutcome.v3.ResultType;
import se.skltp.aggregatingservices.AgServiceFactoryBase;

@Log4j2
public class GAIOAgpServiceFactoryImpl extends
    AgServiceFactoryBase<GetImagingOutcomeType, GetImagingOutcomeResponseType>{

@Override
public String getPatientId(GetImagingOutcomeType queryObject){
    return queryObject.getPatientId().getId();
    }

@Override
public String getSourceSystemHsaId(GetImagingOutcomeType queryObject){
    return queryObject.getPatientId().getId();
    }

@Override
public GetImagingOutcomeResponseType aggregateResponse(List<GetImagingOutcomeResponseType> aggregatedResponseList ){

    GetImagingOutcomeResponseType aggregatedResponse=new GetImagingOutcomeResponseType();

    for (Object object : aggregatedResponseList) {
    	final GetImagingOutcomeResponseType response = (GetImagingOutcomeResponseType)object;
    	aggregatedResponse.getImagingOutcome().addAll(response.getImagingOutcome());
	}
    
    aggregatedResponse.setResult(new ResultType());
    aggregatedResponse.getResult().setLogId("NA");
    aggregatedResponse.getResult().setResultCode(ResultCodeEnum.INFO);

    return aggregatedResponse;
    }
}

