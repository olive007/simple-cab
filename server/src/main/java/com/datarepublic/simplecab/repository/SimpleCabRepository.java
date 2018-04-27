package com.datarepublic.simplecab.repository;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.datarepublic.simplecab.model.NyCab;

public interface SimpleCabRepository extends CrudRepository<NyCab, Long> {

	@Query("SELECT count(*) FROM cab_trip_data c " +
		   "WHERE (c.medallion IN ?1) AND " +
		   "DATE(c.pickup_datetime) = ?2")
	@Cacheable("getCountByMedallionAndPickupDatetime")
	Integer getCountByMedallionAndPickupDatetime(List<String> medallion, Date pickup_datetime);

	@CacheEvict(cacheNames="getCountByMedallionAndPickupDatetime", allEntries=true)
	public default void clearCache() {
		
	}
	
}
