import java.io.*;
import java.util.*;
import java.lang.*;

public class Gertrude 
{
 
//MAKE A CLASS FOR LAG POINTS
	public static void main(String[] args) 
	{
		double testy = 0.0;

		ArrayList<Double> latitudes = new ArrayList<Double>();
		ArrayList<Double> longitudes = new ArrayList<Double>();
		ArrayList<Double> concentrations = new ArrayList<Double>();
		//ArrayList<Pair> pointy = new ArrayList<Pair>();
		int maxD = 769;
		int pointsInLag = 0;
		double totalD = 0.0;
		double mercs = 0.0;
		
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
		        
		    } 
		//MAX DISTANCE LOOP
		    /*for (int i = 0; i < latitudes.size()-1; i++)
		    {
			for (int j = i+1; j < latitudes.size(); j++)
			{
				double compared = getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j));
				if (compared > testy){
					testy = compared;
						xlat = latitudes.get(i);
						xlong = longitudes.get(i);
						ylat = latitudes.get(j);
						ylong = longitudes.get(j);}
			}

		    }
			System.out.println(testy);*/
		}
		catch (IOException ex) 
		{
		    System.out.println("WEB URL NOT FOUND.");
		}
			//CRAZY EXPERIMENTAL MATH GARBAGE

		for (int lag = 0; lag <= maxD; lag++)
		{
			double semivariance = 0.0;
			for (int i = 0; i < latitudes.size()-1;i++)
			{
				for (int j = i+1; j < latitudes.size();j++)
				{
					if ((double) lag - 0.5 <= getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j)) 					&& getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j)) <= (double) lag + 0.5)
					{
						mercs = Math.pow(concentrations.get(i)-concentrations.get(j), 2);
						semivariance += mercs;
						totalD += getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j));
						pointsInLag++;
					}
				}
			}
			System.out.println("The # of points in lag " + lag + " is " + pointsInLag);
			System.out.println("The average distance between points in lag " + lag + " is " + totalD/pointsInLag);
			semivariance = semivariance/(2*pointsInLag);
			System.out.println("semivariance for this lag is " + semivariance);
			System.out.println();

			pointsInLag = 0;
		}
	}
	public static double getDistance(Double lat1, Double lat2, Double long1, Double long2)
	{
		double r = 6373000.0;
		double a = (Math.pow(Math.sin((lat1-lat2)/2), 2)) + Math.cos(lat1) * Math.cos(lat2) * (Math.pow(Math.sin((long1-long2)/2), 2));
		double d = r * (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));	

		return d;

	}


}
