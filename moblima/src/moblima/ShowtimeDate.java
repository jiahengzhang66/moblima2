package moblima;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;

public class ShowtimeDate {

	public int MovieID; 
	public int cinemaID;
	public int cineplexID;
	public int year;
	public int month;
	public int day;
	public int time;
	
	public ShowtimeDate(int MovieID, int cineplexID, int cinemaID, int year, int month, int day) {
		this.MovieID = MovieID;
		this.cineplexID = cineplexID;
		this.cinemaID = cinemaID;
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
	};
	
	public void setMovieID(int MovieID) {
		this.MovieID = MovieID;
	}
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}
	
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getMovieID() {
		return this.MovieID;
	}
	public int getCineplexID() {
		return this.cineplexID;
	}
	public int getCinemaID() {
		return this.cinemaID;
	}
	
	public int getYear() {
		return this.year;
	}
	public int getMonth() {
		return this.month;
	}
	public int getDay() {
		return this.day;
	}
	public int getTime() {
		return this.time;
	}
}

	
