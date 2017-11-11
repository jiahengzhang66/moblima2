package moblima;

import java.util.Date;


public class MovieDigital extends Movies{
	
		public MovieDigital(String title,int movieID,String releaseDate,String typeOfMovie, String genre,String language,
				String synopsis,String status,String ageRating,String actors, String director, Boolean blockBuster, double totalRating)
				{
				super (title,movieID, releaseDate, typeOfMovie, genre, language,
						synopsis, status, ageRating, actors, director, blockBuster, totalRating);
		}
	
	private float movieTypePriceModifier= 2.0f;//default price 
	
	
	private float movieTypePriceModifierFunction () {
		if (this.getBlockBuster()==true) {
			return movieTypePriceModifier*2;
		}
		else {
			return movieTypePriceModifier;
		}
	}
	public float getPriceModifier () {
		return movieTypePriceModifierFunction();
	}
}