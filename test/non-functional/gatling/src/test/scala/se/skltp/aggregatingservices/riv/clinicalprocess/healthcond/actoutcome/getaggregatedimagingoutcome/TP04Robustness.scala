package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.actoutcome.getaggregatedimagingoutcome

import se.skltp.agp.testnonfunctional.TP04RobustnessAbstract

/**
 * Test VP:GetAggregatedImagingOutcome over 12 hours
 */
class TP04Robustness extends TP04RobustnessAbstract with CommonParameters {
  // override skltp-box with qa
  if (baseUrl.startsWith("http://33.33.33.33")) {
      baseUrl = "http://ine-sit-app03.sth.basefarm.net:9012/GetAggregatedImagingOutcome/service/v1"
  }
  setUp(setUpAbstract(serviceName, urn, responseElement, responseItem, baseUrl))
}
