import java.util.*; 
import java.io.*;

public internetSpeedAnalysis {
    public static void main (String [] args) throws FileNotFoundException {

    }

    public static void compareSpeedByIncome (File dataset, ArrayList<String> tenLowest, ArrayList<String> tenHighest) throws FileNotFoundException {
        int averageSpeedOfHighIncome = 0; 
        int averageSpeedOfLowIncome = 0; 
    }   

    public static void compareSpeedByContinent (File dataset) throws FileNotFoundException {
        
    }

    public static int averageIncome10Lowest (File dataSet) {
        Scanner fileScan = new Scanner(dataset); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <String> averageIncome = new ArrayList<>(); 

    }

    public static String findHighestIncomeCountry (File dataSet) {
        Scanner fileScan = new Scanner(dataset); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        //ArrayList <String> averageIncome = new ArrayList<>(); 
        String highestIncomeCountry = ""; 
        
        int meanIncomeIndex = headerAsL.indexOf("meanIncome"); 
        String countryIndex = headerAsL.indexOf("country"); 

        String [] currLineArr = fileScan.nextLine().split(","); 
        ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
        highMeanIncome = currLineAsL.get(meanIncomeIndex); 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            
            highMeanIncome = currLineAsL.get(meanIncomeIndex); 
            if (currLineAsL.get(meanIncomeIndex).equals("")){
                double chocWinPercent = Double.parseDouble(currLineAsL.get(winIndex)); 
                chocolateWins.add(chocWinPercent); 
            }
            else {
                double nonChocWinPercent = Double.parseDouble(currLineAsL.get(winIndex)); 
                nonChocolateWins.add(nonChocWinPercent); 
            }

        }

        return highestIncomeCountry; 
    }

//list of all ountries ine one, list of all countries in the other, and then print out the countries that arent in the main set
    //find average income of 20 lowest countries 
    //get list of 20 lowest countries + their average income 
    //use list of 20 lowest countries + their average broadband 
    //method to find 20 lowest average income 

    //method to find 20 middle average income 

    //method to find 20 highest averaeg income 

    //find average broadband speed given  

    //find average broadband speed given 

}