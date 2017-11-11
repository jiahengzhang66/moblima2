package moblima;

import java.util.Date;
import java.util.*;



public class Movies {
	
	
private String title;
	
	private int movieID;
	
	private String releaseDate; 
	
	private String genre;
	
	private String language;
	
	private String synopsis;
	
	private String status;
	
	private String ageRating;
	
	private String actors;
	
	private String director;
	
	private Boolean blockBuster;
	
	private String typeOfMovie; 
	
	public Movies(String title,int movieID,String releaseDate, String typeOfMovie,String genre,String language,String synopsis,String status,String ageRating,String actors, String director, Boolean blockBuster) {
		this.title = title;
		this.movieID= movieID;
		this.releaseDate = releaseDate;
		this.typeOfMovie = typeOfMovie;
		this.genre = genre;
		this.language = language;
		this.synopsis = synopsis;
		this.status = status;
		this.ageRating = ageRating;
		this.actors = actors;
		this.director = director;
		this.blockBuster = blockBuster;
		}
	
	public String getTitle() {
		return title;
	}
	
	public int getMovieID() {
		return movieID;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getAgeRating() {
		return ageRating;
	}
	
	
	public String getActors() {
		return actors;
	}
	
	public String getDirector() {
		return director;
	}
	
	public Boolean getBlockBuster() {
		return blockBuster;
	}
	
	public void setTitle(String T) {
		title = T;
		
	}
	
	public void setMovieID(int ID) {
		movieID=ID;
	}
	
	public void setReleaseDate(String RD) {
		releaseDate = RD;
	}
	
	public void setLanguage(String L) {
		language = L;
	}
	public void setGenre(String kl) {
		this.genre = kl;
	}
	
	public void setSynopsis(String S) {
		synopsis = S;
	}
	
	public void setStatus(String ST) {
		status = ST;
	}
	
	public void setAgeRating(String AR) {
		ageRating = AR;
	}
	
	public void setActors(String A) {
		actors = A;
	}
	
	public void setDirector(String D) {
		director = D;
	}
	
	public void setBlockBuster(Boolean B) {
		blockBuster = B;
	}

	public String getTypeOfMovie() {
		return typeOfMovie;
	}
	
	public void setTypeOfMovie(String setTypeOfMovie) {
		this.typeOfMovie = setTypeOfMovie;
	}
	
	}