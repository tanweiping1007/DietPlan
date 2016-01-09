package database;

import android.provider.BaseColumns;


public class DietPlanPackageContract {


    public DietPlanPackageContract(){
    }

    public static abstract class DietPackage implements BaseColumns {
        public static final String TABLE_NAME ="DietPlanPackage";
        public static final String COLUMN_ID ="package_id";
        public static final String COLUMN_THUMBNAIL ="package_thumbnail";
        public static final String COLUMN_SUITABLE ="package_suitable";
        public static final String COLUMN_DESCRIPTION ="package_description";

        public static final String COLUMN_DESCRIPTION_DAY1_MORNING ="package_description_day1_morning";
        public static final String COLUMN_DESCRIPTION_DAY1_EVENING ="package_description_day1_evening";
        public static final String COLUMN_DESCRIPTION_DAY1_NIGHT ="package_description_day1_night";

        public static final String COLUMN_DESCRIPTION_DAY2_MORNING ="package_description_day2_morning";
        public static final String COLUMN_DESCRIPTION_DAY2_EVENING ="package_description_day2_evening";
        public static final String COLUMN_DESCRIPTION_DAY2_NIGHT ="package_description_day2_night";

        public static final String COLUMN_DESCRIPTION_DAY3_MORNING ="package_description_day3_morning";
        public static final String COLUMN_DESCRIPTION_DAY3_EVENING ="package_description_day3_evening";
        public static final String COLUMN_DESCRIPTION_DAY3_NIGHT ="package_description_day3_night";

        public static final String COLUMN_DESCRIPTION_DAY4_MORNING ="package_description_day4_morning";
        public static final String COLUMN_DESCRIPTION_DAY4_EVENING ="package_description_day4_evening";
        public static final String COLUMN_DESCRIPTION_DAY4_NIGHT ="package_description_day4_night";


        public static final String COLUMN_DESCRIPTION_DAY5_MORNING ="package_description_day5_morning";
        public static final String COLUMN_DESCRIPTION_DAY5_EVENING ="package_description_day5_evening";
        public static final String COLUMN_DESCRIPTION_DAY5_NIGHT ="package_description_day5_night";

    }
    }
