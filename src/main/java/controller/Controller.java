package controller;

import calculations.Calculator;
import model.TrainItem;
import model.TrainLibrary;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class Controller {
   

    public static void main(String[] args) throws SQLException {

        TrainLibrary trainLibrary = new TrainLibrary();
        List<TrainItem> trainItems = trainLibrary.getTrainCollection();

        Calculator calculator = new Calculator();

        calculator.getNextRepairDate();

//        for(TrainItem trainItem:trainItems){
//
//            Date lastDate = trainItem.getLastDate();
//            Calendar instance = Calendar.getInstance();
//            instance.setTime(lastDate);
//            instance.add(Calendar.YEAR,5);
//            Date date= instance.getTime();
//
//            System.out.println(lastDate);
//            System.out.println(date);
//
//
//        }


    }
}
