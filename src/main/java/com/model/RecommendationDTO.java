package com.model;

/**
 * R_ID FREQUENCY DESCRIPTION METRIC LOWER_BOUND UPPER_BOUND STRING_VALUE
 * 
 * @author Sumit
 *
 */
public class RecommendationDTO {

	private int recommendationId;
	private String description;
	private String frequency;
	private String lowerLimit;
	private String upperLimit;
	private String metric; // modified as per changes from dev_Nikhil branch
	private String value; // modified as per changes from dev_Nikhil branch

	/**
	 * Parameterized constructor
	 * 
	 * @param recommendationId
	 *            the recommendation ID
	 * @param description
	 *            the description
	 * @param frequency
	 *            the frequency
	 * @param lowerLimit
	 *            the lower limit
	 * @param upperLimit
	 *            the upper limit
	 */
	public RecommendationDTO(int recommendationId, String description, String frequency, String lowerLimit,
			String upperLimit, String metric, String value) {
		this.recommendationId = recommendationId;
		this.description = description;
		this.frequency = frequency;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.metric = metric;
		this.value = value;
	}

	/**
	 * @return the recommendationId
	 */
	public int getRecommendationId() {
		return recommendationId;
	}

	/**
	 * @param recommendationId
	 *            the recommendationId to set
	 */
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the lowerLimit
	 */
	public String getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the upperLimit
	 */
	public String getUpperLimit() {
		return upperLimit;
	}

	/**
	 * @param upperLimit
	 *            the upperLimit to set
	 */
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * @return the metric
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * @param metric
	 *            the metric to set
	 */
	public void setMetric(String metric) {
		this.metric = metric;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
