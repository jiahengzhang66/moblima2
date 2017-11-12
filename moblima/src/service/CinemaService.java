package service;
import java.io.IOException;

import cinema.CinemaSeat;

public interface CinemaService {
	
	void cinemaToCSV(CinemaSeat[] seat, int cinemaId, int year, int month, int date , int time) throws IOException;
	
	void printCinemaSeats(int cinemaId, int year, int month, int date , int time);
	
	void CSVToCinema(CinemaSeat[] seat, int cinemaId, int year, int month, int date , int time);
		
}
