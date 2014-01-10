/*
 * JohannMG 
 * Created: Jan 8, 2014 (happy new year!)
 * For 
 */

package numeralconverter;

import java.io.*;

/**
 *
 * @author JohannMG
 */
public class NumeralConverter {

    /**
     * @param args the command line arguments
     * input file name (if local)
     * or input file path (if not stored at the current directory)
     */
    public static void main(String[] args) {

        String user_file; 
        if (args[0] == null){						//quit if no file provided
            System.out.println("Program Quit: No file was specified.");
            System.exit(0);
        }

        //get the user provided file
        user_file = args[0]; 

    }

    /**
    *@param string is the file path containing the roman numerals in a txt file
    *
    */
    public String getRomansFromFile(String filePath){

    	File targetFile = new File(filePath); 
    	String fileText = ""; 
    	BufferedReader reader = null;

    	try{
    		reader = new BufferedReader(new FileReader(targetFile));
                while(reader.ready()){
                    fileText += reader.readLine();
                }
    	}

    	catch (FileNotFoundException  e){
            System.out.println("No file was found at path: " + filePath);
        }
        catch(IOException e){
            System.out.println("There was a problem reading the file.");
        }   
        finally{
            try{ 
               reader.close();
            }
            catch (Exception e){}
        }

        return fileText;

    }

    /**
    * @param romans String with roman numeral text. 
    * Can be in any case as long as all characters are roman numerals
    */
    public int calculateTotalValue(String romans){

    	/**
    	* So technically there are vague rules that as to what should be used 
    	* to subtract but they are inconsistent. Therefore I'm just assuming 
    	* that if the Roman numeral smaller than the 'digit' directly to the right
    	* it becomes subtractive
	*
    	* for example in hypothetical numeral 'ab' if 'a' and 'b' were numerals
	*     	if a < b  	 then -a +b
	*     	and 
	*     	if a >= b 	 then +a +b
    	*/

    	int 	totalVal = 0; 
    	int[] 	valuesArray = new int[romans.length()]; //array of roman values

    	//if empty string
    	if (romans.length() < 1) 
    		return 0;

    	//if one single digit
    	if (romans.length() == 1) 
    		return this.getValueOfRomanFigure(romans.charAt(0));

    	//turn romans into array of ints
    	for (int i = 0; i < romans.length(); i++){
    		valuesArray[i] = getValueOfRomanFigure(romans.charAt(i));
    	}

    	//turn all values that are smaller than thier following, negative. 
    	for (int i = 0; i < valuesArray.length-1; i++){
    		if (valuesArray[i] < valuesArray[i+1]){
    			valuesArray[i] =  -1 * valuesArray[i];
    		}
    	}

    	//final loop to sum all array items of roman numerals
    	for (int iter : valuesArray)
    		totalVal += iter;

    	return totalVal; 
    }

    /**
     * @param char - a roman numeral character. 
     * Lowercase and Uppercase both allowed
     * returns INT value
     * returns -1 if there unexpected character (non-Roman)
    */
    public int getValueOfRomanFigure(char romanChar){

    	//Make uppercase to reduce switch statements
    	romanChar = Character.toUpperCase(romanChar); 

    	switch (romanChar) 
    	{
    		case 'I': 	return 1; 

    		case 'V': 	return 5; 

    		case 'X':	return 10;

    		case 'L': 	return 50;

    		case 'C': 	return 100;

    		case 'D': 	return 500; 

    		case 'M': 	return 1000; 

    		default: 	return -1;    		    		
    	}//end switch

    	//return null;  
    }//end method getValueOfRomanFigure
    
}//end class
