#include<stdio.h> 
#include<string.h> 
#define d 256 

void Rabin(char SubC[], char Cadena[], int q){ 
	int M=strlen(SubC); 
	int N=strlen(Cadena);
	int i, j; 
	int p=0;
	int t=0; 
	int h=1; 
	
	for(i=0;i<M-1;i++) 
		h=(h*d)%q; 
	for(i=0;i<M;i++){ 
		p=(d*p+SubC[i])%q; 
		t=(d*t+Cadena[i])%q; 
	} 
	for(i=0;i<=N-M;i++){ 
		if(p==t){ 
			for(j=0;j<M;j++){ 
				if(Cadena[i+j]!=SubC[j]) 
					break; 
			} 
			if(j==M) 
				printf("Empata en %d \n", i+1); 
		} 
		if(i<N-M) { 
			t=(d*(t-Cadena[i]*h)+Cadena[i+M])%q; 
			if(t<0) 
				t=(t+q); 
		} 
	} 
} 


int main(){ 
	char Cadena[]="navanvanvanvanavny"; 
	char SubC[]="anv"; 
	int q=7;
	Rabin(SubC, Cadena, q); 
	return 0; 
} 
