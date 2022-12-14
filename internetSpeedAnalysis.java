import java.util.*; 
import java.io.*;

public class internetSpeedAnalysis {
    public static void main (String [] args) throws FileNotFoundException {
        File incomes = new File("incomeData.csv"); 
        File internetSpeeds = new File("internet speed data.csv"); 
        compareSpeedByIncome(internetSpeeds, find20LowestIncomeCountry(incomes), find20HighestIncomeCountry(incomes));
        System.out.println("");     
        File populations = new File("populations.csv"); 
        compareSpeedByPop(internetSpeeds, find20LeastPopulated(populations), find20MostPopulated(populations)); 
    }

    //method to compare the average broadband sped of high income-earning countries and low income-earning countries
    //larger purpose is to assess the relationship between a countries average income and its average broadband speed
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

        System.out.print("The average broadband speed of 20 of the highest income-earning countries is "); 
        System.out.printf("%.2f", averageSpeedHighIncome); //rounds average broadband to 2 decimal places (for simplicity)
        System.out.println("."); 
        System.out.print("The average broadband speed of 20 of the lowest income-earning countries is "); 
        System.out.printf("%.2f", averageSpeedLowIncome); 
        System.out.println("."); 
    }   


    //method to compare the average broadband sped of highly populated and lowly populated countries
    //larger purpose is to assess the relationship between a countries population and its average broadband speed
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

    //method to find 20 countries with the lowest incomes
    public static ArrayList<String> find20LowestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));

        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        //bug requires hardcoding here
        int meanIncomeIndex = 2;
        int countryIndex = 0; 

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

    //method to find 20 countries with the highest incomes 
    public static ArrayList<String> find20HighestIncomeCountry (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Integer> allAverageIncomes = new ArrayList<>(); 
        
        //bug requires hardcoding here
        int meanIncomeIndex = 2;  
        int countryIndex = 0; 

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

    //method to find 20 countries with the lowest populations
    public static ArrayList<String> find20LeastPopulated (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Double> allPopulations = new ArrayList<>(); 
        
        //bug requires hardcoding here
        int populationIndex = 2;  
        int countryIndex = 1; 

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

    //method to find 20 countries with the highest populations
    public static ArrayList<String> find20MostPopulated (File dataSet) throws FileNotFoundException {
        Scanner fileScan = new Scanner(dataSet); 
        String [] header = fileScan.nextLine().split(",");
        ArrayList <String> headerAsL = new ArrayList<String> (Arrays.asList(header));
        
        ArrayList <Double> allPopulations = new ArrayList<>(); 
        
        //bug requires hardcoding here
        int populationIndex = 2;  
        int countryIndex = 1;

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

