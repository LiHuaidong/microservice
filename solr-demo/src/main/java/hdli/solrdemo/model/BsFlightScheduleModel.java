package hdli.solrdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class BsFlightScheduleModel {

    private Long id;

    private String bsfsSerialNumber;

    private Date bsfsUpdateDate;

    private String bsfsFlightType;

    private String bsfsAirlinecode;

    private String bsfsTransportname;

    private String bsfsStartstationCode;

    private String bsfsEndstationCode;

    private String bsfsStartcityName;

    private String bsfsEndcityName;

    private String bsfsDepartureTime;

    private String bsfsArrivalTime;

    private String bsfsStopSighn;

    private String bsfsShareSighn;

    private String bsfsSchedule;

    private String bsfsAircrfttype;

    private String bsfsMileage;

    private String bsfsFlightMileage;

    private String bsfsPunctualityRate;

    private Date bsfsEffectiveDate;

    private Date bsfsExpirationDate;

    private String bsfsIshotroute;

    private Integer recStatus;

    private Integer recVer;

    private String creator;

    private String createName;

    private Date createTime;

    private String modifier;

    private String modifyName;

    private Date modifyTime;

    private String substr1;

    private String substr2;

    private BigDecimal subdecima1;

    private BigDecimal subdecima2;

    private Date subdate1;

    private Date subdate2;

    private String bsfsStatusZh;

    private String bsfsStatusEn;
}
