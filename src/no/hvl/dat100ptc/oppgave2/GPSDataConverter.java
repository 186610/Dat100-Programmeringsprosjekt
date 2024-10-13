package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		 int hours = Integer.parseInt(timestr.substring(11, 13));  
		    int minutes = Integer.parseInt(timestr.substring(14, 16));  
		    int seconds = Integer.parseInt(timestr.substring(17, 19));  

		    
		    return hours * 3600 + minutes * 60 + seconds;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		 
	    int timeInSeconds = toSeconds(timeStr);

	    
	    double latitude = Double.parseDouble(latitudeStr);
	    double longitude = Double.parseDouble(longitudeStr);
	    double elevation = Double.parseDouble(elevationStr);

	    return new GPSPoint(timeInSeconds, latitude, longitude, elevation);
	}
		
		
}
