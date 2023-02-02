package calculations;

import model.TrainLibrary;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Calendar.*;

public class Calculator {

    private int[] timeBetweenRepairs = new int[]{1,2,3,4,5,6,7};
    private int[] repairType = new int[]{1,2,3};
    private List<Date> dateOfLastRepair = new ArrayList<Date>();
    private List <Date> dateOfNextRepair = new ArrayList<Date>() ;
    public List<String> newDateOfRepair = new ArrayList<String>();
    public String[][] datesByType = new String[6][1000];
    public int[] countOfrepairsMonth = new int[12];
    private int u=0;


    private int lifecycleOfTrain;
    // Controller???
   // public String[] month = new DateFormatSymbols().getMonths();


    public Calculator() {

    }

//    public void setDateOfNextRepair(List<Date> dateOfNextRepair) {
//        this.dateOfNextRepair = dateOfNextRepair;
//    }





    public void getNextRepairDate() throws SQLException {
        DateFormatSymbols monthDate = new DateFormatSymbols();
        String[] monthNames = monthDate.getMonths();
        int [][] repairsByType = new int[6][12];

      for(int i = 0; i < repairType.length;i++){
          TrainLibrary trainLibrary = new TrainLibrary();
          dateOfLastRepair=trainLibrary.getTrainsByRepairType(repairType[i]);
// getting date of last repair and it's type;
        if(dateOfLastRepair.size()>0 ){
            System.out.println("Repair Type: "+repairType[i]);
            System.out.println("Old date: "+dateOfLastRepair);
            for(Date date: dateOfLastRepair ){
                Calendar instance = getInstance();
                instance.setTime(date);

                instance.add(YEAR,timeBetweenRepairs[i]);
                Date nextDate= instance.getTime();

                dateOfNextRepair.add(nextDate);
            }
            DateFormat form = new SimpleDateFormat("dd.MM.yyyy");

            // getting date of next repairs by types;
            for(Date date: dateOfNextRepair){
                Calendar instance = getInstance();
                instance.setTime(date);
                Date date1= instance.getTime();
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//                System.out.println(format.format(date1));
                newDateOfRepair.add(format.format(date1));
                int month = (int) date1.getMonth()+1;
//Count of repairs Per month by type;
                for(int j=0;j<countOfrepairsMonth.length;j++){
                    if(month-1==j){
                        countOfrepairsMonth[j]=countOfrepairsMonth[j]+1;
                        repairsByType[i][j] = repairsByType[i][j]+1;
                    }
                }
            }
//dates by types of repairs;

            for(int y=0;y< newDateOfRepair.size();y++){
                datesByType[i][y] = newDateOfRepair.get(u);
                if(u == newDateOfRepair.size()-1){
                    break;
                }
                u++;
            }
            u++;
            dateOfNextRepair.clear();
        }
          dateOfLastRepair = null;

        }
//Outputs
        System.out.println(Arrays.toString(monthNames));
        int i=0;

        for(int[] arr: repairsByType){
            int sum=0;
            System.out.println("Count per repairType: " + repairType[i]);

            for(int n: arr ){
                sum = n+sum;
                System.out.print(n+" ");
            }
            System.out.println(" ");
            System.out.println("Total sum for this type: "+sum);
            i++;
        }

//        for(String[] ar: datesByType){
//            for(String str: ar){
//                if(str!=null){
//                    System.out.println(str+"");
//                }
//
//            }
//        }

        for(int z=0;z< repairType.length;z++){
            System.out.println("Repair type: "+repairType[z]+" Dates of this type");
            for(int y=0;y<newDateOfRepair.size();y++){
                if(datesByType[z][y]!=null){
                    System.out.println(datesByType[z][y]);
                }

            }
        }


        System.out.println("Total count per Month: ");
        System.out.println(Arrays.toString(monthNames));
        System.out.println(Arrays.toString(countOfrepairsMonth));
        System.out.println(newDateOfRepair);
    }
}
