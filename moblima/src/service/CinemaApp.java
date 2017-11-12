package service;

import java.io.IOException;
import cinema.CinemaSeat;

public class CinemaApp{
	private CinemaService service;
	
	public CinemaApp(CinemaService svc) {
		this.service = svc;
	}
	
	public CinemaApp() {
	}

	public void createNewCSV(CinemaSeat[] seat,int cinemaId, int year, int month, int date, int time) throws IOException {
			this.service.cinemaToCSV(seat, cinemaId, year, month, date, time);
	}

	public void printHallSeats(int cinemaId, int year, int month, int date, int time) {				
			this.service.printCinemaSeats(cinemaId, year, month, date, time);
	}
		
	public void openCSVToCinema(CinemaSeat[] seat,int cinemaId,int year, int month, int date, int time) {
		
		this.service.CSVToCinema(seat,cinemaId, year, month, date, time);
	}

	public void saveCinemaToCSV(CinemaSeat[] seat,int cinemaId,int year, int month, int date, int time) throws IOException {
		
		this.service.cinemaToCSV(seat,cinemaId, year, month, date, time);
	}

}
