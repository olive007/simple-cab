package com.datarepublic.simplecab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Client {
	
	public static void main(String[] args){
		
		SimpleCabService.deleteCache();

		List<String> medallions = new ArrayList<>();
		
		medallions.add("000318C2E3E6381580E5C99910A60668");
		medallions.add("EC56F31BCDF8FA69876604EA996C66F6");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			printResult(LocalTime.now(), SimpleCabService.getMedallionsSummary(medallions, sdf.parse("2013-12-30")));
			printResult(LocalTime.now(), SimpleCabService.getMedallionsSummary(medallions, sdf.parse("2013-12-30")));
			printResult(LocalTime.now(), SimpleCabService.getMedallionsSummary(medallions, sdf.parse("2013-12-30"), true));
		}
		catch (ParseException e) {
			System.err.println("Error in Date");
		}

	}
	
	private static void printResult(LocalTime timeBefore, Integer result) {
		LocalTime now = LocalTime.now();
		System.out.println("Result: "+result+", Time elapsed: "+Duration.between(now, timeBefore));
	}

}
