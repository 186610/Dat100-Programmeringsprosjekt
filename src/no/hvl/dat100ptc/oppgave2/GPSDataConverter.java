package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		String timesek = new String(timestr);
		hr = Integer.parseInt(timesek.substring(11, 13));
		hr = hr*3600;
		min = Integer.parseInt(timesek.substring(14, 16));
		min = min*60;
		sec = Integer.parseInt(timesek.substring(17, 19));
		
		
		secs = hr + min + sec;
	
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int time = toSeconds(timeStr);  
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		GPSPoint gpspoint = new GPSPoint(time, latitude, longitude, elevation); 
		
		return gpspoint;
		
	}
	
}
