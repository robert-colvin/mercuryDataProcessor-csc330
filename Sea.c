#include <stdio.h>
#include <string.h>
#include <stdlib.h> 

int main(){ 

FILE *mercuryFile;
mercuryFile=fopen("mercurydata.csv","r");
char info[100000]; 
char *pointer;
char *end;
while (fscanf(mercuryFile,"%s", info)!=EOF){
pointer=strtok(info,", \n");
while(pointer!=NULL){
double Data=atof(pointer);    
printf("%.10f\n",Data); 
pointer=strtok(NULL,", \n");        
}
printf("\n",NULL);
}
}     
