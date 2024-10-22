package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		// TODO Bruk getTime-metoden for å skrive tidspunkt ut på skjermen og setTime-metoden for å endre tidspunkt til 2.
		//Bruk toString-metoden for å skrive informasjon om objektet ut på skjermen.
	GPSPoint gps1 = new GPSPoint(1, 2.0, 3.0, 5.0);
	System.out.println(gps1.getTime());
	gps1.setTime(2);
	System.out.print(gps1);
	}

}
