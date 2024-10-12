package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		for(int i = 1; i < gpspoints.length; i++) {
			distance += GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
		}
		return distance;
	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			if (gpspoints[i-1].getElevation() > gpspoints[i].getElevation()) {
				
			} else {
				elevation += gpspoints[i].getElevation() - gpspoints[i-1].getElevation(); 
				
			}
		
		}
		return elevation;
	}

	public int totalTime() {
		int totaltime = 0;
		totaltime +=  gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime(); 
		
		return totaltime;
	}
	
	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for(int i = 1; i < gpspoints.length; i++) {
			speeds[i-1] =GPSUtils.speed(gpspoints[i-1], gpspoints[i]); 
		}
		return speeds;
	}

	public int[] times() {

		int[] times = new int[gpspoints.length-1];
		
		for(int i = 1; i < gpspoints.length; i++) {
			times[i-1] = gpspoints[i].getTime() - gpspoints[i - 1].getTime(); 
		}
		return times;
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		return maxspeed = GPSUtils.findMax(speeds());
		
	}

	public double averageSpeed() {

		double average = 0;
		
		return average = totalDistance() / totalTime();
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double met = 0;		
		double speedmph = speed * MS;
		
		if (speedmph < 10) {
			met += 4.0;
		} else if (speedmph < 12) {
			met += 6.0;
		} else if (speedmph < 14) {
			met += 8.0;
		} else if (speedmph < 16) {
			met += 10.0;
		} else if (speedmph < 20) {
			met += 12.0;
		} else if (speedmph > 20) {
			met += 16.0;
		}
		
		return met * weight * (secs / 3600.0);
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		int[] times = this.times();
		double[] speed = this.speeds();
		

		for (int i = 0; i < times.length; i++) {
			totalkcal += kcal(weight, times[i], speed[i]);
		}
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("=================================================");
		System.out.println("Total time     : " + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance : " + GPSUtils.formatDouble(totalDistance() / 1000) + " km");
		System.out.println("Total elevation: " + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      : " + GPSUtils.formatDouble(maxSpeed() * 3.6) + " km/t");
		System.out.println("Average speed  : " + GPSUtils.formatDouble(averageSpeed() * 3.6) + " km/t");
		System.out.println("Energy         : " + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		System.out.println("=================================================");
	}

}
