package hdli.solrdemo.po;

import java.math.BigDecimal;
import java.util.Date;

public class BsFlightSchedule {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBsfsSerialNumber() {
        return bsfsSerialNumber;
    }

    public void setBsfsSerialNumber(String bsfsSerialNumber) {
        this.bsfsSerialNumber = bsfsSerialNumber == null ? null : bsfsSerialNumber.trim();
    }

    public Date getBsfsUpdateDate() {
        return bsfsUpdateDate;
    }

    public void setBsfsUpdateDate(Date bsfsUpdateDate) {
        this.bsfsUpdateDate = bsfsUpdateDate;
    }

    public String getBsfsFlightType() {
        return bsfsFlightType;
    }

    public void setBsfsFlightType(String bsfsFlightType) {
        this.bsfsFlightType = bsfsFlightType == null ? null : bsfsFlightType.trim();
    }

    public String getBsfsAirlinecode() {
        return bsfsAirlinecode;
    }

    public void setBsfsAirlinecode(String bsfsAirlinecode) {
        this.bsfsAirlinecode = bsfsAirlinecode == null ? null : bsfsAirlinecode.trim();
    }

    public String getBsfsTransportname() {
        return bsfsTransportname;
    }

    public void setBsfsTransportname(String bsfsTransportname) {
        this.bsfsTransportname = bsfsTransportname == null ? null : bsfsTransportname.trim();
    }

    public String getBsfsStartstationCode() {
        return bsfsStartstationCode;
    }

    public void setBsfsStartstationCode(String bsfsStartstationCode) {
        this.bsfsStartstationCode = bsfsStartstationCode == null ? null : bsfsStartstationCode.trim();
    }

    public String getBsfsEndstationCode() {
        return bsfsEndstationCode;
    }

    public void setBsfsEndstationCode(String bsfsEndstationCode) {
        this.bsfsEndstationCode = bsfsEndstationCode == null ? null : bsfsEndstationCode.trim();
    }

    public String getBsfsStartcityName() {
        return bsfsStartcityName;
    }

    public void setBsfsStartcityName(String bsfsStartcityName) {
        this.bsfsStartcityName = bsfsStartcityName == null ? null : bsfsStartcityName.trim();
    }

    public String getBsfsEndcityName() {
        return bsfsEndcityName;
    }

    public void setBsfsEndcityName(String bsfsEndcityName) {
        this.bsfsEndcityName = bsfsEndcityName == null ? null : bsfsEndcityName.trim();
    }

    public String getBsfsDepartureTime() {
        return bsfsDepartureTime;
    }

    public void setBsfsDepartureTime(String bsfsDepartureTime) {
        this.bsfsDepartureTime = bsfsDepartureTime == null ? null : bsfsDepartureTime.trim();
    }

    public String getBsfsArrivalTime() {
        return bsfsArrivalTime;
    }

    public void setBsfsArrivalTime(String bsfsArrivalTime) {
        this.bsfsArrivalTime = bsfsArrivalTime == null ? null : bsfsArrivalTime.trim();
    }

    public String getBsfsStopSighn() {
        return bsfsStopSighn;
    }

    public void setBsfsStopSighn(String bsfsStopSighn) {
        this.bsfsStopSighn = bsfsStopSighn == null ? null : bsfsStopSighn.trim();
    }

    public String getBsfsShareSighn() {
        return bsfsShareSighn;
    }

    public void setBsfsShareSighn(String bsfsShareSighn) {
        this.bsfsShareSighn = bsfsShareSighn == null ? null : bsfsShareSighn.trim();
    }

    public String getBsfsSchedule() {
        return bsfsSchedule;
    }

    public void setBsfsSchedule(String bsfsSchedule) {
        this.bsfsSchedule = bsfsSchedule == null ? null : bsfsSchedule.trim();
    }

    public String getBsfsAircrfttype() {
        return bsfsAircrfttype;
    }

    public void setBsfsAircrfttype(String bsfsAircrfttype) {
        this.bsfsAircrfttype = bsfsAircrfttype == null ? null : bsfsAircrfttype.trim();
    }

    public String getBsfsMileage() {
        return bsfsMileage;
    }

    public void setBsfsMileage(String bsfsMileage) {
        this.bsfsMileage = bsfsMileage == null ? null : bsfsMileage.trim();
    }

    public String getBsfsFlightMileage() {
        return bsfsFlightMileage;
    }

    public void setBsfsFlightMileage(String bsfsFlightMileage) {
        this.bsfsFlightMileage = bsfsFlightMileage == null ? null : bsfsFlightMileage.trim();
    }

    public String getBsfsPunctualityRate() {
        return bsfsPunctualityRate;
    }

    public void setBsfsPunctualityRate(String bsfsPunctualityRate) {
        this.bsfsPunctualityRate = bsfsPunctualityRate == null ? null : bsfsPunctualityRate.trim();
    }

    public Date getBsfsEffectiveDate() {
        return bsfsEffectiveDate;
    }

    public void setBsfsEffectiveDate(Date bsfsEffectiveDate) {
        this.bsfsEffectiveDate = bsfsEffectiveDate;
    }

    public Date getBsfsExpirationDate() {
        return bsfsExpirationDate;
    }

    public void setBsfsExpirationDate(Date bsfsExpirationDate) {
        this.bsfsExpirationDate = bsfsExpirationDate;
    }

    public String getBsfsIshotroute() {
        return bsfsIshotroute;
    }

    public void setBsfsIshotroute(String bsfsIshotroute) {
        this.bsfsIshotroute = bsfsIshotroute == null ? null : bsfsIshotroute.trim();
    }

    public Integer getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Integer recStatus) {
        this.recStatus = recStatus;
    }

    public Integer getRecVer() {
        return recVer;
    }

    public void setRecVer(Integer recVer) {
        this.recVer = recVer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName == null ? null : modifyName.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSubstr1() {
        return substr1;
    }

    public void setSubstr1(String substr1) {
        this.substr1 = substr1 == null ? null : substr1.trim();
    }

    public String getSubstr2() {
        return substr2;
    }

    public void setSubstr2(String substr2) {
        this.substr2 = substr2 == null ? null : substr2.trim();
    }

    public BigDecimal getSubdecima1() {
        return subdecima1;
    }

    public void setSubdecima1(BigDecimal subdecima1) {
        this.subdecima1 = subdecima1;
    }

    public BigDecimal getSubdecima2() {
        return subdecima2;
    }

    public void setSubdecima2(BigDecimal subdecima2) {
        this.subdecima2 = subdecima2;
    }

    public Date getSubdate1() {
        return subdate1;
    }

    public void setSubdate1(Date subdate1) {
        this.subdate1 = subdate1;
    }

    public Date getSubdate2() {
        return subdate2;
    }

    public void setSubdate2(Date subdate2) {
        this.subdate2 = subdate2;
    }

    public String getBsfsStatusZh() {
        return bsfsStatusZh;
    }

    public void setBsfsStatusZh(String bsfsStatusZh) {
        this.bsfsStatusZh = bsfsStatusZh == null ? null : bsfsStatusZh.trim();
    }

    public String getBsfsStatusEn() {
        return bsfsStatusEn;
    }

    public void setBsfsStatusEn(String bsfsStatusEn) {
        this.bsfsStatusEn = bsfsStatusEn == null ? null : bsfsStatusEn.trim();
    }
}