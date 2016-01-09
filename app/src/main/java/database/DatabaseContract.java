package database;

import android.provider.BaseColumns;

/**
 * Created by STUDIO on 13/12/2015.
 */
public class DatabaseContract {

    public DatabaseContract(){
    }

    // User
    public static abstract class User implements BaseColumns {
        public static final String USER_TABLE_NAME ="User";
        public static final String USER_COLUMN_ID ="UID";
        public static final String USER_COLUMN_USERNAME ="username";
        public static final String USER_COLUMN_PASSWORD ="password";
        public static final String USER_COLUMN_NAME ="name";
        public static final String USER_COLUMN_AGE ="age";
        public static final String USER_COLUMN_DATEOFBIRTH ="dateOfBirth";
        public static final String USER_COLUMN_EMAIL ="email";
        public static final String USER_COLUMN_WEIGHT ="weight";
        public static final String USER_COLUMN_HEIGHT ="height";
        public static final String USER_COLUMN_SENSITIVE_FOOD ="sensitive_food";
        public static final String USER_COLUMN_HEARTRATE ="heartRate";
        public static final String USER_COLUMN_REGISTERDATE ="registerDate";
    }

    // Food
    public static abstract class Food implements BaseColumns {
        public static final String FOOD_TABLE_NAME ="Food";
        public static final String FOOD_COLUMN_ID ="FID";
        public static final String FOOD_COLUMN_NAME ="name";
        public static final String FOOD_COLUMN_CALORIES ="calories";
        public static final String FOOD_COLUMN_CARBOHYDRATE ="carbohydrate";
        public static final String FOOD_COLUMN_PROTEIN ="protein";
        public static final String FOOD_COLUMN_FAT ="fat";
        public static final String FOOD_COLUMN_FIBER ="fiber";
        public static final String FOOD_COLUMN_TAG ="tag";
    }


    // DietPackage
    public static abstract class DietPackage implements BaseColumns {
        public static final String DIETPACKAGE_TABLE_NAME ="DietPackage";
        public static final String DIETPACKAGE_COLUMN_ID ="DPID";
        public static final String DIETPACKAGE_COLUMN_DIETPACKAGESUITABLE ="dietPackageSuitable";
        public static final String DIETPACKAGE_COLUMN_DIETPACKAGEDESCRIPTION ="dietPackageDescription";
    }

    // FoodMeal
    public static abstract class FoodMeal implements BaseColumns {
        public static final String FOODMEAL_TABLE_NAME ="FoodMeal";
        public static final String FOODMEAL_COLUMN_ID ="FMID";
        public static final String FOODMEAL_COLUMN_FOOD_ID ="FID";
        public static final String FOODMEAL_COLUMN_DIETMEALTYPE ="dietMealType";
    }

    // FoodDietMeal
    public static abstract class FoodDietMeal implements BaseColumns {
        public static final String FOODDIETMEAL_TABLE_NAME ="FoodDietMeal";
        public static final String FOODDIETMEAL_COLUMN_ID ="FDMID";
        public static final String FOODDIETMEAL_COLUMN_DIETPACKAGE_ID ="DPID";
        public static final String FOODDIETMEAL_COLUMN_FOODMEAL_ID ="FMID";

    }

    // DailyDietPackage
    public static abstract class DailyDietPackage implements BaseColumns {
        public static final String DAILYDIETPACKAGE_TABLE_NAME ="DailyDietPackage";
        public static final String DAILYDIETPACKAGE_COLUMN_ID ="DDPID";
        public static final String DAILYDIETPACKAGE_COLUMN_FOODMEAL_ID ="FDMID";
        public static final String DAILYDIETPACKAGE_COLUMN_DAY ="day";
    }

    // Progress
    public static abstract class Progress implements BaseColumns {
        public static final String PROGRESS_TABLE_NAME ="Progress";
        public static final String PROGRESS_COLUMN_ID ="PID";
        public static final String PROGRESS_COLUMN_USER_ID ="UID";
        public static final String PROGRESS_COLUMN_DIETPACKAGE_ID ="DPID";
        public static final String PROGRESS_COLUMN_DIETFINISHDATE ="dietFinishDate";
    }

}



