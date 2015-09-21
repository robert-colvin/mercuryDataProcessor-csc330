#include <stdio.h>
#include <string.h>
#include <stdlib.h> 
#include <math.h>



double radionConvert(double number){
double result=(number*M_PI)/180.0;
return result;
}

int main(){ 



FILE *mercuryFile;
mercuryFile=fopen("mercurydata.csv","r");
double Longitude[9999];
double Latitude[9999];
double Zee[9999];
int Narray[9999];
double SumArray[9999];

char info[100000]; 
char *pointer;
char *end;
double a=0;
int n=0;
double d=0;
double r= 6373000.0;
int count=0;
int c=0;
double Sum=0; 
int iclone;
double BigD=0;
static int z=0;
double lagMax=0.5;
double lagMin=0.0;

	while (fscanf(mercuryFile,"%s", info)!=EOF){
	pointer=strtok(info,", \n");
		Latitude[c]=radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n");  
  
Longitude[c]= radionConvert(atof(pointer));
	pointer=strtok(NULL,", \n"); 
	Zee[c]=atof(pointer);
        c=c+1;

	}


//LEZ DO SOME MATH!



do{
n=0;
Sum=0;
int i=1;
int j=i+1;
iclone=i;

	do{
	
	j=iclone+1;
	


		do{
		
		
		double dlat=Latitude[iclone]-Latitude[j];
		double dlong=Longitude[iclone]-Longitude[j];
		a= pow(sin(dlat/2.0),2) + cos(Latitude[iclone]) * cos(Latitude[j]) * pow(sin(dlong/2.0),2);
		
		d=r*(2 * atan2(sqrt(a),sqrt(1-a)));

	
				if(j<c)
				if(BigD<d)
				BigD=d;
				
	
				
				
		if(d<=lagMax && d>=lagMin){
		n=n+1;
		}
		j++;
		
		}while(j<c);
	
iclone=iclone+1;
}while(c>iclone);


do{
	
	j=i+1;
	


		do{
		
		
		double dlat=Latitude[i]-Latitude[j];
		double dlong=Longitude[i]-Longitude[j];
		a= pow(sin(dlat/2.0),2) + cos(Latitude[i]) * cos(Latitude[j]) * pow(sin(dlong/2.0),2);
		
		d=r*(2 * atan2(sqrt(a),sqrt(1-a)));

				
		if(d<=lagMax && d>=lagMin){
		Sum=Sum+pow((Zee[i]-Zee[j]),2)/(2*(n+0.0));
		}
		j++;
		
		}while(j<c);
	
i=i+1;
}while(c>i);
printf("%.10f\n",Sum);
printf("%i\n",n);
lagMin=lagMin+0.5;
lagMax=lagMax+0.5;
}while(n!=0); //This condition is wrong

}

