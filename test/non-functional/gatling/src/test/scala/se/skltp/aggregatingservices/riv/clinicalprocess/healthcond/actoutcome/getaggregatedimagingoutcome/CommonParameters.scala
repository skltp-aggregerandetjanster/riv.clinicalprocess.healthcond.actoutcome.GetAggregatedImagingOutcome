package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome

trait CommonParameters {
  val serviceName:String     = "ImagingOutcome"
  val urn:String             = "urn:riv:clinicalprocess:healthcond:actoutcome:GetImagingOutcomeResponder:1"
  val responseElement:String = "GetImagingOutcomeResponse"
  val responseItem:String    = "imagingOutcome"
  var baseUrl:String         = if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
                                   System.getProperty("baseUrl")
                               } else {
                                   "http://33.33.33.33:8081/GetAggregatedImagingOutcome/service/v1"
                               }
}
