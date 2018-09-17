package com.betravelsome.travelpack.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.betravelsome.travelpack.model.Trip;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract class TravelPackRoomDatabase extends RoomDatabase {

    // Using the Singleton Pattern to make sure that only one instance of the database can
    // be opened at the same time.
    private static TravelPackRoomDatabase INSTANCE;

    public static TravelPackRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TravelPackRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create the database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TravelPackRoomDatabase.class, "travel_pack_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Abstract getter method for the MovieDao
    public abstract TravelPackDao travelPackDao();
}
