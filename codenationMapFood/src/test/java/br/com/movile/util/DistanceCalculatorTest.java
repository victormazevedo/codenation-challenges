package br.com.movile.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DistanceCalculatorTest {
	
	DistanceCalculator distanceCalculator;
	
	@Test
	public void shouldCalculteDistanceWithUnitK() {
				
		double lat1 = -51.228496 ,lon1 = -30.03742831, lat2 = -46.657908, lon2 = -23.554423;
		String unit = "K";
		
		double distance = distanceCalculator.distance(lat1, lon1, lat2, lon2, unit);
		
		assertNotNull(distance);
		
		
	}
	
	@Test
	public void shouldCalculteDistanceWithUnitN() {
				
		double lat1 = -51.228496 ,lon1 = -30.03742831, lat2 = -46.657908, lon2 = -23.554423;
		String unit = "N";
		
		double distance = distanceCalculator.distance(lat1, lon1, lat2, lon2, unit);
		
		assertNotNull(distance);
		
	}
	
	@Test
	public void shouldReturnZeroWithEqualsLongLat() {
				
		double lat1 = -51.228496 ,lon1 = -30.03742831, lat2 = -51.228496, lon2 = -30.03742831;
		String unit = "K";
		
		double distance = distanceCalculator.distance(lat1, lon1, lat2, lon2, unit);
		
		assertEquals(0, distance);
		
	}
	
	

}
