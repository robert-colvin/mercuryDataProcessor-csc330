import java.io.*;
import java.util.*;
import java.lang.*;

public class Gertrude 
{

	public static void main(String[] args) 
	{
		ArrayList<Double> latitudes = new ArrayList<Double>();
		ArrayList<Double> longitudes = new ArrayList<Double>();
		ArrayList<Double> concentrations = new ArrayList<Double>();
		
		try{
            File reader = new File("mercurydata.csv");;
            Scanner in = new Scanner(reader);

            while (in.hasNextLine()) 
            {
                String line = in.nextLine().trim();
                StringTokenizer zeus = new StringTokenizer(line, ",");
                latitudes.add(Double.parseDouble(zeus.nextToken()));
                longitudes.add(Double.parseDouble(zeus.nextToken()));
                concentrations.add(Double.parseDouble(zeus.nextToken()));
                
            } 
            for (int i = 0; i < latitudes.size(); i++)
	    {
		System.out.println("latitude: " + latitudes.get(i) + " longitude: " + longitudes.get(i) + " Hg concentration: " + concentrations.get(i)); 	
            }

        }
        catch (IOException ex) {
            System.out.println("WEB URL NOT FOUND.");
        }

	}

}
