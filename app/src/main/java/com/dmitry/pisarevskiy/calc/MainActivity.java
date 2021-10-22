package com.dmitry.pisarevskiy.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IMainView {
    private TextView tvInput;
    private TextView tvOutput;
    private MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInput = findViewById(R.id.tvInput);
        tvOutput = findViewById(R.id.tvOutput);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonLeftBracket = findViewById(R.id.buttonLeftBracket);
        Button buttonRightBracket = findViewById(R.id.buttonRightBracket);

        setOnClickYourself(button0);
        setOnClickYourself(button1);
        setOnClickYourself(button2);
        setOnClickYourself(button3);
        setOnClickYourself(button4);
        setOnClickYourself(button5);
        setOnClickYourself(button6);
        setOnClickYourself(button7);
        setOnClickYourself(button8);
        setOnClickYourself(button9);
        setOnClickYourself(buttonPlus);
        setOnClickYourself(buttonMinus);
        setOnClickYourself(buttonMultiply);
        setOnClickYourself(buttonDivide);
        setOnClickYourself(buttonLeftBracket);
        setOnClickYourself(buttonRightBracket);
        setOnClickYourself(buttonDot);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvInput.getText().equals("")) {
                    presenter.calculateResult(tvInput.getText().toString());
                    try {
                        tvOutput.setText(String.valueOf(Logic.result(tvInput.getText().toString())));
                    } catch (IllegalArgumentException | StringIndexOutOfBoundsException | NullPointerException e) {
                        e.printStackTrace();
                        System.out.println("Неверный аргумент");
                    }
                }
            }
        });
    }

    public void setOnClickYourself(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder(tvInput.getText());
                str.append(button.getText());
                tvInput.setText(str);
            }
        });
    }

    @Override
    public void displayResult(String result) {
        tvOutput.setText(result);
    }
}