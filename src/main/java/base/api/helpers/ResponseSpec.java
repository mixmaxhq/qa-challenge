package base.api.helpers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResponseSpec {
    final Logger responseSpecLogger = LogManager.getLogger(ResponseSpec.class);
    public ResponseSpecification getResponseSpecification() {
        responseSpecLogger.info("Verify Response Code is equal to 200 and return the response");
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.URI)
                .log(LogDetail.BODY)
                .build();
    }
    public ResponseSpecification postResponseSpecification() {
        responseSpecLogger.info("Verify Response Code is equal to 201 and return the response");
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .log(LogDetail.URI)
                .log(LogDetail.BODY)
                .build();
    }
    public ResponseSpecification putResponseSpecification() {
        responseSpecLogger.info("Verify Response Code is equal to 200 and return the response");
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.URI)
                .log(LogDetail.BODY)
                .build();
    }
}
