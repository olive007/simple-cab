package com.datarepublic.simplecab.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datarepublic.simplecab.repository.SimpleCabRepository;

@RestController
@RequestMapping(path="/api")
public class ApiController {

	@Autowired
	private SimpleCabRepository cabRepository;

	@GetMapping(path="/nbrTripByPickupDateAndMedallion/{pickup_datetime}/{medallion}")
	public @ResponseBody Integer getNbrMedallion(
		@PathVariable String medallion,
		@PathVariable @DateTimeFormat(iso=ISO.DATE) Date pickup_datetime) {

		List<String> medallions = new ArrayList<>(1);

		medallions.add(medallion);
		
		return cabRepository.getCountByMedallionAndPickupDatetime(medallions, pickup_datetime);
	}

	
	@GetMapping(path="/clearCache")
	public void clearCache() {
		cabRepository.clearCache();
	}


	@GetMapping(path="/nbrTripByPickupDateAndMedallions/{pickup_datetime}/{medallionList}",
				produces="application/JSON")
    @ResponseBody
    public Integer getNbrMedallionsFromCache(
    		@PathVariable String medallionList,
    		@PathVariable @DateTimeFormat(iso=ISO.DATE) Date pickup_datetime) {

		List<String> medallions = new ArrayList<>();
		
		medallions.addAll(Arrays.asList(medallionList.split(",")));
		
		return cabRepository.getCountByMedallionAndPickupDatetime(medallions, pickup_datetime);
	}

	
	
	@GetMapping(path="/fresh/nbrTripByPickupDateAndMedallions/{pickup_datetime}/{medallionList}",
			produces="application/JSON")
	@ResponseBody
	public Integer getNbrMedallionsFromDataBase(
		@PathVariable String medallionList,
		@PathVariable @DateTimeFormat(iso=ISO.DATE) Date pickup_datetime) {

		clearCache();
		
		return getNbrMedallionsFromCache(medallionList, pickup_datetime);
	}

}

