package model;

import java.sql.Date;

public class TrainItem {

    private int id;
    private String name;
    private String genre;
    private String age;
    private Date lastDate;

    public TrainItem(String name, String genre, String age, Date lastDate) {
        this.name = name;
        this.genre = genre;
        this.age = age;
        this.lastDate = lastDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
