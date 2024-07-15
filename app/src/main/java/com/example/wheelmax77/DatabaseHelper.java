package com.example.wheelmax77;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Car_db";
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_CARS = "Cars";
    public static final String TABLE_ORDERS = "Orders";
    public static final String TABLE_CAR_COMPARISONS = "CarComparisons";

    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FULLNAME = "fullname";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PHONENUMBER = "phoneNumber";

    private static final String CAR_ID = "car_id";
    private static final String CAR_NAME = "name";
    private static final String CAR_PRICE = "price";
    private static final String CAR_IMAGEURL = "imageurl";
    private static final String CAR_DESCRIPTION = "description";
    private static final String CAR_MAKE = "make";
    private static final String CAR_MODEL = "model";
    private static final String CAR_YEAR = "year";
    private static final String CAR_MILEAGE = "mileage";

    private static final String ORDER_ID = "order_id";
    private static final String ORDER_USER_ID = "user_id";
    private static final String ORDER_CAR_ID = "car_id";
    private static final String ORDER_DATE = "date";
    private static final String ORDER_STATUS = "status";

    private static final String CAR_COMPARISON_ID = "comparison_id";
    private static final String CAR_COMPARISON_USER_ID = "user_id";
    private static final String CAR_COMPARISON_CAR1_ID = "car1_id";
    private static final String CAR_COMPARISON_CAR2_ID = "car2_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_EMAIL + " TEXT, "
                + USER_PASSWORD + " TEXT, "
                + USER_FULLNAME + " TEXT, "
                + USER_ADDRESS + " TEXT, "
                + USER_PHONENUMBER + " TEXT)";
        db.execSQL(createUsersTable);

        String createCarsTable = "CREATE TABLE " + TABLE_CARS + " ("
                + CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_NAME + " TEXT, "
                + CAR_PRICE + " REAL, "
                + CAR_IMAGEURL + " TEXT, "
                + CAR_DESCRIPTION + " TEXT, "
                + CAR_MAKE + " TEXT, "
                + CAR_MODEL + " TEXT, "
                + CAR_YEAR + " INTEGER, "
                + CAR_MILEAGE + " INTEGER)";
        db.execSQL(createCarsTable);

        String createOrdersTable = "CREATE TABLE " + TABLE_ORDERS + " ("
                + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ORDER_USER_ID + " INTEGER, "
                + ORDER_CAR_ID + " INTEGER, "
                + ORDER_DATE + " TEXT, "
                + ORDER_STATUS + " TEXT DEFAULT 'Booked', "
                + "FOREIGN KEY (" + ORDER_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + USER_ID + "), "
                + "FOREIGN KEY (" + ORDER_CAR_ID + ") REFERENCES " + TABLE_CARS + "(" + CAR_ID + "))";
        db.execSQL(createOrdersTable);

        String createCarComparisonsTable = "CREATE TABLE " + TABLE_CAR_COMPARISONS + " ("
                + CAR_COMPARISON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_COMPARISON_USER_ID + " INTEGER, "
                + CAR_COMPARISON_CAR1_ID + " INTEGER, "
                + CAR_COMPARISON_CAR2_ID + " INTEGER, "
                + "FOREIGN KEY (" + CAR_COMPARISON_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + USER_ID + "), "
                + "FOREIGN KEY (" + CAR_COMPARISON_CAR1_ID + ") REFERENCES " + TABLE_CARS + "(" + CAR_ID + "), "
                + "FOREIGN KEY (" + CAR_COMPARISON_CAR2_ID + ") REFERENCES " + TABLE_CARS + "(" + CAR_ID + "))";
        db.execSQL(createCarComparisonsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_COMPARISONS);
        onCreate(db);
    }

    public boolean insertUser(String email, String password, String fullname, String address, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_FULLNAME, fullname);
        contentValues.put(USER_ADDRESS, address);
        contentValues.put(USER_PHONENUMBER, phoneNumber);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + USER_EMAIL + " = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + USER_EMAIL + " = ? AND " + USER_PASSWORD + " = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public boolean insertCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_NAME, car.getName());
        contentValues.put(CAR_PRICE, car.getPrice());
        contentValues.put(CAR_IMAGEURL, car.getImageUrl());
        // Uncomment and add more fields if needed
        // contentValues.put(CAR_DESCRIPTION, car.getDescription());
        // contentValues.put(CAR_MAKE, car.getMake());
        // contentValues.put(CAR_MODEL, car.getModel());
        // contentValues.put(CAR_YEAR, car.getYear());
        // contentValues.put(CAR_MILEAGE, car.getMileage());

        long result = db.insert(TABLE_CARS, null, contentValues);
        db.close(); // Close the database after the operation
        return result != -1;
    }

    public void insertAllCars() {
        Car[] cars = new Car[0];
    }



    public List<Car> getAllCars() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Car> cars = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM cars", null);
            if (cursor.moveToFirst()) {
                do {
                    Car car = new Car();
                    car.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    car.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow("price")));
                    car.setImageUrl(cursor.getInt(cursor.getColumnIndexOrThrow("imageUrl"))); // Assuming imageUrl is a String
                    cars.add(car);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return cars;
    }



//
//    public boolean insertOrder(Order order) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ORDER_USER_ID, order.getUserId());
//        contentValues.put(ORDER_CAR_ID, order.getCarId());
//        contentValues.put(ORDER_DATE, order.getOrderDate());
//        long result = db.insert(TABLE_ORDERS, null, contentValues);
//        return result != -1;
//    }
//
//    public boolean insertCarComparison(CarComparison carComparison) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CAR_COMPARISON_USER_ID, carComparison.getUserId());
//        contentValues.put(CAR_COMPARISON_CAR1_ID, carComparison.getCarId1());
//        contentValues.put(CAR_COMPARISON_CAR2_ID, carComparison.getCarId2());
//        long result = db.insert(TABLE_CAR_COMPARISONS, null, contentValues);
//        return result != -1;
//    }

    // Add additional methods for retrieving, updating, and deleting data as needed.
}
