#include <stdio.h>
#include <string.h>
#include <stdlib.h> 
#include <math.h>


//Converts decimals to radians
double radionConvert(double number){
double result=(number*M_PI)/180.0;
return result;
}

int main(){ 

//Set up a bunch of variables

//File setup
FILE *mercuryFile;
mercuryFile=fopen("mercurydata.csv","r");
//i and j loop variables
int i=1;
int j=i+1;

//Variables for values read.
double Longitude[9999];
double Latitude[9999];
double Zee[9999]; //Zee is the concentration value Array, named for being the z value in x,y,z as I didn't know what it was at the time.
char info[1000]; 
char *pointer;

//Variables that will be calculated
double a=0;
int lagPoints=0;
double distance=0;
//the constant 'r'
double r= 6373000.0;

//a counter
int count=0;

//Sum of the data given at end of last loop 
double Sum=0; 

//The biggest distance
double BigD=0;

//Total distance
double dTotal=0;

//Lag value
int lagVal=0;

//The distance matrix used to increase speed
static double DistanceMatrix[3260][3260];


//Read in all values
while (fscanf(mercuryFile,"%s", info)!=EOF){
	pointer=strtok(info,", \n");
		Latitude[count]=radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n");  
	Longitude[count]= radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n"); 
	Zee[count]=atof(pointer);
        count=count+1;

	}

 i=0;
	do{
	
	 j=i+1;
	

		///Create distance matrix
		do{
		double dlat=Latitude[i]-Latitude[j];
		double dlong=Longitude[i]-Longitude[j];
		a= pow(sin(dlat/2.0),2) + cos(Latitude[i]) * cos(Latitude[j]) * pow(sin(dlong/2.0),2);
		
		distance=r*(2 * atan2(sqrt(a),sqrt(1-a)));
			//get biggest distance
			if(j<count){
				if(distance>BigD)
				BigD=distance;
			}	
				
				
		DistanceMatrix[i][j]=distance;
				
				
		j++;
		
		}while(j<count);
	
	i=i+1;
	}while(count>i);


	
	//use matrix to calculate semivariance

	do{
	lagPoints=0;
	Sum=0;
	 i=0;
	dTotal=0;
		do{
		 j=i+1;
			do{
			distance=DistanceMatrix[i][j];
			
			if(distance<=lagVal+0.5 && distance>=lagVal-0.5){
			dTotal+=distance;
			Sum=Sum+pow((Zee[i]-Zee[j]),2);
			lagPoints++;
			}
		j++;

		}while(j<count);

	i=i+1;
	}while(i<count);
Sum/=(2*(lagPoints+0.0));
printf(" The # of points in lag %i is %i \n",lagVal,lagPoints);
printf("The average distance between points in lag %i is %f\n",lagVal,dTotal/(lagPoints+0.0));
printf("The semivariance for this lag is %f\n",Sum);
printf("\n");

lagVal++;
}while(BigD+0.6>lagVal); 

}

