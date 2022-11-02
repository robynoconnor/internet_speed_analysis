import java.util.*; 
import java.io.*;

public class internetSpeedAnalysis {
    public static void main (String [] args) throws FileNotFoundException {
        File incomes = new File("incomeData.csv"); 
        System.out.println(find20HighestIncomeCountry(incomes)); 
        //System.out.println(find20LowestIncomeCountry(incomes));
        File internetSpeeds = new File("internet speed data.csv"); 
        compareSpeedByIncome(internetSpeeds, find20LowestIncomeCountry(incomes), find20HighestIncomeCountry(incomes)); 
        File populations = new File("populations.csv"); 
        //System.out.println(find20MostPopulated(populations)); 
        //System.out.println(find20LeastPopulated(populations)); 
        //compareSpeedByPop(internetSpeeds, find20LeastPopulated(populations), find20MostPopulated(populations)); 
    }

    public static void compareSpeedByIncome (File dataSet, ArrayList<String> twentyLowest, ArrayList<String> twentyHighest) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");

        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header)); 

        int countryIndex = 0; 
        int broadbandIndex = 1;

        double totalSpeedHighIncome = 0; 
        double totalSpeedLowIncome = 0; 
        double averageSpeedHighIncome = 0; 
        double averageSpeedLowIncome = 0; 

        while (fileScan.hasNextLine()) { //parse through each line 
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            String currCountry = currLineAsL.get(countryIndex); 
            for (int i=0; i<twentyHighest.size(); i++) { 
                if (currCountry.equals(twentyHighest.get(i))) {//if country of current line is one of the countries with the 20 highest incomes...
                    totalSpeedHighIncome +=  Double.parseDouble(currLineAsL.get(broadbandIndex)); //adds corresponding broadband value
                }
            }

            for (int j = 0; j<twentyLowest.size(); j++) {
                if (currLineAsL.get(countryIndex).equals(twentyLowest.get(j))) {//if country of current line is one of the countries with the 20 lowest incomes...
                    totalSpeedLowIncome += Double.parseDouble(currLineAsL.get(broadbandIndex)); //adds corresponding broadband value
                }
            }
        }
        
        averageSpeedHighIncome = totalSpeedHighIncome/twentyHighest.size(); 
        averageSpeedLowIncome = totalSpeedLowIncome/twentyLowest.size(); 

        System.out.print("The average broadband speed of the 20 of the highest income-earning countries is "); 
        System.out.printf("%.2f", averageSpeedHighIncome); //rounds average broadband to 2 decimal places (for simplicity)
        System.out.println("."); 
        System.out.print("The average broadband speed of the 20 of the lowest income-earning countries is "); 
        System.out.printf("%.2f", averageSpeedLowIncome); 
        System.out.println("."); 
    }   

    public static void compareSpeedByPop (File dataSet, ArrayList<String> twentyLowest, ArrayList<String> twentyHighest) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");

        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header)); 

        int countryIndex = 0; 
        int broadbandIndex = 1;

        double totalSpeedHighPops = 0; 
        double totalSpeedLowPops = 0; 
        double averageSpeedHighPop= 0; 
        double averageSpeedLowPop= 0; 

        while (fileScan.hasNextLine()) { //parse through each line 
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            String currCountry = currLineAsL.get(countryIndex); 
            for (int i=0; i<twentyHighest.size(); i++) { 
                if (currCountry.equals(twentyHighest.get(i))) {//if country of current line is one of the high population countries...
                    totalSpeedHighPops +=  Double.parseDouble(currLineAsL.get(broadbandIndex)); //adds corresponding broadband value
                }
            }

            for (int j = 0; j<twentyLowest.size(); j++) {
                if (currLineAsL.get(countryIndex).equals(twentyLowest.get(j))) {//if country of current line is one of the low population countries...
                    totalSpeedLowPops += Double.parseDouble(currLineAsL.get(broadbandIndex)); //adds corresponding broadband value
                }
            }
        }
        
        averageSpeedHighPop = totalSpeedHighPops/twentyHighest.size(); 
        averageSpeedLowPop = totalSpeedLowPops/twentyLowest.size(); 

        System.out.print("The average broadband speed of 20 of the most populated countries is "); 
        System.out.printf("%.2f", averageSpeedHighPop); 
        System.out.println("."); 
        System.out.print("The average broadband speed of 20 of the least populated countries is "); 
        System.out.printf("%.2f", averageSpeedLowPop); //rounds average broadband to 2 decimal places (for simplicity)
        System.out.println("."); 
    }

    public static ArrayList<String> find20LowestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));

        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        int meanIncomeIndex = 2;
        int countryIndex = 0; //EDIT had to hard code 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            int currIncome = Integer.parseInt(currLineAsL.get(meanIncomeIndex)); 
            allAverageIncomes.add(currIncome);  
        }
        allAverageIncomes.sort(Comparator.naturalOrder());; //puts incomes into accending order
        ArrayList <Integer> twentyLowestIncomes = new ArrayList<>();
        ArrayList <String> twentyLowestIncomeCountries = new ArrayList<>(); 

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
                    twentyLowestIncomeCountries.add(currLineAsL2.get(countryIndex)); 
                }
            }
        }
        return twentyLowestIncomeCountries; 
    }

    public static ArrayList<String> find20HighestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        int meanIncomeIndex = 2;  
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

    //public static void matchCountryLists (File internetSpeed, File income) throws FileNotFoundException {
     
    // }

    public static ArrayList<String> find20LeastPopulated (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Double> allPopulations = new ArrayList<>(); 
        
        int populationIndex = 2;  
        int countryIndex = 1; //EDIT had to hard code for some reason bc i was getting -1 as an index 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            double currPop = Double.parseDouble(currLineAsL.get(populationIndex)); 
            allPopulations.add(currPop);  
        }
        allPopulations.sort(Comparator.naturalOrder());; //puts populations into accending order
        ArrayList <Double> twentyLeastPop = new ArrayList<>();
        ArrayList <String> twentyLeastPopCountries = new ArrayList<>(); 

        for (int i=0; i<20; i++) {
            twentyLeastPop.add(allPopulations.get(i)); //puts twenty lowest populations in one array list 
        }
      
        //goes back through population data to determine countries corresponding to 20 highest populations
        Scanner fileScan2 = new Scanner(dataSet); 
        while (fileScan2.hasNextLine()) {
            String [] currLineArr2 = fileScan2.nextLine().split(","); 
            ArrayList <String> currLineAsL2 = new ArrayList<> (Arrays.asList(currLineArr2)); 
            double currPop2 = 0; 
            try {
                currPop2 = Double.parseDouble(currLineAsL2.get(populationIndex));
            } catch (NumberFormatException e) {
                //e.printStackTrace(); 
            }
            for (int j=0; j<twentyLeastPop.size(); j++) {
                if (currPop2 == twentyLeastPop.get(j)) { //if this population corresponds to one of the highest populations
                    twentyLeastPopCountries.add(currLineAsL2.get(countryIndex)); //add country name to list of highest pop countries
                }
            }
        }
        return twentyLeastPopCountries; 
    }

    public static ArrayList<String> find20MostPopulated (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Double> allPopulations = new ArrayList<>(); 
        
        int populationIndex = 2;  
        int countryIndex = 1; //EDIT had to hard code for some reason bc i was getting -1 as an index 

        while (fileScan.hasNextLine()) {
            String[] currLineArr = fileScan.nextLine().split(","); 
            ArrayList <String> currLineAsL = new ArrayList<> (Arrays.asList(currLineArr)); 
            double currPop = Double.parseDouble(currLineAsL.get(populationIndex)); 
            allPopulations.add(currPop);  
        }
        allPopulations.sort(Comparator.naturalOrder());; //puts populations into accending order
        ArrayList <Double> twentyMostPop = new ArrayList<>();
        ArrayList <String> twentyMostPopCountries = new ArrayList<>(); 

        for (int i=allPopulations.size()-1; i>allPopulations.size()-21; i--) {
            twentyMostPop.add(allPopulations.get(i)); //twenty highest populations in one array list 
        }
      
        //goes back through population data to determine countries corresponding to 20 highest populations
        Scanner fileScan2 = new Scanner(dataSet); 
        while (fileScan2.hasNextLine()) {
            String [] currLineArr2 = fileScan2.nextLine().split(","); 
            ArrayList <String> currLineAsL2 = new ArrayList<> (Arrays.asList(currLineArr2)); 
            double currPop2 = 0; 
            try {
                currPop2 = Double.parseDouble(currLineAsL2.get(populationIndex));
            } catch (NumberFormatException e) {
                //e.printStackTrace(); 
            }
            for (int j=0; j<twentyMostPop.size(); j++) {
                if (currPop2 == twentyMostPop.get(j)) { //if this population corresponds to one of the highest populations
                    twentyMostPopCountries.add(currLineAsL2.get(countryIndex)); //add country name to list of highest pop countries
                }
            }
        }
        return twentyMostPopCountries; 
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

//czech republic 
//tonga 
//gabon 
//Dominia
//Samoa,2537,3418,6795,222.382
//Tuvalu,2099,2802,4470,11.312
//comoros
//Eswatini,1097,2091,9003,1201.67
//Timor-Leste,970,1147,3709,1341.296
//Sao Tome and Principe,901,1620,4145,227.38
//Republic of the Congo,892,1375,3835,5970.424
//South Sudan,793,1121,1234,10913.164
//Benin,699,989,3432,13352.864
//entral African Republic,491,891,986,5579.144
