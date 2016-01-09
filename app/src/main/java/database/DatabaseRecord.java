package database;

import java.io.Serializable;

/**
 * Created by STUDIO on 13/12/2015.
 */
public class DatabaseRecord {

    public DatabaseRecord(){}

    public static class UserRecord implements Serializable{
        String ID,username, password, name, birthDate, email, sensitiveFood;
        int age;
        Double weight, height, heartRate;
        String registerDate;

        public UserRecord(){}

        public UserRecord(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

        public UserRecord(String ID, String username, String password, String name, int age, String birthDate, String email, Double weight, Double height, String sensitiveFood, Double heartRate, String registerDate)
        {
            this.ID = ID;
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

        public UserRecord(String username, String password, String name, int age, String birthDate, String email, Double weight, Double height, String sensitiveFood, Double heartRate, String registerDate)
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

        public String getID() { return ID;}
        public void setID(String ID) {this.ID = ID;}

        public String getUsername() { return username;}
        public void setUsername(String username) {this.username = username;}

        public String getPassword() { return password;}
        public void setPassword(String password) {this.password = password;}

        public String getName() { return name;}
        public void setName(String name) {this.name = name;}

        public int getAge() { return age;}
        public void setAge(int age) {this.age = age;}

        public String getBirthDate() { return birthDate;}
        public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

        public String getEmail() { return email;}
        public void setEmail(String email) {this.email = email;}

        public Double getWeight() { return weight;}
        public void setWeight(Double weight) {this.weight = weight;}

        public Double getHeight() { return height;}
        public void setHeight(Double height) {this.height = height;}

        public String getSensitiveFood() { return sensitiveFood;}
        public void setSensitiveFood(String sensitiveFood) {this.sensitiveFood = sensitiveFood;}

        public Double getHeartRate() { return heartRate;}
        public void setHeartRate(Double heartRate) {this.heartRate = heartRate;}

        public String getRegisterDate() { return registerDate;}
        public void setRegisterDate(String registerDate) {this.registerDate = registerDate;}

    }

    public static class FoodRecord{
        String category, name, tag ;
        Double calories, carbohydrate, protein, fat, fiber;

        public FoodRecord(){}
        public FoodRecord(String category, String name, Double calories, Double carbohydrate, Double protein, Double fat, Double fiber, String tag){
            this.name = name;

            this.category = category;
            this.calories = calories;
            this.carbohydrate = carbohydrate;
            this.protein = protein;
            this.fat = fat;
            this.fiber = fiber;
            this.tag = tag;
        }

        public String getName() { return name;}
        public void setName(String name) {this.name = name;}

        public Double getCalories() { return calories;}
        public void setCalories(Double calories) {this.calories = calories;}

        public Double getCarbohydrate() { return carbohydrate;}
        public void setCarbohydrate(Double carbohydrate) {this.carbohydrate = carbohydrate;}

        public Double getProtein() { return protein;}
        public void setProtein(Double protein) {this.protein = protein;}

        public Double getFat() { return fat;}
        public void setFat(Double fat) {this.fat = fat;}

        public Double getFiber() { return fiber;}
        public void setFiber(Double fiber) {this.fiber = fiber;}

        public String getTag() { return tag;}
        public void setTag(String protein) {this.tag = tag;}
    }

    public class DietPackage{
        String dietPackageSuitable, dietPackageDescription;
        public DietPackage(){}

        public DietPackage(String dietPackageSuitable, String dietPackageDescription){
            this.dietPackageSuitable = dietPackageSuitable;
            this.dietPackageDescription = dietPackageDescription;
        }

        public String getDietPackageSuitable() { return dietPackageSuitable;}
        public void setDietPackageSuitable(String dietPackageSuitable) {this.dietPackageSuitable = dietPackageSuitable;}

        public String getDietPackageDescription() { return dietPackageDescription;}
        public void setDietPackageDescription(String dietPackageDescription) {this.dietPackageDescription = dietPackageDescription;}
    }

    public class FoodMeal{
        String dietMealType;

        public FoodMeal(){}

        public  FoodMeal(String dietMealType){
            this.dietMealType = dietMealType;
        }

        public String getDietMealType() { return dietMealType;}
        public void setDietMealType(String dietMealType) {this.dietMealType = dietMealType;}

    }

    public class FoodDietMeal {
       public FoodDietMeal() {
       }
   }

    public class DailyDietPackage{
        String day;
        public DailyDietPackage(){}

        public DailyDietPackage(String day){
            this.day = day;
        }
    }

    public class Progress{
        String dietFinishDate;
        public Progress(){}

        public Progress(String dietFinishDate){
            this.dietFinishDate = dietFinishDate;
        }
    }
}
