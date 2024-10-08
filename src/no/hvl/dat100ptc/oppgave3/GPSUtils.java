package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {
		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {
		double min;

		min = da[0];
		
		for(double d : da) {
			if (d < min)
				min = d;
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		
		for(int i = 0; i < latitudes.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < longitudes.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;
	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double d;
		double latitude1 = gpspoint1.getLatitude();
		double longitude1 = gpspoint1.getLongitude();
		double latitude2 = gpspoint2.getLatitude();
		double longitude2 = gpspoint2.getLongitude();

		double phi1 = toRadians(latitude1);
		double phi2 = toRadians(latitude2);
		double deltaphi = toRadians(latitude2 - latitude1);
		double deltadelta = toRadians(longitude2 - longitude1);

		d = R * compute_c(compute_a(phi1, phi2, deltaphi, deltadelta));
		
		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		return pow(sin(deltaphi / 2),2) + (cos(phi1) * cos(phi2) * pow(sin(deltadelta / 2), 2));	
	}

	private static double compute_c(double a) {
		return 2 * atan2(sqrt(a), sqrt(1-a));	
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		return distance(gpspoint1, gpspoint2) / secs;
	}

	public static String formatTime(int secs) {
		String TIMESEP = ":";

		int hr = secs / 3600;
		int min = (secs % 3600) / 60;
		int sec = secs % 60;
		return String.format("%10s", String.format("%02d%s%02d%s%02d", hr, TIMESEP, min, TIMESEP, sec));
	}

	public static String formatDouble(double d) {
		return String.format("%10.02f", round(d * 100) / 100.0);
	}
}
