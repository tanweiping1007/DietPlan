package user.authentication;


import java.util.Date;

public class User {
    String username, password, name, birthDate, email, sensitiveFood;
    int age;
    Double weight, height, heartRate;
    String registerDate;

    public User(String name, int age, String username, String password)
    {
        this.name =name;
        this.age =age;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, int age, String birthDate, String email, Double weight, Double height, String sensitiveFood, Double heartRate, String registerDate)
    {
        this.username = username;
        this.password = password;
        this.name =name;
        this.age =age;
        this.birthDate= birthDate;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.sensitiveFood = sensitiveFood;
        this.heartRate = heartRate;
        this.registerDate = registerDate;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String toString()
    {
        return username + "xxx";
    }

}
