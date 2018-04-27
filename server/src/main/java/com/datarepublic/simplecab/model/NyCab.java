package com.datarepublic.simplecab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="cab_trip_data")
public class NyCab {
	
	
	// Attribute
	@Id
	private String medallion;
	private String hack_license;
	private String vendor_id;
	private Integer rate_code;
	private String store_and_fwd_flag;
	private Date pickup_datetime;
	private Date dropoff_datetime;
	private Integer passenger_count;
	private Integer trip_time_in_secs;
	private Double trip_distance;
	private Double pickup_longitude;
	private Double pickup_latitude;
	private Double dropoff_longitude;
	private Double dropoff_latitude;

	
	// Constructor
	public NyCab() {
		this("", "", "", 0, "", new Date(), new Date(), 0, 0, .0);
	}

	public NyCab(
			String medallion,
			String hack_license,
			String vendor_id,
			Integer rate_code,
			String store_and_fwd_flag,
			Date pickup_datetime,
			Date dropoff_datetime,
			Integer passenger_count,
			Integer trip_time_in_secs,
			Double trip_distance) {

		super();
		this.medallion = medallion;
		this.hack_license = hack_license;
		this.vendor_id = vendor_id;
		this.rate_code = rate_code;
		this.store_and_fwd_flag = store_and_fwd_flag;
		this.pickup_datetime = pickup_datetime;
		this.dropoff_datetime = dropoff_datetime;
		this.passenger_count = passenger_count;
		this.trip_time_in_secs = trip_time_in_secs;
		this.trip_distance = trip_distance;
		this.pickup_longitude = .0;
		this.pickup_latitude = .0;
		this.dropoff_longitude = .0;
		this.dropoff_latitude = .0;
	}

	
	// Getter
	public String getMedallion() {
		return medallion;
	}

	public String getHack_license() {
		return hack_license;
	}

	
	
	// Setter
	public void setMedallion(String medallion) {
		this.medallion = medallion;
	}

	public void setHack_license(String hack_license) {
		this.hack_license = hack_license;
	}

}
