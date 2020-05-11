import java.util.*;

class EncryptionProject{
	public static void main(String... args){
		System.out.println("Enter '1' for Caesar cipher");
		System.out.println("Enter '2' for Playfair cipher");
		System.out.println("Enter '0' to exit");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		if(input == 1){
			caesarMain();
		}
		else if(input ==2){
			playfairMain();
		}
		else if(input==0){
			System.exit(0);
		}
		else{
			throw new IllegalArgumentException("Option is invalid!");
		}
	}
	
		public static void caesarMain(){
			try{
				Scanner sc = new Scanner(System.in);
				System.out.println("Welcome to Caesar Cipher app!");
				System.out.println("Enter '1' to encrypt plain text");
				System.out.println("Enter '2' to decrypt cipher text");
				System.out.println("Enter '0' to return");
				int input = sc.nextInt();
			
				if(input == 1){
					System.out.print("Enter the plain text : ");
					String p = sc.next();
					System.out.print("Enter the number of shift(s) : ");
					int s = sc.nextInt();
					System.out.println("The cipher text for "+p+" is : ");
					System.out.println(encryptCaesar(p,s));
				}
				else if(input == 2){
					System.out.print("Enter the cipher text : ");
					String c = sc.next();
					System.out.print("Enter the number of shift(s) : ");
					int s = sc.nextInt();
					System.out.println("The plain text for "+c+" is : ");
					System.out.println(decryptCaesar(c, s));
				}
				else if(input == 0){
					main("Back!");
				}
				else{
					throw new IllegalArgumentException("Option is invalid!");
				}
			}
			catch(Exception e){}
		}
		public static String encryptCaesar(String p, int s){
			String c = "";
        		char ch;
        		for(int i=0; i < p.length();i++){
             			
            			ch = p.charAt(i);
        	    
	            		// if alphabet lies between a and z 
            			if(ch >= 'a' && ch <= 'z'){
             				
             				ch = (char) (ch + s);
          				
             				if(ch > 'z') {
                				 
                				ch = (char) (ch+'a'-'z'-1);
             				}
             				c = c + ch;
            			}
            
            			
            			else if(ch >= 'A' && ch <= 'Z') {
             				
             				ch = (char) (ch + s);    
                
             				
             				if(ch > 'Z') {
                 
                 				ch = (char) (ch+'A'-'Z'-1);
             				}
             				c = c + ch;
            			}
            			else {
             				c = c + ch;   
            			}
    			}
			return c;
		}
		public static String decryptCaesar(String c, int s){
        		String p = "";
        		for(int i=0; i < c.length();i++){
            			
            			char ch = c.charAt(i);
            			// if alphabet lies between a and z 
            			if(ch >= 'a' && ch <= 'z'){
                			
    					ch = (char) (ch - s);
            
              				
                			if(ch < 'a') {
                    				 
                    				ch = (char) (ch-'a'+'z'+1);
                			}
                			p = p + ch;
            			}    
                		
            			else if(ch >= 'A' && ch <= 'Z'){
             				
                			ch = (char)(ch - s);
                
                			
                			if (ch < 'A') {
                    				
                    				ch = (char) (ch-'A'+'Z'+1);
                			}
                			p = p + ch;            
            			}
            			else {
             				p = p + ch;            
            			}         	
			}
			return p;
		}




/*---------------------------------------------------------------------------------------------------------------------------------------------------
*/

		static char keyMatrix[][]=new char[5][5];

		
		public static void playfairMain(){
			try{
				Scanner sc = new Scanner(System.in);
				System.out.println("Welcome to Playfair Cipher app!");
				System.out.println("Enter '1' to encrypt plain text");
				System.out.println("Enter '2' to decrypt cipher text");
				System.out.println("Enter '0' to return");
				int input = sc.nextInt();
			
				if(input == 1){
					System.out.print("Enter the plain text : ");
					String p = sc.next();
					System.out.print("Enter an encryption key : ");
					String k = sc.next();
					if(p.length()%2==0){
						System.out.println("The cipher text for "+p+" is : ");
						String c = encryptPlayfair(p,k);
           					System.out.println("Encrypted text:");
           					System.out.println("---------------------------------------------------------\n"+c);
            					System.out.println("---------------------------------------------------------");
					}
					else{
						System.out.println("Enter text of even length!");
					}
				}
				else if(input == 2){
					System.out.print("Enter the cipher text : ");
					String c = sc.next();
					System.out.print("Enter the encryption key : ");
					String k = sc.next();
					if(c.length()%2==0){
						System.out.println("The plain text for "+c+" is : ");
						String p = decryptPlayfair(c,k);
						System.out.println("Decrypted text:" );
           					System.out.println("---------------------------------------------------------\n"+p);
            					System.out.println("---------------------------------------------------------");
					}
					else{
						System.out.println("Enter text of even length!");
					}
				}
				else if(input == 0){
					main("Back!");
				}
				else{
					throw new IllegalArgumentException("Option is invalid!");
				}
			}
			catch(Exception e){}
		}

  		public static boolean indexOfChar(char c){
			String allChar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  			for(int i=0;i < allChar.length();i++){
   				if(allChar.charAt(i)==c)
                            		return true;       
  				}
  			return false;
 		}
		
		public static boolean repeat(char c){
           		if(!indexOfChar(c)){
               			return true;
           		}
                	for(int i=0;i < keyMatrix.length;i++){
                    		for(int j=0;j < keyMatrix[i].length;j++){ 
                        		if(keyMatrix[i][j]==c || c=='J')
	                            		return true;
                    		}
                	}
                	return false;
       		}

		public static void insertKey(String k){
            		k=k.toUpperCase();
            		k=k.replaceAll("J", "I");
            		k=k.replaceAll(" ", "");
            		int a=0,b=0;
             
            		for(int i=0;i < k.length();i++){
                    		if(!repeat(k.charAt(i))){
                        		keyMatrix[a][b++]=k.charAt(i);
                        		if(b>4){
						b=0;
                            			a++;
                        		}
                    		}
            		}
            
            		char p='A';
             
            		while(a < 5){
                   		while(b < 5){
                        		if(!repeat(p)){
                            			keyMatrix[a][b++]=p;    
                        		}
                      			p++;
                   		}
                   		b=0;
                   		a++;
            		}
             		System.out.print("-------------------------Key Matrix-------------------");
            		for(int i=0;i < 5;i++){
                		System.out.println();
                		for(int j=0;j < 5;j++){
                    			System.out.print("\t"+keyMatrix[i][j]);
                		}
            		}
            		System.out.println("\n---------------------------------------------------------"); 
       		}

		public static int rowPos(char c){
             		for(int i=0;i < keyMatrix.length;i++){
                    		for(int j=0;j < keyMatrix[i].length;j++){ 
                        		if(keyMatrix[i][j]==c)
                            			return i;
                    		}
                	}
             		return -1;
       		}
        
       		public static int columnPos(char c){
             		for(int i=0;i < keyMatrix.length;i++){
                    		for(int j=0;j < keyMatrix[i].length;j++){ 
                        		if(keyMatrix[i][j]==c)
                            			return j;
                    		}
                	}
             		return -1;
       		}

		public static String encryptChar(String p){
           		p=p.toUpperCase();
           		char a=p.charAt(0),b=p.charAt(1);
           		String cipherChar="";
           		int r1,c1,r2,c2;
           		r1=rowPos(a);
           		c1=columnPos(a);
           		r2=rowPos(b);
          		c2=columnPos(b);
         
           		if(c1==c2){
                		++r1;
               			++r2;
               			if(r1>4)
                   			r1=0;
                
               			if(r2>4)
                   			r2=0;
               			cipherChar+=keyMatrix[r1][c2];
               			cipherChar+=keyMatrix[r2][c1];
           		}
           		else if(r1==r2){    
               			++c1;
               			++c2;
               			if(c1>4)
                   			c1=0;
                
               			if(c2>4)
                   			c2=0;
               			cipherChar+=keyMatrix[r1][c1];
               			cipherChar+=keyMatrix[r2][c2];
                
           		}
           		else{
               			cipherChar+=keyMatrix[r1][c2];
               			cipherChar+=keyMatrix[r2][c1];
           		}
           		return cipherChar;
       		}
		

		public static String encryptPlayfair(String p,String k){
			insertKey(k);
           		String c="";
           		p=p.replaceAll("j", "i");
        		p=p.replaceAll(" ", "");
           		p=p.toUpperCase();
           		int len=p.length();
          
           		if(len/2!=0){
               			p+="X";
               			++len;
           		}
            
           		for(int i=0;i < len-1;i=i+2){
              			c+=encryptChar(p.substring(i,i+2));
              			c+=" "; 
           		}
           		return c;
		}

		public static String decryptChar(String c){
           		c=c.toUpperCase();
           		char a=c.charAt(0),b=c.charAt(1);
           		String plainChar="";
           		int r1,c1,r2,c2;
          		r1=rowPos(a);
           		c1=columnPos(a);
           		r2=rowPos(b);
           		c2=columnPos(b);
         
           		if(c1==c2){
                		--r1;
               			--r2;
               			if(r1 < 0)
                   			r1=4;
                
               			if(r2 < 0)
                   			r2=4;
               			plainChar+=keyMatrix[r1][c2];
               			plainChar+=keyMatrix[r2][c1];
           		}
           		else if(r1==r2){    
               			--c1;
               			--c2;
               			if(c1 < 0)
                   			c1=4;
                
               			if(c2 < 0)
                   			c2=4;
               			plainChar+=keyMatrix[r1][c1];
               			plainChar+=keyMatrix[r2][c2];
                
           		}
           		else{
               			plainChar+=keyMatrix[r1][c2];
               			plainChar+=keyMatrix[r2][c1];
           		}
           		return plainChar;
       		}

		public static String decryptPlayfair(String c,String k){
			insertKey(k);
			String p="";
           		c=c.replaceAll("j", "i");
           		c=c.replaceAll(" ", "");
           		c=c.toUpperCase();
           		int len=c.length();
           		for(int i=0;i < len-1;i=i+2){
              			p+=decryptChar(c.substring(i,i+2));
              			p+=" ";  
           		}
           		return p;
		}
}