package com.example.wheelmax77;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public List<Car> getSampleCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Range Rover", 7000000, R.drawable.rangered));
        cars.add(new Car("Range Rover", 9000000, R.drawable.rngwhite));
        cars.add(new Car("Mercedes", 7000000, R.drawable.marcedes));
        cars.add(new Car("Gclass", 7000000, R.drawable.gclass));
        cars.add(new Car("Jeep", 1000000, R.drawable.jeep));
        cars.add(new Car("Toyota", 50000, R.drawable.toyota));
        cars.add(new Car("Range Rover", 7000000, R.drawable.rangered));
        cars.add(new Car("Range Rover", 9000000, R.drawable.rngwhite));
        cars.add(new Car("Mercedes", 7000000, R.drawable.marcedes));
        cars.add(new Car("Gclass", 7000000, R.drawable.gclass));
        cars.add(new Car("Jeep", 1000000, R.drawable.jeep));
        cars.add(new Car("Toyota", 50000, R.drawable.toyota));

        DatabaseHelper databaseHelper = null;
        databaseHelper.insertAllCars();
        return cars;
    }

}