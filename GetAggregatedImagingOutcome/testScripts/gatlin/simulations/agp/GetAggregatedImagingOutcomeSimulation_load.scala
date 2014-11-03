package agp

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._

class GetAggregatedReferralOutcomeSimulation_load extends Simulation {

  val testTimeSecs   = 120
  val noOfUsers      = 10
  val rampUpTimeSecs = 10
        val minWaitMs      = 2000 milliseconds
  val maxWaitMs      = 5000 milliseconds

  // System under test
  val _baseURL        = "http://33.33.33.33:8088"
  val _contextPath    = "/GetAggregatedReferralOutcome/service/v3"



  val httpConf = httpConfig
    .baseURL(_baseURL)
    .disableResponseChunksDiscarding

  val headers = Map(
    "Accept-Encoding" -> "gzip,deflate",
    "Content-Type" -> "text/xml;charset=UTF-8",
    "SOAPAction" -> "urn:riv:clinicalprocess:healthcond:actoutcome:GetReferralOutcomeResponder:3:GetReferralOutcome",
    "x-vp-sender-id" -> "sid",
    "x-rivta-original-serviceconsumer-hsaid" -> "TP",
                "Keep-Alive" -> "115")


  val scn = scenario("GetAggregatedReferralOutcome")
    .during(testTimeSecs) {
      feed(csv("patients.csv").random)
      .exec(
        http("GetAggregatedReferralOutcome ${patientid} - ${name}")
          .post(_contextPath)
          .headers(headers)
          .fileBody("GetReferralOutcome_request", Map("patientId" -> "${patientid}")).asXML
          .check(status.is(session => session.getTypedAttribute[String]("status").toInt))
          .check(xpath("soap:Envelope", List("soap" -> "http://schemas.xmlsoap.org/soap/envelope/")).exists)
          .check(xpath("//*[local-name()='referralOutcome']",
                         List("ns2" -> "urn:riv:clinicalprocess:healthcond:actoutcome:GetReferralOutcomeResponder:3")).count.is(session => session.getTypedAttribute[String]("count").toInt))
      )
      .pause(minWaitMs, maxWaitMs)
    }

  setUp(scn.users(noOfUsers).ramp(rampUpTimeSecs).protocolConfig(httpConf))

}
