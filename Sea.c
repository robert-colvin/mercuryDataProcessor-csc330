#include <stdio.h>
#include <string.h>
#include <stdlib.h> 
#include <math.h>

int main(){ 

FILE *mercuryFile;
mercuryFile=fopen("mercurydata.csv","r");
double Longitude[9999];
double Latitude[9999];
double Zee[9999];
char info[100000]; 
char *pointer;
char *end;
int a=0;
int n=0;
int d=0;
int r= 6733000;
	while (fscanf(mercuryFile,"%s", info)!=EOF){
	pointer=strtok(info,", \n");
	Longitude[n]= atof(pointer);
	pointer=strtok(NULL,", \n");  
  	Latitude[n]=atof(pointer);
	pointer=strtok(NULL,", \n"); 
	Zee[n]=atof(pointer);
	printf("%.10f\n",Longitude[n]); 
	printf("%.10f\n",Latitude[n]); 
	printf("%.10f\n",Zee[n]); 
	n=n+1;
	}
int c=0;
//LEZ DO SOME MATH!
double Sum=0;
int i=1;
int j=i+1;
	do{
	i=i+1;
	j=i+1;
		do{
		c=c+1;
		//if distance is less than a thing
		Sum=Sum+pow((Zee[i]-Zee[j]),2)/(2*n+0.0);
		j=j+1;
		d=r*(2 * atan2(sqrt(a),sqrt(1-a)));
		
		}while(j<n);
	}while(i<n);
printf("%.10f\n",Sum);
printf("%i\n",n);
printf("%i\n",c);
}     
