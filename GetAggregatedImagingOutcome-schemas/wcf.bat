@REM Licensed to the soi-toolkit project under one or more
@REM contributor license agreements.  See the NOTICE file distributed with
@REM this work for additional information regarding copyright ownership.
@REM The soi-toolkit project licenses this file to You under the Apache License, Version 2.0
@REM (the "License"); you may not use this file except in compliance with
@REM the License.  You may obtain a copy of the License at
@REM
@REM     http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
	
@REM ---------------------------------------------------------------------------------
@REM Generates c# WCF service contracts (interface), client proxies and wcf config
@REM file for the WSDLs + XML Schemas of the service domain, using .Net WCF tool svcuti.exe
@REM ---------------------------------------------------------------------------------	
	
	CD ..
	
	SET SCHEMADIR=schemas
		
	SET X0=%SCHEMADIR%/schemas/core_components/clinicalprocess_healthcond_actoutcome_3.0.xsd 	
	SET X1=%SCHEMADIR%/schemas/core_components/clinicalprocess_healthcond_actoutcome_enum_3.0.xsd 	
	SET X2=%SCHEMADIR%/schemas/core_components/itintegration_registry_1.0.xsd 	
	SET X3=%SCHEMADIR%/schemas/interactions/GetECGOutcomeInteraction/GetECGOutcomeInteraction_1.0_RIVTABP21.wsdl 	
	SET X4=%SCHEMADIR%/schemas/interactions/GetECGOutcomeInteraction/GetECGOutcomeResponder_1.0.xsd 	
	SET X5=%SCHEMADIR%/schemas/interactions/GetImagingOutcomeInteraction/GetImagingOutcomeInteraction_1.0_RIVTABP21.wsdl 	
	SET X6=%SCHEMADIR%/schemas/interactions/GetImagingOutcomeInteraction/GetImagingOutcomeResponder_1.0.xsd 	
	SET X7=%SCHEMADIR%/schemas/interactions/GetLaboratoryOrderOutcomeInteraction/GetLaboratoryOrderOutcomeInteraction_3.0_RIVTABP21.wsdl 	
	SET X8=%SCHEMADIR%/schemas/interactions/GetLaboratoryOrderOutcomeInteraction/GetLaboratoryOrderOutcomeResponder_3.0.xsd 	
	SET X9=%SCHEMADIR%/schemas/interactions/GetMaternityMedicalHistoryInteraction/GetMaternityMedicalHistoryInteraction_3.0_RIVTABP21.wsdl 	
	SET X10=%SCHEMADIR%/schemas/interactions/GetMaternityMedicalHistoryInteraction/GetMaternityMedicalHistoryResponder_3.0.xsd 	
	SET X11=%SCHEMADIR%/schemas/interactions/GetReferralOutcomeInteraction/GetReferralOutcomeInteraction_3.0_RIVTABP21.wsdl 	
	SET X12=%SCHEMADIR%/schemas/interactions/GetReferralOutcomeInteraction/GetReferralOutcomeResponder_3.0.xsd 
	SET SCHEMAS=%X0% %X1% %X2% %X3% %X4% %X5% %X6% %X7% %X8% %X9% %X10% %X11% %X12%  
	SET OUTFILE=/out:wcf/generated-src/GetAggregatedImagingOutcomeClient.cs
	SET APPCONFIG=/config:wcf/generated-src/app.config
	SET NAMESPACE=/namespace:*,GetAggregatedImagingOutcome.Schemas
	
	@REM ServiceModel Metadata Utility Tool
	SET SVCUTIL="svcutil.exe"
	%SVCUTIL% /language:cs %OUTFILE% %APPCONFIG% %NAMESPACE% %SCHEMAS%