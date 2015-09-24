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
double Zee[9999];
char info[1000]; 
char *pointer;

//Variables that will be calculated
double a=0;
int lagPoints=0;
double d=0;

//the constant 'r'
double r= 6373000.0;

//a counter
int c=0;

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
		Latitude[c]=radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n");  
	Longitude[c]= radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n"); 
	Zee[c]=atof(pointer);
        c=c+1;

	}

 i=1;
	do{
	
	 j=i+1;
	

		///Create distance matrix
		do{
		double dlat=Latitude[i]-Latitude[j];
		double dlong=Longitude[i]-Longitude[j];
		a= pow(sin(dlat/2.0),2) + cos(Latitude[i]) * cos(Latitude[j]) * pow(sin(dlong/2.0),2);
		
		d=r*(2 * atan2(sqrt(a),sqrt(1-a)));
			if(j<c){
				if(d>BigD)
				BigD=d;
			}	
				
				
		DistanceMatrix[i][j]=d;
				
				
		j++;
		
		}while(j<c);
	
	i=i+1;
	}while(c>i);


	
	//use matrix to calculate semivariance

	do{
	lagPoints=0;
	Sum=0;
	 i=1;
	dTotal=0;
		do{
		 j=i+1;
			do{
			d=DistanceMatrix[i][j];
			
			if(d<=lagVal+0.5 && d>=lagVal-0.5){
			dTotal+=d;
			Sum=Sum+pow((Zee[i]-Zee[j]),2);
			lagPoints++;}
		j++;

		}while(j<c);

	i=i+1;
	}while(i<c);
Sum/=(2*(lagPoints+0.0));
printf(" The # of points in lag %i is %i \n",lagVal,lagPoints);
printf("The average distance between points in lag %i is %f\n",lagVal,dTotal/(lagPoints+0.0));
printf("The semivariance for this lag is %f\n",Sum);
printf("\n");

lagVal++;
}while(BigD+0.6>lagVal); 

}

