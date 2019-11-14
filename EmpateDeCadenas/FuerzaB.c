#include<stdio.h>
#include<string.h>

void FuerzaBruta(char cadena[],char subc[], int n, int m){
	int i,j,k, cont=0;
	char aux[100];
	for(i=0; i<=n;i++){
		for(j=i,k=0;j<m;j++,k++)
			aux[k]=cadena[i+k];
		aux[k]='\0';
		if(strcmp(subc,aux)==0){
			cont++;}
		m++;
	}
	printf("Empata en %d \n", cont+1);
}

int main(void){
	char Cadena[]="navanvanvanvanavn";
	char SubC[]="anv";
	int n,m;
	n=strlen(Cadena);
	m=strlen(SubC);
	FuerzaBruta(Cadena,SubC,n,m);
	return 0;
	
}
