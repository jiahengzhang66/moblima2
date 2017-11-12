package moblima;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class holidayDateList {

	public Date[] holidayDate = new Date[50];
	public Formatter x; // add to class diagram
	public Scanner sc;

	
	
	public holidayDateList() throws ParseException{	

		int index=0;

		openHolidayDatesFile();
		holidayDate[0] = new Date();
		
			while(sc.hasNext()) {
				holidayDate[index] = stringToDate(sc.nextLine());
				index++;
				}
			}
	
	
	private void openHolidayDatesFile() { //add to class diagram
		try {
			sc = new Scanner(new File("holidayDates.txt"));
		}
		catch(Exception e) {
			System.out.println("you have an error, could not find file");
		}
	}
	
	
	public String stringHolidayDate(String day, String month, String date, String year) {
		String d = day + ", " + month + " "+ date + ", " + year;
		return d;
	}
	
	
	public void addHolidayDate() throws IOException{//add to class diagram
		BufferedWriter writer = new BufferedWriter (new FileWriter("holidayDates.txt",true));
		System.out.println("Insert Day, month, date, then followed by year of the date you want to add");
		openHolidayDatesFile();
		String day, month, date, year;
		Scanner sc = new Scanner(System.in);
		day = sc.next();
		month = sc.next();
		date = sc.next();
		year = sc.next();
		String d = stringHolidayDate(day, month, date, year);
		writer.write("\n" + d);
		writer.close();
		sc.close();
	}
	
	private String dateToString(Date d) {
		openHolidayDatesFile();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		StringBuffer sb = new StringBuffer(dateFormatter.format(d));
		return sb.toString();
	}
	
	public Date stringToDate(String s) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		Date d = dateFormatter.parse(s);
		return d;
	}
	
	public void printList() throws ParseException {
		int index = 0;
		openHolidayDatesFile();
		holidayDate[0] = new Date();
		
			while(sc.hasNext()) {
				holidayDate[index] = stringToDate(sc.nextLine());
				index++;
		for (int i = 0; i < holidayDate.length; i++ ) {
			if (holidayDate[i] != null) {
			System.out.println(holidayDate[i]);
			}
		}
	}
	
	
	}
	public void removeDate() throws ParseException, FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader (new FileReader("holidayDates.txt"));
		BufferedWriter writer = new BufferedWriter (new FileWriter("holidayDates.txt",true));
		
		FileWriter write = new FileWriter("holidayDates.txt",true);
		System.out.println("Insert Day, month, date, then followed by year of the date you want to remove");
		openHolidayDatesFile();
		String day, month, date, year, line;
		Scanner sc = new Scanner(System.in);
		day = sc.next();
		month = sc.next();
		date = sc.next();
		year = sc.next();
		String d = stringHolidayDate(day, month, date, year);
		int counter = 0;
		String e = d;
		String replace = "Sunday, January 1, 1111"; //replace the date with a junk date so that there will not be errors

		
		System.out.println("test");
		while( (line = reader.readLine())!=null) {
			e = e.concat(line + "\n");
		}
		System.out.println(e);
		e = e.replace(d, replace);
		reader.close(); 
		sc.close();
		
		FileOutputStream fileOut = new FileOutputStream("holidayDates.txt");
		fileOut.write(e.getBytes()); 
		fileOut.close();
		
	}
	
}

//if (line.equals(d)) {
//	System.out.println(line);
//	String newline = line.replace(line, " ");
//	
//	fileOut.write(newline.getBytes());
//	fileOut.close();
//	System.out.println("line replaced");
//	
//	write.close();
//}
//else
//{
//	fileOut.write(line.getBytes());
//	System.out.println("Could not find date");
//}
