package model;

import DAO.TrainLibraryDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TrainLibrary {

    private List trainCollection;
    private List<Date> dateList;
    private TrainLibraryDAO trainLibraryDAO;
    private List<Date>dateByrepairType;

    public TrainLibrary() throws SQLException {
        this.trainLibraryDAO = new TrainLibraryDAO();
    }

    public List getTrainCollection(){
        if(trainCollection ==null){
            trainCollection = trainLibraryDAO.getTrainCollection();
        }
        return trainCollection;
    }

    public List<Date> getDateList(){
        if(dateList == null){
            dateList = trainLibraryDAO.getDate();
        }
        return dateList;
    }

    public List<Date> getTrainsByRepairType(int repairType){
        if(dateByrepairType==null){
            dateByrepairType = trainLibraryDAO.getTrainsByRepairType(repairType);

        }
        return dateByrepairType;
    }
}
