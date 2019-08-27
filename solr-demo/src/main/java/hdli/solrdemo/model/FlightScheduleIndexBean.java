package hdli.solrdemo.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class FlightScheduleIndexBean {

    @Field
    private String serialNumber;

    @Field
    private Date flightDate;

    @Field
    private String flightType;

    @Field
    private String flightNo;

    @Field
    private String departAirport;

    @Field
    private String arriveAirport;

    @Field
    private String startCityName;

    @Field
    private String endCityName;

    @Field
    private String planDepartureTime;

    @Field
    private String planArrivalTime;
}
