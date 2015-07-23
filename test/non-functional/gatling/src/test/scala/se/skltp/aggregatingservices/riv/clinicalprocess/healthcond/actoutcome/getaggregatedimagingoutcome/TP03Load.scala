package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome

import se.skltp.agp.testnonfunctional.TP03LoadAbstract

/**
 * Load test VP:GetAggregatedImagingOutcome.
 */
class TP03Load extends TP03LoadAbstract with CommonParameters {
 // override skltp-box with qa
  if (baseUrl.startsWith("http://33.33.33.33")) {
      baseUrl = "http://ine-sit-app03.sth.basefarm.net:9012/GetAggregatedImagingOutcome/service/v1"
//    baseUrl = "http://ine-dit-app02.sth.basefarm.net:9012/GetAggregatedImagingOutcome/service/v1"
  }
  setUp(setUpAbstract(serviceName, urn, responseElement, responseItem, baseUrl))
}
