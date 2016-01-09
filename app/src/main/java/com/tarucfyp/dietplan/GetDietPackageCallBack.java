package com.tarucfyp.dietplan;

import database.DatabaseRecord;

/**
 * Created by STUDIO on 1/1/2016.
 */
public interface GetDietPackageCallBack {
        public abstract void done(DatabaseRecord.FoodRecord returnedFood);

}
