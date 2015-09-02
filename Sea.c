#include <stdio.h>


int main(){

FILE *mercuryFile;
mercuryFile=fopen("mercurydata.csv","r");
char info[10000]; //When this was an ara it printed correc thins sort of

while (fscanf(mercuryFile,"%s", info)!=EOF){

printf("%s",info);   
}
}     
