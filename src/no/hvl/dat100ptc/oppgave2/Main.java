package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {
	    public static void main(String[] args) {
	      
	        GPSPoint point1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);
	        GPSPoint point2 = new GPSPoint(31947, 60.385490, 5.217317, 62.0);

	        
	        GPSData gpsData = new GPSData(2);

	        
	        gpsData.insertGPS(point1);
	        gpsData.insertGPS(point2);

	       
	        gpsData.print();
	    }
	}