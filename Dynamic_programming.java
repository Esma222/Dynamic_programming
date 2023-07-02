import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


public class Esma_Beydili_2020510018 {
   
    static int min(int[] minarray)
    {
    	int min = minarray[0];  
        //Loop through the array  
        for (int b = 0; b < minarray.length; b++) {  
            //Compare elements of array with min  
           if(minarray[b] <min)  
               min = minarray[b];  
        }  
          return min;
    }
    
    
    // Returns the minimum value that can
    static int dpPlayer(int N, int salary[],
                        int demand[], int n,int p,int c)
    {
        int i, w;
        int K[][] = new int[n + 1][N + 1];
        int K2[][] = new int[n + 1][N + 1];
 
       //
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= N; w++)
            {
            	if (i == 0 )
            	{	if(w!=0){
            	         K[i][w] = salary[w-1];
            	     }
            	    else {
            		K[i][w] = 0;
            	     }
            	}            	
            	else if(demand[i-1]>p) {
            		
            		int arrMin[]=new int[(demand[i-1]-p)+w+1] ;//bir indeksin alabileceði tüm olasý deðerleri içinde bulundurur
            		   if(w>=0&&w<p)
            		   {
            			for(int m=(demand[i-1]-p)+w;m>=0;m--) {
            				arrMin[m]= (K[i-1][m]+ (((demand[i-1]-p)+w-m)*c))+ K[0][w];//sondaki k[0][w] hep salary ekler, 0. salary olmadýðý için salary arrayini kullanmadým
            				
            			}
            			
            			int min = min(arrMin);
            			
            			for(int j=0; j<arrMin.length;j++) {
            				if(arrMin[j]==min)
            				{
            					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
            				}
            			}
            			K[i][w]=min;
            			
            		   }
            		   else if(w>=p&&w<demand[i-1]){
            			
            			   for(int m=(demand[i-1]-p)+w;m>=0;m--) {
               				arrMin[m]= (K[i-1][m]+ (((demand[i-1]-p)+w-m)*c))+ K[0][w];
               			}
            			   int min = min(arrMin);
            			   
               			for(int j=0; j<arrMin.length;j++) {
               				if(arrMin[j]==min)
               				{
               					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
               				}
               			}
               			K[i][w]=min;
            			   
            		   }
            		   else if(w>=demand[i-1]&& w<=N) {
            			
            			   if(arrMin.length<=N+1)
            			   {for(int m=(demand[i-1]-p)+w;m>=0;m--) {
               				arrMin[m]= (K[i-1][m]+ (((demand[i-1]-p)+w-m)*c))+ K[0][w];
               			}
            			   int min = min(arrMin);
               			for(int j=0; j<arrMin.length;j++) {
               				if(arrMin[j]==min)
               				{
               					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
               				}
               			}
               			K[i][w]=min;
            			   }
            			   else {
            				   int arrMinN[]=new int[N+1] ;
            				   
            				   for(int m=N;m>=0;m--) {
                      				arrMinN[m]= (K[i-1][m]+ (((N)-m)*c))+ K[0][w];
                      			}
            				   int min = min(arrMinN);
                   			
                   			for(int j=0; j<arrMinN.length;j++) {
                   				if(arrMinN[j]==min)
                   				{
                   					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
                   				}
                   			}
                   			K[i][w]=min;
            			   }
            			   
            		   }
            		
            			
            		
            	}
            	///////////////////////////////demandin p ye eþit ya da küçük olduðu durumlar için
            	else {
            		int arrMin[]=new int[(p-demand[i-1])+w] ;//bir indeksin alabileceði tüm olasý deðerleri içinde bulundurur
            		
                    if(w>=0&&w<demand[i-1]) {
                    	if(arrMin.length==0) {
                    		int arrMinN[]=new int[1] ;
                    		 for(int m=0;m>=0;m--) {
                    			 arrMinN[m]= K[i-1][m]+ K[0][w];
                   			}
                    		 int min = min(arrMinN);  
                 			
                 			for(int j=0; j<arrMinN.length;j++) {
                 				if(arrMinN[j]==min)
                 				{
                 					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
                 				}
                 			}
                 			K[i][w]=min;
                    	}
                        else{
                    	for(int m=(p-demand[i-1])+w-1;m>=0;m--) {
            				arrMin[m]= K[i-1][m]+ K[0][w];
            			}
                    	int min = min(arrMin); 
            			
            			for(int j=0; j<arrMin.length;j++) {
            				if(arrMin[j]==min)
            				{
            					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
            				}
            			}
            			K[i][w]=min;
                    	}
                    	
            		}
            		else if(w>=demand[i-1]&&w<p){
            			
            			for(int m=(p-demand[i-1])+w-1;m>=0;m--) {
            				arrMin[m]= (K[i-1][m]+ ((p-(demand[i-1])+w-m-1)*c))+ K[0][w];
            			}
            			int min = min(arrMin); 
            			
            			for(int j=0; j<arrMin.length;j++) {
            				if(arrMin[j]==min)
            				{
            					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
            				}
            			}
            			K[i][w]=min;
            			
            			
            		}
            		else if(w>=p&& w<=N ) {
            			
            			if(arrMin.length<=N+1)
            			{for(int m=(p-demand[i-1])+w-1;m>=0;m--) {
            				arrMin[m]= (K[i-1][m]+ ((p-(demand[i-1])+w-m-1)*c))+ K[0][w];
            			}
            			int min = min(arrMin); 
            			
            			for(int j=0; j<arrMin.length;j++) {
            				if(arrMin[j]==min)
            				{
            					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
            				}
            			}
            			K[i][w]=min;
            		   } else {
     				   int arrMinN[]=new int[N+1] ;
    				   
     				   for(int m=N;m>=0;m--) {
               				arrMinN[m]= (K[i-1][m]+ (((N)-m)*c))+ K[0][w];
               			}
     				  int min = min(arrMinN);  
          			
          			for(int j=0; j<arrMinN.length;j++) {
          				if(arrMinN[j]==min)
          				{
          					K2[i][w]= j;//minimum cost için nereden geldiðini bulmak için
          				}
          			}
          			K[i][w]=min;
     			   }
            			
            		}
            		
            	}
            	
            	
          
                }
            }
       System.out.println("**************************");//minimum costun nereden geldiðini yazdýrmak için
        int x=0;
        for(int a=n;a>=0;a--) {
        	System.out.println("["+a+","+x+"] : "+K[a][x]);
        	x=K2[a][x];
        	
        }
        System.out.println("**************************");
        
            return K[i-1][0];
        }
    
 
    
        // Driver code
        public static void main(String args[])throws FileNotFoundException
        {  
        	
        	
         	 int demand[] = FileReadConvertArray("yearly_player_demand.txt");
             int salary[] = FileReadConvertArray("players_salary.txt");
             int numberOfPlayer=0;
             for(int i=0;i<demand.length;i++) {
              	numberOfPlayer+=demand[i];
              }
             int n = 3;   int p=5; int c=5;
             System.out.println("DP results : "+dpPlayer(numberOfPlayer, salary, demand, n,p,c));
        	
        	
        }

        public static int[] FileReadConvertArray(String txt_name) throws FileNotFoundException
        {
        	 File student = new File(txt_name);
             Scanner idLineReader = new Scanner(student);
             
             int idAmount = 0;
             while (idLineReader.hasNextLine()) {
             	idAmount++;
             	idLineReader.nextLine();
             }
             
             idLineReader.close();
             
             int[] idArr = new int [idAmount-1];
             int num=0;

            Scanner idReader = new  Scanner(student);
             while (idReader.hasNextLine()) {
            	 
             	String[] line = idReader.nextLine().split("	");
             	if(num!=0)
             	{ //System.out.println(line[0]);
             		idArr[num-1]=Integer.parseInt(line[1]);
             	}
             	num++;
             }
             idReader.close();
             //System.out.println("***");
             
             return idArr;
        }
	
	
	
}

