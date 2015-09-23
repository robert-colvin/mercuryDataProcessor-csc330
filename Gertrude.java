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
		int maxD = 0;
		int pointsInLag = 0;
		double totalD = 0.0;
		double mercs = 0.0;
		
		try
		{//GRABBING/STORING DATA FROM FILE
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

		}
		catch (IOException ex) 
		{
		    System.out.println("WEB URL NOT FOUND.");
		}

		//MAX DISTANCE LOOP
		for (int i = 0; i < latitudes.size()-1; i++)
		{
			for (int j = i+1; j < latitudes.size(); j++)
			{
				double compared = getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j));
				if (compared > testy)
					testy = compared;
			}
			//SAVE MAX DISTANCE AS NEAREST INT SO THAT LAGS CAN COMPLETELY INCREMENT BY 1
			maxD=Math.round((float) testy);
		}


		//POPULATING THE DISTANCE MATRIX, HENCE THE NAME LONGNEO
		double[][] longNeo = new double[latitudes.size()][latitudes.size()];
		for (int i = 0; i < latitudes.size()-1;i++)
		{
			for (int j = i+1; j < latitudes.size(); j++)
			{
				longNeo[i][j] = getDistance(latitudes.get(i), latitudes.get(j), longitudes.get(i), longitudes.get(j));
			}
		}
		//System.out.println(longNeo[0][0]);
		//CALCULATE SEMIVARIANCE WITH SOME WILD EQUATION I CANT QUITE GRASP

		for (int lag = 0; lag <= maxD; lag++)
		{
			double semivariance = 0.0;
			
			for (int i = 0; i<longNeo.length;i++)
			{
				for (int j = i+1; j< longNeo.length;j++)
				{
					if ((double) lag - 0.5 <= longNeo[i][j] 
					&& longNeo[i][j] <= (double) lag + 0.5)
					{
						mercs = Math.pow(concentrations.get(i)-concentrations.get(j), 2);
						semivariance += mercs;
						totalD += longNeo[i][j];
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
