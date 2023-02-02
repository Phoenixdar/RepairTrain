package DAO;

import model.TrainItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainLibraryDAO {

    private static final String GET_DATES = "SELECT release_date FROM movie";
    private static final String  GET_TRAIN_COLLECTION = "SELECT name,genre,age_limit,release_date FROM movie";
    private static final String  GET_TRAIN_COLLECTION_BY_REPAIR_TYPE = "SELECT release_date FROM movie WHERE rating =?";
    private  static final String GET_NAMES = "SELECT name FROM movie";
    private static final String URL_CONNECTION = "jdbc:mysql://127.0.0.1:3306/edb?user=root&password=root";

    public TrainLibraryDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List getTrainCollection(){
        List collection = new ArrayList<TrainItem>();

        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_TRAIN_COLLECTION);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                Date date = resultSet.getDate("release_date");
                String age = resultSet.getString("age_limit");
                TrainItem trainItem = new TrainItem(name,genre,age,date);

                collection.add(trainItem);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
        return collection;
    }

    public List getTrainsByRepairType(int repairType){
        List<Date> dateRate = new ArrayList();
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_TRAIN_COLLECTION_BY_REPAIR_TYPE);
            preparedStatement.setInt(1, repairType);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                dateRate.add(resultSet.getDate("release_date"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
        return dateRate;
    }

    public List getDate(){
        List<Date> dates = new ArrayList();
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_DATES);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                dates.add(resultSet.getDate("release_date"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
        return dates;
    }

    public List getNames(){
        List names = new ArrayList();
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(URL_CONNECTION);
            preparedStatement = connection.prepareStatement(GET_NAMES);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                names.add(resultSet.getString("name"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
        return names;
    }
    private void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try{
                preparedStatement.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
