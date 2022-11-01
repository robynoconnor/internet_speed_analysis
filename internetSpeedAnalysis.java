import java.util.*; 
import java.io.*;

public class internetSpeedAnalysis {
    public static void main (String [] args) throws FileNotFoundException {
        File incomes = new File("incomeData.csv"); 
        System.out.println(find20HighestIncomeCountry(incomes)); 
        System.out.println(find20LowestIncomeCountry(incomes));
    }

    // public static void compareSpeedByIncome (File dataset, ArrayList<String> tenLowest, ArrayList<String> tenHighest) throws FileNotFoundException {
    //     int averageSpeedOfHighIncome = 0; 
    //     int averageSpeedOfLowIncome = 0; 
    // }   

    // public static void compareSpeedByContinent (File dataset) throws FileNotFoundException {
        
    // }

    public static ArrayList<String> find20LowestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        int meanIncomeIndex = headerAsL.indexOf("meanIncome");  
        int countryIndex = 0; //EDIT had to hard code for some reason bc i was getting -1 as index of country 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            int currIncome = Integer.parseInt(currLineAsL.get(meanIncomeIndex)); 
            allAverageIncomes.add(currIncome);  
        }
        allAverageIncomes.sort(Comparator.naturalOrder());; //puts incomes into accending order
        ArrayList <Integer> twentyLowestIncomes = new ArrayList<>();
        ArrayList <String> twentyLowestCountries = new ArrayList<>(); 

        for (int i=0; i<20; i++) {
            twentyLowestIncomes.add(allAverageIncomes.get(i)); //twenty lowest incomes in one array list 
        }
    
        //goes back through income data to determine countries corresponding to 20 lowest incomes
        Scanner fileScan2 = new Scanner(dataSet); 
        while (fileScan2.hasNextLine()) {
            String [] currLineArr2 = fileScan2.nextLine().split(","); 
            ArrayList <String> currLineAsL2 = new ArrayList<> (Arrays.asList(currLineArr2)); 
            int currIncome2 = 0; 
            try {
                currIncome2 = Integer.parseInt(currLineAsL2.get(meanIncomeIndex));
            } catch (NumberFormatException e) {
                //e.printStackTrace(); //NEED??????
            }
            for (int j=0; j<twentyLowestIncomes.size(); j++) {
                if (currIncome2 == twentyLowestIncomes.get(j)) {
                    twentyLowestCountries.add(currLineAsL2.get(countryIndex)); 
                }
            }
        }
        return twentyLowestCountries; 
    }

    public static ArrayList<String> find20HighestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        int meanIncomeIndex = headerAsL.indexOf("meanIncome");  
        int countryIndex = 0; //EDIT had to hard code for some reason bc i was getting -1 as index of country 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            int currIncome = Integer.parseInt(currLineAsL.get(meanIncomeIndex)); 
            allAverageIncomes.add(currIncome);  
        }
        allAverageIncomes.sort(Comparator.naturalOrder());; //puts incomes into accending order
        ArrayList <Integer> twentyHighestIncomes = new ArrayList<>();
        ArrayList <String> twentyHighestCountries = new ArrayList<>(); 

        for (int i=allAverageIncomes.size()-1; i>allAverageIncomes.size()-21; i--) {
            twentyHighestIncomes.add(allAverageIncomes.get(i)); //twenty highest incomes in one array list 
        }
        System.out.println(twentyHighestIncomes.size()); 
        //goes back through income data to determine countries corresponding to 20 highest incomes
        Scanner fileScan2 = new Scanner(dataSet); 
        while (fileScan2.hasNextLine()) {
            String [] currLineArr2 = fileScan2.nextLine().split(","); 
            ArrayList <String> currLineAsL2 = new ArrayList<> (Arrays.asList(currLineArr2)); 
            int currIncome2 = 0; 
            try {
                currIncome2 = Integer.parseInt(currLineAsL2.get(meanIncomeIndex));
            } catch (NumberFormatException e) {
                //e.printStackTrace(); //NEED??????
            }
            for (int j=0; j<twentyHighestIncomes.size(); j++) {
                if (currIncome2 == twentyHighestIncomes.get(j)) {
                    twentyHighestCountries.add(currLineAsL2.get(countryIndex)); 
                }
            }
        }
        return twentyHighestCountries; 
    }
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

