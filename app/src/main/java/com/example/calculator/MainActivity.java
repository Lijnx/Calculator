package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator;

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] Ids = new int[] {
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine,
                R.id.dot,
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.division,
                R.id.power,
                R.id.right_bracket,
                R.id.left_bracket,
                R.id.equals,
                R.id.erase,
                R.id.clear
        };

        text = findViewById(R.id.text);

        calculator = new CalculatorModel();

        View.OnClickListener ButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onPressed(view.getId());
                text.setText(calculator.getInputText());
            }
        };

        for (int i = 0; i < Ids.length; i++) {
            findViewById(Ids[i]).setOnClickListener(ButtonOnclickListener);
        }
    }
}