import java.io.*;
import java.util.*;
import java.lang.*;

public class Gertrude 
{
	public static int n = 0;

	public static void main(String[] args) 
	{
		double testy = 0.0;
		ArrayList<Double> latitudes = new ArrayList<Double>();
		ArrayList<Double> longitudes = new ArrayList<Double>();
		ArrayList<Double> concentrations = new ArrayList<Double>();
		
		try
		{
		    File reader = new File("mercurydata.csv");;
		    Scanner in = new Scanner(reader);

		    while (in.hasNextLine()) 
		    {
		        String line = in.nextLine().trim();
		        StringTokenizer zeus = new StringTokenizer(line, ",");
		        latitudes.add(Math.toRadians(Double.parseDouble(zeus.nextToken())));
		        longitudes.add(Math.toRadians(Double.parseDouble(zeus.nextToken())));
		        concentrations.add(Double.parseDouble(zeus.nextToken()));
			n++;
		        
		    } 
		    for (int i = 0; i < latitudes.size()-1; i++)
		    {
			for (int j = i+1; j < latitudes.size(); j++)
			{
				double compared = getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j));
				if (compared > testy)
					testy = compared;
			}
			
		    }
			System.out.println(testy);
		/*System.out.println(getDistance(37.0, 32.0, -122.0, -83.0));
		System.out.println(getDistance(Math.toRadians(37.0), Math.toRadians(32.0), Math.toRadians(-122.0), 
														Math.toRadians(-83.0)));
		System.out.println(getDistance(32.8347, 33.7550, -83.6517, -84.3900));
		System.out.println(getDistance(Math.toRadians(32.8347), Math.toRadians(33.7550), Math.toRadians(-83.6517), 
														Math.toRadians(-84.3900)));*/

		}
		catch (IOException ex) 
		{
		    System.out.println("WEB URL NOT FOUND.");
		}
	}
	public static double getDistance(Double lat1, Double lat2, Double long1, Double long2)
	{
		//lat1 = Math.toRadians
		double r = 6373000.0;
		double a = (Math.pow(Math.sin((lat1-lat2)/2), 2)) + Math.cos(lat1) * Math.cos(lat2) * (Math.pow(Math.sin((long1-long2)/2), 2));
		double d = r * (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));	

		return d;

	}
	//CRAZY EXPERIMENTAL MATH GARBAGE
	/*for (int i = 0; i < n; i++)
	{
		for (int j = i+1; 
	}*/

}
