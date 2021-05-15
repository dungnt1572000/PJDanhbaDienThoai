package com.example.danhbadienthoai.databaseproduct;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class}, version = 2)
public abstract class DataLocal extends RoomDatabase {
    private static final String DATABASE_NAME = "product.db";
    private static DataLocal instance;
//    static Migration migration_1to2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("Alter table product add column value integer");
//            database.execSQL("Alter table product add column nhanxet Text");
//        }
//    };
    public static synchronized DataLocal getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DataLocal.class, DATABASE_NAME)
                    .allowMainThreadQueries()
//                    .addMigrations(migration_1to2)
                    .build();
        }
        return instance;
    }


    public abstract ProductDAO productDAO();
}