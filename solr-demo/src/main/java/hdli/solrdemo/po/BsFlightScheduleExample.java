package hdli.solrdemo.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BsFlightScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BsFlightScheduleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberIsNull() {
            addCriterion("BSFS_SERIAL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberIsNotNull() {
            addCriterion("BSFS_SERIAL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberEqualTo(String value) {
            addCriterion("BSFS_SERIAL_NUMBER =", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberNotEqualTo(String value) {
            addCriterion("BSFS_SERIAL_NUMBER <>", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberGreaterThan(String value) {
            addCriterion("BSFS_SERIAL_NUMBER >", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_SERIAL_NUMBER >=", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberLessThan(String value) {
            addCriterion("BSFS_SERIAL_NUMBER <", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("BSFS_SERIAL_NUMBER <=", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberLike(String value) {
            addCriterion("BSFS_SERIAL_NUMBER like", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberNotLike(String value) {
            addCriterion("BSFS_SERIAL_NUMBER not like", value, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberIn(List<String> values) {
            addCriterion("BSFS_SERIAL_NUMBER in", values, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberNotIn(List<String> values) {
            addCriterion("BSFS_SERIAL_NUMBER not in", values, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberBetween(String value1, String value2) {
            addCriterion("BSFS_SERIAL_NUMBER between", value1, value2, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsSerialNumberNotBetween(String value1, String value2) {
            addCriterion("BSFS_SERIAL_NUMBER not between", value1, value2, "bsfsSerialNumber");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateIsNull() {
            addCriterion("BSFS_UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateIsNotNull() {
            addCriterion("BSFS_UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateEqualTo(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE =", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE <>", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE >", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE >=", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE <", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE <=", value, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE in", values, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE not in", values, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE between", value1, value2, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BSFS_UPDATE_DATE not between", value1, value2, "bsfsUpdateDate");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeIsNull() {
            addCriterion("BSFS_FLIGHT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeIsNotNull() {
            addCriterion("BSFS_FLIGHT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_TYPE =", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeNotEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_TYPE <>", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeGreaterThan(String value) {
            addCriterion("BSFS_FLIGHT_TYPE >", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_TYPE >=", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeLessThan(String value) {
            addCriterion("BSFS_FLIGHT_TYPE <", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_TYPE <=", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeLike(String value) {
            addCriterion("BSFS_FLIGHT_TYPE like", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeNotLike(String value) {
            addCriterion("BSFS_FLIGHT_TYPE not like", value, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeIn(List<String> values) {
            addCriterion("BSFS_FLIGHT_TYPE in", values, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeNotIn(List<String> values) {
            addCriterion("BSFS_FLIGHT_TYPE not in", values, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeBetween(String value1, String value2) {
            addCriterion("BSFS_FLIGHT_TYPE between", value1, value2, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightTypeNotBetween(String value1, String value2) {
            addCriterion("BSFS_FLIGHT_TYPE not between", value1, value2, "bsfsFlightType");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeIsNull() {
            addCriterion("BSFS_AIRLINECODE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeIsNotNull() {
            addCriterion("BSFS_AIRLINECODE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeEqualTo(String value) {
            addCriterion("BSFS_AIRLINECODE =", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeNotEqualTo(String value) {
            addCriterion("BSFS_AIRLINECODE <>", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeGreaterThan(String value) {
            addCriterion("BSFS_AIRLINECODE >", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_AIRLINECODE >=", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeLessThan(String value) {
            addCriterion("BSFS_AIRLINECODE <", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_AIRLINECODE <=", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeLike(String value) {
            addCriterion("BSFS_AIRLINECODE like", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeNotLike(String value) {
            addCriterion("BSFS_AIRLINECODE not like", value, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeIn(List<String> values) {
            addCriterion("BSFS_AIRLINECODE in", values, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeNotIn(List<String> values) {
            addCriterion("BSFS_AIRLINECODE not in", values, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeBetween(String value1, String value2) {
            addCriterion("BSFS_AIRLINECODE between", value1, value2, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsAirlinecodeNotBetween(String value1, String value2) {
            addCriterion("BSFS_AIRLINECODE not between", value1, value2, "bsfsAirlinecode");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameIsNull() {
            addCriterion("BSFS_TRANSPORTNAME is null");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameIsNotNull() {
            addCriterion("BSFS_TRANSPORTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameEqualTo(String value) {
            addCriterion("BSFS_TRANSPORTNAME =", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameNotEqualTo(String value) {
            addCriterion("BSFS_TRANSPORTNAME <>", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameGreaterThan(String value) {
            addCriterion("BSFS_TRANSPORTNAME >", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_TRANSPORTNAME >=", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameLessThan(String value) {
            addCriterion("BSFS_TRANSPORTNAME <", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameLessThanOrEqualTo(String value) {
            addCriterion("BSFS_TRANSPORTNAME <=", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameLike(String value) {
            addCriterion("BSFS_TRANSPORTNAME like", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameNotLike(String value) {
            addCriterion("BSFS_TRANSPORTNAME not like", value, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameIn(List<String> values) {
            addCriterion("BSFS_TRANSPORTNAME in", values, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameNotIn(List<String> values) {
            addCriterion("BSFS_TRANSPORTNAME not in", values, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameBetween(String value1, String value2) {
            addCriterion("BSFS_TRANSPORTNAME between", value1, value2, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsTransportnameNotBetween(String value1, String value2) {
            addCriterion("BSFS_TRANSPORTNAME not between", value1, value2, "bsfsTransportname");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeIsNull() {
            addCriterion("BSFS_STARTSTATION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeIsNotNull() {
            addCriterion("BSFS_STARTSTATION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeEqualTo(String value) {
            addCriterion("BSFS_STARTSTATION_CODE =", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeNotEqualTo(String value) {
            addCriterion("BSFS_STARTSTATION_CODE <>", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeGreaterThan(String value) {
            addCriterion("BSFS_STARTSTATION_CODE >", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_STARTSTATION_CODE >=", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeLessThan(String value) {
            addCriterion("BSFS_STARTSTATION_CODE <", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_STARTSTATION_CODE <=", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeLike(String value) {
            addCriterion("BSFS_STARTSTATION_CODE like", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeNotLike(String value) {
            addCriterion("BSFS_STARTSTATION_CODE not like", value, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeIn(List<String> values) {
            addCriterion("BSFS_STARTSTATION_CODE in", values, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeNotIn(List<String> values) {
            addCriterion("BSFS_STARTSTATION_CODE not in", values, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeBetween(String value1, String value2) {
            addCriterion("BSFS_STARTSTATION_CODE between", value1, value2, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartstationCodeNotBetween(String value1, String value2) {
            addCriterion("BSFS_STARTSTATION_CODE not between", value1, value2, "bsfsStartstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeIsNull() {
            addCriterion("BSFS_ENDSTATION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeIsNotNull() {
            addCriterion("BSFS_ENDSTATION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeEqualTo(String value) {
            addCriterion("BSFS_ENDSTATION_CODE =", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeNotEqualTo(String value) {
            addCriterion("BSFS_ENDSTATION_CODE <>", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeGreaterThan(String value) {
            addCriterion("BSFS_ENDSTATION_CODE >", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_ENDSTATION_CODE >=", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeLessThan(String value) {
            addCriterion("BSFS_ENDSTATION_CODE <", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_ENDSTATION_CODE <=", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeLike(String value) {
            addCriterion("BSFS_ENDSTATION_CODE like", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeNotLike(String value) {
            addCriterion("BSFS_ENDSTATION_CODE not like", value, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeIn(List<String> values) {
            addCriterion("BSFS_ENDSTATION_CODE in", values, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeNotIn(List<String> values) {
            addCriterion("BSFS_ENDSTATION_CODE not in", values, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeBetween(String value1, String value2) {
            addCriterion("BSFS_ENDSTATION_CODE between", value1, value2, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsEndstationCodeNotBetween(String value1, String value2) {
            addCriterion("BSFS_ENDSTATION_CODE not between", value1, value2, "bsfsEndstationCode");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameIsNull() {
            addCriterion("BSFS_STARTCITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameIsNotNull() {
            addCriterion("BSFS_STARTCITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameEqualTo(String value) {
            addCriterion("BSFS_STARTCITY_NAME =", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameNotEqualTo(String value) {
            addCriterion("BSFS_STARTCITY_NAME <>", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameGreaterThan(String value) {
            addCriterion("BSFS_STARTCITY_NAME >", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_STARTCITY_NAME >=", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameLessThan(String value) {
            addCriterion("BSFS_STARTCITY_NAME <", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameLessThanOrEqualTo(String value) {
            addCriterion("BSFS_STARTCITY_NAME <=", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameLike(String value) {
            addCriterion("BSFS_STARTCITY_NAME like", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameNotLike(String value) {
            addCriterion("BSFS_STARTCITY_NAME not like", value, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameIn(List<String> values) {
            addCriterion("BSFS_STARTCITY_NAME in", values, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameNotIn(List<String> values) {
            addCriterion("BSFS_STARTCITY_NAME not in", values, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameBetween(String value1, String value2) {
            addCriterion("BSFS_STARTCITY_NAME between", value1, value2, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsStartcityNameNotBetween(String value1, String value2) {
            addCriterion("BSFS_STARTCITY_NAME not between", value1, value2, "bsfsStartcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameIsNull() {
            addCriterion("BSFS_ENDCITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameIsNotNull() {
            addCriterion("BSFS_ENDCITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameEqualTo(String value) {
            addCriterion("BSFS_ENDCITY_NAME =", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameNotEqualTo(String value) {
            addCriterion("BSFS_ENDCITY_NAME <>", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameGreaterThan(String value) {
            addCriterion("BSFS_ENDCITY_NAME >", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_ENDCITY_NAME >=", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameLessThan(String value) {
            addCriterion("BSFS_ENDCITY_NAME <", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameLessThanOrEqualTo(String value) {
            addCriterion("BSFS_ENDCITY_NAME <=", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameLike(String value) {
            addCriterion("BSFS_ENDCITY_NAME like", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameNotLike(String value) {
            addCriterion("BSFS_ENDCITY_NAME not like", value, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameIn(List<String> values) {
            addCriterion("BSFS_ENDCITY_NAME in", values, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameNotIn(List<String> values) {
            addCriterion("BSFS_ENDCITY_NAME not in", values, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameBetween(String value1, String value2) {
            addCriterion("BSFS_ENDCITY_NAME between", value1, value2, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsEndcityNameNotBetween(String value1, String value2) {
            addCriterion("BSFS_ENDCITY_NAME not between", value1, value2, "bsfsEndcityName");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeIsNull() {
            addCriterion("BSFS_DEPARTURE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeIsNotNull() {
            addCriterion("BSFS_DEPARTURE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeEqualTo(String value) {
            addCriterion("BSFS_DEPARTURE_TIME =", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeNotEqualTo(String value) {
            addCriterion("BSFS_DEPARTURE_TIME <>", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeGreaterThan(String value) {
            addCriterion("BSFS_DEPARTURE_TIME >", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_DEPARTURE_TIME >=", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeLessThan(String value) {
            addCriterion("BSFS_DEPARTURE_TIME <", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_DEPARTURE_TIME <=", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeLike(String value) {
            addCriterion("BSFS_DEPARTURE_TIME like", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeNotLike(String value) {
            addCriterion("BSFS_DEPARTURE_TIME not like", value, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeIn(List<String> values) {
            addCriterion("BSFS_DEPARTURE_TIME in", values, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeNotIn(List<String> values) {
            addCriterion("BSFS_DEPARTURE_TIME not in", values, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeBetween(String value1, String value2) {
            addCriterion("BSFS_DEPARTURE_TIME between", value1, value2, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsDepartureTimeNotBetween(String value1, String value2) {
            addCriterion("BSFS_DEPARTURE_TIME not between", value1, value2, "bsfsDepartureTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeIsNull() {
            addCriterion("BSFS_ARRIVAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeIsNotNull() {
            addCriterion("BSFS_ARRIVAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeEqualTo(String value) {
            addCriterion("BSFS_ARRIVAL_TIME =", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeNotEqualTo(String value) {
            addCriterion("BSFS_ARRIVAL_TIME <>", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeGreaterThan(String value) {
            addCriterion("BSFS_ARRIVAL_TIME >", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_ARRIVAL_TIME >=", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeLessThan(String value) {
            addCriterion("BSFS_ARRIVAL_TIME <", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_ARRIVAL_TIME <=", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeLike(String value) {
            addCriterion("BSFS_ARRIVAL_TIME like", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeNotLike(String value) {
            addCriterion("BSFS_ARRIVAL_TIME not like", value, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeIn(List<String> values) {
            addCriterion("BSFS_ARRIVAL_TIME in", values, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeNotIn(List<String> values) {
            addCriterion("BSFS_ARRIVAL_TIME not in", values, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeBetween(String value1, String value2) {
            addCriterion("BSFS_ARRIVAL_TIME between", value1, value2, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsArrivalTimeNotBetween(String value1, String value2) {
            addCriterion("BSFS_ARRIVAL_TIME not between", value1, value2, "bsfsArrivalTime");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnIsNull() {
            addCriterion("BSFS_STOP_SIGHN is null");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnIsNotNull() {
            addCriterion("BSFS_STOP_SIGHN is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnEqualTo(String value) {
            addCriterion("BSFS_STOP_SIGHN =", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnNotEqualTo(String value) {
            addCriterion("BSFS_STOP_SIGHN <>", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnGreaterThan(String value) {
            addCriterion("BSFS_STOP_SIGHN >", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_STOP_SIGHN >=", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnLessThan(String value) {
            addCriterion("BSFS_STOP_SIGHN <", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnLessThanOrEqualTo(String value) {
            addCriterion("BSFS_STOP_SIGHN <=", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnLike(String value) {
            addCriterion("BSFS_STOP_SIGHN like", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnNotLike(String value) {
            addCriterion("BSFS_STOP_SIGHN not like", value, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnIn(List<String> values) {
            addCriterion("BSFS_STOP_SIGHN in", values, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnNotIn(List<String> values) {
            addCriterion("BSFS_STOP_SIGHN not in", values, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnBetween(String value1, String value2) {
            addCriterion("BSFS_STOP_SIGHN between", value1, value2, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsStopSighnNotBetween(String value1, String value2) {
            addCriterion("BSFS_STOP_SIGHN not between", value1, value2, "bsfsStopSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnIsNull() {
            addCriterion("BSFS_SHARE_SIGHN is null");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnIsNotNull() {
            addCriterion("BSFS_SHARE_SIGHN is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnEqualTo(String value) {
            addCriterion("BSFS_SHARE_SIGHN =", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnNotEqualTo(String value) {
            addCriterion("BSFS_SHARE_SIGHN <>", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnGreaterThan(String value) {
            addCriterion("BSFS_SHARE_SIGHN >", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_SHARE_SIGHN >=", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnLessThan(String value) {
            addCriterion("BSFS_SHARE_SIGHN <", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnLessThanOrEqualTo(String value) {
            addCriterion("BSFS_SHARE_SIGHN <=", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnLike(String value) {
            addCriterion("BSFS_SHARE_SIGHN like", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnNotLike(String value) {
            addCriterion("BSFS_SHARE_SIGHN not like", value, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnIn(List<String> values) {
            addCriterion("BSFS_SHARE_SIGHN in", values, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnNotIn(List<String> values) {
            addCriterion("BSFS_SHARE_SIGHN not in", values, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnBetween(String value1, String value2) {
            addCriterion("BSFS_SHARE_SIGHN between", value1, value2, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsShareSighnNotBetween(String value1, String value2) {
            addCriterion("BSFS_SHARE_SIGHN not between", value1, value2, "bsfsShareSighn");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleIsNull() {
            addCriterion("BSFS_SCHEDULE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleIsNotNull() {
            addCriterion("BSFS_SCHEDULE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleEqualTo(String value) {
            addCriterion("BSFS_SCHEDULE =", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleNotEqualTo(String value) {
            addCriterion("BSFS_SCHEDULE <>", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleGreaterThan(String value) {
            addCriterion("BSFS_SCHEDULE >", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_SCHEDULE >=", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleLessThan(String value) {
            addCriterion("BSFS_SCHEDULE <", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleLessThanOrEqualTo(String value) {
            addCriterion("BSFS_SCHEDULE <=", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleLike(String value) {
            addCriterion("BSFS_SCHEDULE like", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleNotLike(String value) {
            addCriterion("BSFS_SCHEDULE not like", value, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleIn(List<String> values) {
            addCriterion("BSFS_SCHEDULE in", values, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleNotIn(List<String> values) {
            addCriterion("BSFS_SCHEDULE not in", values, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleBetween(String value1, String value2) {
            addCriterion("BSFS_SCHEDULE between", value1, value2, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsScheduleNotBetween(String value1, String value2) {
            addCriterion("BSFS_SCHEDULE not between", value1, value2, "bsfsSchedule");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeIsNull() {
            addCriterion("BSFS_AIRCRFTTYPE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeIsNotNull() {
            addCriterion("BSFS_AIRCRFTTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeEqualTo(String value) {
            addCriterion("BSFS_AIRCRFTTYPE =", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeNotEqualTo(String value) {
            addCriterion("BSFS_AIRCRFTTYPE <>", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeGreaterThan(String value) {
            addCriterion("BSFS_AIRCRFTTYPE >", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_AIRCRFTTYPE >=", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeLessThan(String value) {
            addCriterion("BSFS_AIRCRFTTYPE <", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeLessThanOrEqualTo(String value) {
            addCriterion("BSFS_AIRCRFTTYPE <=", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeLike(String value) {
            addCriterion("BSFS_AIRCRFTTYPE like", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeNotLike(String value) {
            addCriterion("BSFS_AIRCRFTTYPE not like", value, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeIn(List<String> values) {
            addCriterion("BSFS_AIRCRFTTYPE in", values, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeNotIn(List<String> values) {
            addCriterion("BSFS_AIRCRFTTYPE not in", values, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeBetween(String value1, String value2) {
            addCriterion("BSFS_AIRCRFTTYPE between", value1, value2, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsAircrfttypeNotBetween(String value1, String value2) {
            addCriterion("BSFS_AIRCRFTTYPE not between", value1, value2, "bsfsAircrfttype");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageIsNull() {
            addCriterion("BSFS_MILEAGE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageIsNotNull() {
            addCriterion("BSFS_MILEAGE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageEqualTo(String value) {
            addCriterion("BSFS_MILEAGE =", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageNotEqualTo(String value) {
            addCriterion("BSFS_MILEAGE <>", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageGreaterThan(String value) {
            addCriterion("BSFS_MILEAGE >", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_MILEAGE >=", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageLessThan(String value) {
            addCriterion("BSFS_MILEAGE <", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageLessThanOrEqualTo(String value) {
            addCriterion("BSFS_MILEAGE <=", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageLike(String value) {
            addCriterion("BSFS_MILEAGE like", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageNotLike(String value) {
            addCriterion("BSFS_MILEAGE not like", value, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageIn(List<String> values) {
            addCriterion("BSFS_MILEAGE in", values, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageNotIn(List<String> values) {
            addCriterion("BSFS_MILEAGE not in", values, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageBetween(String value1, String value2) {
            addCriterion("BSFS_MILEAGE between", value1, value2, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsMileageNotBetween(String value1, String value2) {
            addCriterion("BSFS_MILEAGE not between", value1, value2, "bsfsMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageIsNull() {
            addCriterion("BSFS_FLIGHT_MILEAGE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageIsNotNull() {
            addCriterion("BSFS_FLIGHT_MILEAGE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE =", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageNotEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE <>", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageGreaterThan(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE >", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE >=", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageLessThan(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE <", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageLessThanOrEqualTo(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE <=", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageLike(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE like", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageNotLike(String value) {
            addCriterion("BSFS_FLIGHT_MILEAGE not like", value, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageIn(List<String> values) {
            addCriterion("BSFS_FLIGHT_MILEAGE in", values, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageNotIn(List<String> values) {
            addCriterion("BSFS_FLIGHT_MILEAGE not in", values, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageBetween(String value1, String value2) {
            addCriterion("BSFS_FLIGHT_MILEAGE between", value1, value2, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsFlightMileageNotBetween(String value1, String value2) {
            addCriterion("BSFS_FLIGHT_MILEAGE not between", value1, value2, "bsfsFlightMileage");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateIsNull() {
            addCriterion("BSFS_PUNCTUALITY_RATE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateIsNotNull() {
            addCriterion("BSFS_PUNCTUALITY_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateEqualTo(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE =", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateNotEqualTo(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE <>", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateGreaterThan(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE >", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE >=", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateLessThan(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE <", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateLessThanOrEqualTo(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE <=", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateLike(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE like", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateNotLike(String value) {
            addCriterion("BSFS_PUNCTUALITY_RATE not like", value, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateIn(List<String> values) {
            addCriterion("BSFS_PUNCTUALITY_RATE in", values, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateNotIn(List<String> values) {
            addCriterion("BSFS_PUNCTUALITY_RATE not in", values, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateBetween(String value1, String value2) {
            addCriterion("BSFS_PUNCTUALITY_RATE between", value1, value2, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsPunctualityRateNotBetween(String value1, String value2) {
            addCriterion("BSFS_PUNCTUALITY_RATE not between", value1, value2, "bsfsPunctualityRate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateIsNull() {
            addCriterion("BSFS_EFFECTIVE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateIsNotNull() {
            addCriterion("BSFS_EFFECTIVE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateEqualTo(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE =", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateNotEqualTo(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE <>", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateGreaterThan(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE >", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE >=", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateLessThan(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE <", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateLessThanOrEqualTo(Date value) {
            addCriterion("BSFS_EFFECTIVE_DATE <=", value, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateIn(List<Date> values) {
            addCriterion("BSFS_EFFECTIVE_DATE in", values, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateNotIn(List<Date> values) {
            addCriterion("BSFS_EFFECTIVE_DATE not in", values, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateBetween(Date value1, Date value2) {
            addCriterion("BSFS_EFFECTIVE_DATE between", value1, value2, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsEffectiveDateNotBetween(Date value1, Date value2) {
            addCriterion("BSFS_EFFECTIVE_DATE not between", value1, value2, "bsfsEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateIsNull() {
            addCriterion("BSFS_EXPIRATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateIsNotNull() {
            addCriterion("BSFS_EXPIRATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateEqualTo(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE =", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateNotEqualTo(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE <>", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateGreaterThan(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE >", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE >=", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateLessThan(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE <", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateLessThanOrEqualTo(Date value) {
            addCriterion("BSFS_EXPIRATION_DATE <=", value, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateIn(List<Date> values) {
            addCriterion("BSFS_EXPIRATION_DATE in", values, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateNotIn(List<Date> values) {
            addCriterion("BSFS_EXPIRATION_DATE not in", values, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateBetween(Date value1, Date value2) {
            addCriterion("BSFS_EXPIRATION_DATE between", value1, value2, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsExpirationDateNotBetween(Date value1, Date value2) {
            addCriterion("BSFS_EXPIRATION_DATE not between", value1, value2, "bsfsExpirationDate");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteIsNull() {
            addCriterion("BSFS_ISHOTROUTE is null");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteIsNotNull() {
            addCriterion("BSFS_ISHOTROUTE is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteEqualTo(String value) {
            addCriterion("BSFS_ISHOTROUTE =", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteNotEqualTo(String value) {
            addCriterion("BSFS_ISHOTROUTE <>", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteGreaterThan(String value) {
            addCriterion("BSFS_ISHOTROUTE >", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_ISHOTROUTE >=", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteLessThan(String value) {
            addCriterion("BSFS_ISHOTROUTE <", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteLessThanOrEqualTo(String value) {
            addCriterion("BSFS_ISHOTROUTE <=", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteLike(String value) {
            addCriterion("BSFS_ISHOTROUTE like", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteNotLike(String value) {
            addCriterion("BSFS_ISHOTROUTE not like", value, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteIn(List<String> values) {
            addCriterion("BSFS_ISHOTROUTE in", values, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteNotIn(List<String> values) {
            addCriterion("BSFS_ISHOTROUTE not in", values, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteBetween(String value1, String value2) {
            addCriterion("BSFS_ISHOTROUTE between", value1, value2, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andBsfsIshotrouteNotBetween(String value1, String value2) {
            addCriterion("BSFS_ISHOTROUTE not between", value1, value2, "bsfsIshotroute");
            return (Criteria) this;
        }

        public Criteria andRecStatusIsNull() {
            addCriterion("REC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRecStatusIsNotNull() {
            addCriterion("REC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRecStatusEqualTo(Integer value) {
            addCriterion("REC_STATUS =", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusNotEqualTo(Integer value) {
            addCriterion("REC_STATUS <>", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusGreaterThan(Integer value) {
            addCriterion("REC_STATUS >", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("REC_STATUS >=", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusLessThan(Integer value) {
            addCriterion("REC_STATUS <", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusLessThanOrEqualTo(Integer value) {
            addCriterion("REC_STATUS <=", value, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusIn(List<Integer> values) {
            addCriterion("REC_STATUS in", values, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusNotIn(List<Integer> values) {
            addCriterion("REC_STATUS not in", values, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusBetween(Integer value1, Integer value2) {
            addCriterion("REC_STATUS between", value1, value2, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("REC_STATUS not between", value1, value2, "recStatus");
            return (Criteria) this;
        }

        public Criteria andRecVerIsNull() {
            addCriterion("REC_VER is null");
            return (Criteria) this;
        }

        public Criteria andRecVerIsNotNull() {
            addCriterion("REC_VER is not null");
            return (Criteria) this;
        }

        public Criteria andRecVerEqualTo(Integer value) {
            addCriterion("REC_VER =", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerNotEqualTo(Integer value) {
            addCriterion("REC_VER <>", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerGreaterThan(Integer value) {
            addCriterion("REC_VER >", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerGreaterThanOrEqualTo(Integer value) {
            addCriterion("REC_VER >=", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerLessThan(Integer value) {
            addCriterion("REC_VER <", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerLessThanOrEqualTo(Integer value) {
            addCriterion("REC_VER <=", value, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerIn(List<Integer> values) {
            addCriterion("REC_VER in", values, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerNotIn(List<Integer> values) {
            addCriterion("REC_VER not in", values, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerBetween(Integer value1, Integer value2) {
            addCriterion("REC_VER between", value1, value2, "recVer");
            return (Criteria) this;
        }

        public Criteria andRecVerNotBetween(Integer value1, Integer value2) {
            addCriterion("REC_VER not between", value1, value2, "recVer");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNull() {
            addCriterion("CREATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNotNull() {
            addCriterion("CREATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNameEqualTo(String value) {
            addCriterion("CREATE_NAME =", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotEqualTo(String value) {
            addCriterion("CREATE_NAME <>", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThan(String value) {
            addCriterion("CREATE_NAME >", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME >=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThan(String value) {
            addCriterion("CREATE_NAME <", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME <=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLike(String value) {
            addCriterion("CREATE_NAME like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotLike(String value) {
            addCriterion("CREATE_NAME not like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameIn(List<String> values) {
            addCriterion("CREATE_NAME in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotIn(List<String> values) {
            addCriterion("CREATE_NAME not in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameBetween(String value1, String value2) {
            addCriterion("CREATE_NAME between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_NAME not between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("MODIFIER is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("MODIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("MODIFIER =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("MODIFIER <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("MODIFIER >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFIER >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("MODIFIER <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("MODIFIER <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("MODIFIER like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("MODIFIER not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("MODIFIER in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("MODIFIER not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("MODIFIER between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("MODIFIER not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyNameIsNull() {
            addCriterion("MODIFY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModifyNameIsNotNull() {
            addCriterion("MODIFY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyNameEqualTo(String value) {
            addCriterion("MODIFY_NAME =", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameNotEqualTo(String value) {
            addCriterion("MODIFY_NAME <>", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameGreaterThan(String value) {
            addCriterion("MODIFY_NAME >", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_NAME >=", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameLessThan(String value) {
            addCriterion("MODIFY_NAME <", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_NAME <=", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameLike(String value) {
            addCriterion("MODIFY_NAME like", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameNotLike(String value) {
            addCriterion("MODIFY_NAME not like", value, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameIn(List<String> values) {
            addCriterion("MODIFY_NAME in", values, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameNotIn(List<String> values) {
            addCriterion("MODIFY_NAME not in", values, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameBetween(String value1, String value2) {
            addCriterion("MODIFY_NAME between", value1, value2, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyNameNotBetween(String value1, String value2) {
            addCriterion("MODIFY_NAME not between", value1, value2, "modifyName");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andSubstr1IsNull() {
            addCriterion("SUBSTR1 is null");
            return (Criteria) this;
        }

        public Criteria andSubstr1IsNotNull() {
            addCriterion("SUBSTR1 is not null");
            return (Criteria) this;
        }

        public Criteria andSubstr1EqualTo(String value) {
            addCriterion("SUBSTR1 =", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1NotEqualTo(String value) {
            addCriterion("SUBSTR1 <>", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1GreaterThan(String value) {
            addCriterion("SUBSTR1 >", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1GreaterThanOrEqualTo(String value) {
            addCriterion("SUBSTR1 >=", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1LessThan(String value) {
            addCriterion("SUBSTR1 <", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1LessThanOrEqualTo(String value) {
            addCriterion("SUBSTR1 <=", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1Like(String value) {
            addCriterion("SUBSTR1 like", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1NotLike(String value) {
            addCriterion("SUBSTR1 not like", value, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1In(List<String> values) {
            addCriterion("SUBSTR1 in", values, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1NotIn(List<String> values) {
            addCriterion("SUBSTR1 not in", values, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1Between(String value1, String value2) {
            addCriterion("SUBSTR1 between", value1, value2, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr1NotBetween(String value1, String value2) {
            addCriterion("SUBSTR1 not between", value1, value2, "substr1");
            return (Criteria) this;
        }

        public Criteria andSubstr2IsNull() {
            addCriterion("SUBSTR2 is null");
            return (Criteria) this;
        }

        public Criteria andSubstr2IsNotNull() {
            addCriterion("SUBSTR2 is not null");
            return (Criteria) this;
        }

        public Criteria andSubstr2EqualTo(String value) {
            addCriterion("SUBSTR2 =", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2NotEqualTo(String value) {
            addCriterion("SUBSTR2 <>", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2GreaterThan(String value) {
            addCriterion("SUBSTR2 >", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2GreaterThanOrEqualTo(String value) {
            addCriterion("SUBSTR2 >=", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2LessThan(String value) {
            addCriterion("SUBSTR2 <", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2LessThanOrEqualTo(String value) {
            addCriterion("SUBSTR2 <=", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2Like(String value) {
            addCriterion("SUBSTR2 like", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2NotLike(String value) {
            addCriterion("SUBSTR2 not like", value, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2In(List<String> values) {
            addCriterion("SUBSTR2 in", values, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2NotIn(List<String> values) {
            addCriterion("SUBSTR2 not in", values, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2Between(String value1, String value2) {
            addCriterion("SUBSTR2 between", value1, value2, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubstr2NotBetween(String value1, String value2) {
            addCriterion("SUBSTR2 not between", value1, value2, "substr2");
            return (Criteria) this;
        }

        public Criteria andSubdecima1IsNull() {
            addCriterion("SUBdecima1 is null");
            return (Criteria) this;
        }

        public Criteria andSubdecima1IsNotNull() {
            addCriterion("SUBdecima1 is not null");
            return (Criteria) this;
        }

        public Criteria andSubdecima1EqualTo(BigDecimal value) {
            addCriterion("SUBdecima1 =", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1NotEqualTo(BigDecimal value) {
            addCriterion("SUBdecima1 <>", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1GreaterThan(BigDecimal value) {
            addCriterion("SUBdecima1 >", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SUBdecima1 >=", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1LessThan(BigDecimal value) {
            addCriterion("SUBdecima1 <", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1LessThanOrEqualTo(BigDecimal value) {
            addCriterion("SUBdecima1 <=", value, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1In(List<BigDecimal> values) {
            addCriterion("SUBdecima1 in", values, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1NotIn(List<BigDecimal> values) {
            addCriterion("SUBdecima1 not in", values, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUBdecima1 between", value1, value2, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima1NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUBdecima1 not between", value1, value2, "subdecima1");
            return (Criteria) this;
        }

        public Criteria andSubdecima2IsNull() {
            addCriterion("SUBdecima2 is null");
            return (Criteria) this;
        }

        public Criteria andSubdecima2IsNotNull() {
            addCriterion("SUBdecima2 is not null");
            return (Criteria) this;
        }

        public Criteria andSubdecima2EqualTo(BigDecimal value) {
            addCriterion("SUBdecima2 =", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2NotEqualTo(BigDecimal value) {
            addCriterion("SUBdecima2 <>", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2GreaterThan(BigDecimal value) {
            addCriterion("SUBdecima2 >", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SUBdecima2 >=", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2LessThan(BigDecimal value) {
            addCriterion("SUBdecima2 <", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("SUBdecima2 <=", value, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2In(List<BigDecimal> values) {
            addCriterion("SUBdecima2 in", values, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2NotIn(List<BigDecimal> values) {
            addCriterion("SUBdecima2 not in", values, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUBdecima2 between", value1, value2, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdecima2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUBdecima2 not between", value1, value2, "subdecima2");
            return (Criteria) this;
        }

        public Criteria andSubdate1IsNull() {
            addCriterion("SUBDATE1 is null");
            return (Criteria) this;
        }

        public Criteria andSubdate1IsNotNull() {
            addCriterion("SUBDATE1 is not null");
            return (Criteria) this;
        }

        public Criteria andSubdate1EqualTo(Date value) {
            addCriterion("SUBDATE1 =", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1NotEqualTo(Date value) {
            addCriterion("SUBDATE1 <>", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1GreaterThan(Date value) {
            addCriterion("SUBDATE1 >", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1GreaterThanOrEqualTo(Date value) {
            addCriterion("SUBDATE1 >=", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1LessThan(Date value) {
            addCriterion("SUBDATE1 <", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1LessThanOrEqualTo(Date value) {
            addCriterion("SUBDATE1 <=", value, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1In(List<Date> values) {
            addCriterion("SUBDATE1 in", values, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1NotIn(List<Date> values) {
            addCriterion("SUBDATE1 not in", values, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1Between(Date value1, Date value2) {
            addCriterion("SUBDATE1 between", value1, value2, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate1NotBetween(Date value1, Date value2) {
            addCriterion("SUBDATE1 not between", value1, value2, "subdate1");
            return (Criteria) this;
        }

        public Criteria andSubdate2IsNull() {
            addCriterion("SUBDATE2 is null");
            return (Criteria) this;
        }

        public Criteria andSubdate2IsNotNull() {
            addCriterion("SUBDATE2 is not null");
            return (Criteria) this;
        }

        public Criteria andSubdate2EqualTo(Date value) {
            addCriterion("SUBDATE2 =", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2NotEqualTo(Date value) {
            addCriterion("SUBDATE2 <>", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2GreaterThan(Date value) {
            addCriterion("SUBDATE2 >", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2GreaterThanOrEqualTo(Date value) {
            addCriterion("SUBDATE2 >=", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2LessThan(Date value) {
            addCriterion("SUBDATE2 <", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2LessThanOrEqualTo(Date value) {
            addCriterion("SUBDATE2 <=", value, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2In(List<Date> values) {
            addCriterion("SUBDATE2 in", values, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2NotIn(List<Date> values) {
            addCriterion("SUBDATE2 not in", values, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2Between(Date value1, Date value2) {
            addCriterion("SUBDATE2 between", value1, value2, "subdate2");
            return (Criteria) this;
        }

        public Criteria andSubdate2NotBetween(Date value1, Date value2) {
            addCriterion("SUBDATE2 not between", value1, value2, "subdate2");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhIsNull() {
            addCriterion("BSFS_STATUS_ZH is null");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhIsNotNull() {
            addCriterion("BSFS_STATUS_ZH is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhEqualTo(String value) {
            addCriterion("BSFS_STATUS_ZH =", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhNotEqualTo(String value) {
            addCriterion("BSFS_STATUS_ZH <>", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhGreaterThan(String value) {
            addCriterion("BSFS_STATUS_ZH >", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhGreaterThanOrEqualTo(String value) {
            addCriterion("BSFS_STATUS_ZH >=", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhLessThan(String value) {
            addCriterion("BSFS_STATUS_ZH <", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhLessThanOrEqualTo(String value) {
            addCriterion("BSFS_STATUS_ZH <=", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhLike(String value) {
            addCriterion("BSFS_STATUS_ZH like", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhNotLike(String value) {
            addCriterion("BSFS_STATUS_ZH not like", value, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhIn(List<String> values) {
            addCriterion("BSFS_STATUS_ZH in", values, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhNotIn(List<String> values) {
            addCriterion("BSFS_STATUS_ZH not in", values, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhBetween(String value1, String value2) {
            addCriterion("BSFS_STATUS_ZH between", value1, value2, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusZhNotBetween(String value1, String value2) {
            addCriterion("BSFS_STATUS_ZH not between", value1, value2, "bsfsStatusZh");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnIsNull() {
            addCriterion("bsfs_status_en is null");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnIsNotNull() {
            addCriterion("bsfs_status_en is not null");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnEqualTo(String value) {
            addCriterion("bsfs_status_en =", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnNotEqualTo(String value) {
            addCriterion("bsfs_status_en <>", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnGreaterThan(String value) {
            addCriterion("bsfs_status_en >", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnGreaterThanOrEqualTo(String value) {
            addCriterion("bsfs_status_en >=", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnLessThan(String value) {
            addCriterion("bsfs_status_en <", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnLessThanOrEqualTo(String value) {
            addCriterion("bsfs_status_en <=", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnLike(String value) {
            addCriterion("bsfs_status_en like", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnNotLike(String value) {
            addCriterion("bsfs_status_en not like", value, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnIn(List<String> values) {
            addCriterion("bsfs_status_en in", values, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnNotIn(List<String> values) {
            addCriterion("bsfs_status_en not in", values, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnBetween(String value1, String value2) {
            addCriterion("bsfs_status_en between", value1, value2, "bsfsStatusEn");
            return (Criteria) this;
        }

        public Criteria andBsfsStatusEnNotBetween(String value1, String value2) {
            addCriterion("bsfs_status_en not between", value1, value2, "bsfsStatusEn");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}