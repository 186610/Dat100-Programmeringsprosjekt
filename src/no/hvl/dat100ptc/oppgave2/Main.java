package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {
		GPSPoint gps1 = new GPSPoint(4, 2.5, 3.5, 6.0);
		GPSPoint gps2 = new GPSPoint(6, 5.0, 7.0, 4.0);
		GPSData data = new GPSData(2);
		
		data.insertGPS(gps1);
		data.insertGPS(gps2);	
		data.print();
	}
}
