package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {
		setColor(0,255,0);
		
		int prevX = 0;
		int prevY = 0;
		
		for(int i = 0; i < gpspoints.length; i++) {
			int xpunkt = (int) (ybase - (maxlon - gpspoints[i].getLongitude()) * xstep);
			int ypunkt = (int) ((maxlat - gpspoints[i].getLatitude()) * ystep + (1.5 * MARGIN));
			
			fillCircle(xpunkt, ypunkt, 3);
			
			if(prevX != 0 || prevY != 0) {
				drawLine(xpunkt, ypunkt, prevX, prevY);
			}
			
			prevX = xpunkt;
			prevY = ypunkt;
		}
		
	}

	public void showStatistics() {
		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier New",12);
		
		drawString("Total time     : " + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, MARGIN);
		drawString("Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km", MARGIN, MARGIN + TEXTDISTANCE );
		drawString("Total elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", MARGIN, MARGIN + 2 * TEXTDISTANCE);
		drawString("Max speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed() * 3.6) + " km/t", MARGIN, MARGIN + 3 * TEXTDISTANCE);
		drawString("Average speed  : " + GPSUtils.formatDouble(gpscomputer.averageSpeed() * 3.6) + " km/t", MARGIN, MARGIN + 4 * TEXTDISTANCE);
		drawString("Energy         : " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", MARGIN, MARGIN + 5 * TEXTDISTANCE);
		
	}

	public void replayRoute(int ybase) {
		int startX = (int) (ybase - (maxlon - gpspoints[0].getLongitude()) * xstep);
		int startY = (int) ((maxlat - gpspoints[0].getLatitude()) * ystep + (1.5 * MARGIN));
		
		setColor(0,0,255);
		
		int circleId = fillCircle(startX, startY, 6);
		
		for (int i = 1; i < gpspoints.length; i++) {
			int xpunkt = (int) (ybase - (maxlon - gpspoints[i].getLongitude()) * xstep);
			int ypunkt = (int) ((maxlat - gpspoints[i].getLatitude()) * ystep + (1.5 * MARGIN));
			moveCircle(circleId, xpunkt, ypunkt);
			
		}
	}

}
