package com.example.waybill_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText mileage_before;
    EditText mileage_after;
    EditText fuel_before;
    EditText refill;
    EditText consumption_per_100_km;
    EditText consumption;
    EditText engine_hours;
    TextView mileage;
    TextView consumption_norm;
    TextView remaining_fuel;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mileage_before = (EditText)findViewById(R.id.mileage_before);
        mileage_after = (EditText)findViewById(R.id.mileage_after);
        fuel_before = (EditText)findViewById(R.id.fuel_before);
        refill = (EditText)findViewById(R.id.refill);
        consumption_per_100_km = (EditText)findViewById(R.id.consumption_per_100_km);
        consumption = (EditText)findViewById(R.id.consumption);
        engine_hours = (EditText)findViewById(R.id.engine_hours);
        mileage = (TextView)findViewById(R.id.mileage);
        consumption_norm = (TextView)findViewById(R.id.consumption_norm);
        remaining_fuel = (TextView)findViewById(R.id.remaining_fuel);
        button = (Button)findViewById(R.id.button);
    }

    public void mileageCalc() {
        // Метод рассчета пробега
        int result = 0;
        String after = mileage_after.getText().toString();
        String before = mileage_before.getText().toString();
        TextView mileage = (TextView) findViewById(R.id.mileage);
        System.out.println(after);
        System.out.println(before);
        if (after.equals("")) {
            mileage.setText("Заполните поле 'Пробег после выезда'");
        } else if (before.equals("")) {
            mileage.setText("Заполните поле 'Пробег до выезда'");
        } else {
            result = Integer.parseInt(after) - Integer.parseInt(before);
            mileage.setText(String.valueOf(result));
        }
    }

    public void consumptionNormCalc() {
        String mil = mileage.getText().toString();
        String con_per_100_km = consumption_per_100_km.getText().toString();
        String eng_hours = engine_hours.getText().toString();
        String cons = consumption.getText().toString();
        EditText engine_hours = (EditText) findViewById(R.id.engine_hours);
        EditText consumption = (EditText) findViewById(R.id.consumption);
        TextView consumption_norm = (TextView) findViewById(R.id.consumption_norm);
        if (mil.equals("Заполните поле 'Пробег после выезда'") || mil.equals("Заполните поле 'Пробег до выезда'")) {
            consumption_norm.setText("Невозможно выполнить рассчёт, заполните все нужные поля");
        } else if (con_per_100_km.equals("")) {
            consumption_norm.setText("Заполните поле 'Расход на 100 км'");
        } else if (eng_hours.equals("")) {
            engine_hours.setText("0");
        } else if (cons.equals("")) {
            consumption.setText("0");
        } else {
            float cons1 = (Float.parseFloat(mil) * Float.parseFloat(con_per_100_km))/100;
            float cons2 = (Integer.parseInt(eng_hours) * Float.parseFloat(cons));
            float cons_norm = cons1 + cons2;
            @SuppressLint("DefaultLocale") String result = String.format("%.3f", cons_norm);

            consumption_norm.setText(result);
        }

    }

    public void remainingFuel() {
        String f_before = fuel_before.getText().toString();
        String c_norm = consumption_norm.getText().toString();
        String ref = refill.getText().toString();
        EditText refill = (EditText) findViewById(R.id.refill);
        TextView remaining_fuel = (TextView) findViewById(R.id.remaining_fuel);
        c_norm = c_norm.replaceAll(",", ".");
        if (f_before.equals("")) {
            remaining_fuel.setText("Заполните поле 'Топлива при выезде'");
        } else if (ref.equals("")) {
            refill.setText("0");
        } else {
            float rem_fuel1 = Float.parseFloat(f_before) - Float.parseFloat(c_norm);
            float rem_fuel = rem_fuel1 + Float.parseFloat(ref);
            @SuppressLint("DefaultLocale") String result = String.format("%.3f", rem_fuel);
            remaining_fuel.setText(result);
        }

    }

    public void clickResult(View view) {
        mileageCalc();
        consumptionNormCalc();
        consumptionNormCalc();
        consumptionNormCalc();
        remainingFuel();
        remainingFuel();
    }

}