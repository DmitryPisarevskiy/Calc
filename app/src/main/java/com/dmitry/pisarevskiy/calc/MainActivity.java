package com.dmitry.pisarevskiy.calc;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvInput;
    private TextView tvOutput;

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
                    try {
                        tvOutput.setText(String.valueOf(result(tvInput.getText().toString())));
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

    private float result(String text) throws IllegalArgumentException {
        if (text.charAt(0)=='(' && text.charAt(text.length()-1)==')' && checkedBrackets(text.substring(1,text.length()-1))) {
            return (result(text.substring(1,text.length()-1)));
        }
        if (text.charAt(0)=='+' || text.charAt(0)=='-') {
            return (result("0"+text));
        }
        if (text.charAt(text.length()-1)=='+' || text.charAt(text.length()-1)=='-') {
            return (result(text+"0"));
        }
        if (isFloat(text)) {
            return Float.parseFloat(text);
        }
        if (splitBySign(text,'+').length==2) {
            return (result(splitBySign(text,'+')[0]) + result(splitBySign(text,'+')[1]));
        }
        if (splitBySign(text,'-').length==2) {
            return (result(splitBySign(text,'-')[0]) - result(splitBySign(text,'-')[1]));
        }
        if (splitBySign(text,'*').length==2) {
            return (result(splitBySign(text,'*')[0]) * result(splitBySign(text,'*')[1]));
        }
        if (splitBySign(text,'/').length==2) {
            return (result(splitBySign(text,'/')[0]) / result(splitBySign(text,'/')[1]));
        }
        throw new IllegalArgumentException();
    }

    public static boolean isFloat(String value) {
        return value.matches("\\d+(\\.\\d+)?");
    }

    public static String[] splitBySign(String text, char sign) {
        int i=0;
        int bracketsCounter=0;
        while (i<text.length()) {
            if (text.charAt(i)=='(') {
                bracketsCounter=1;
                while (bracketsCounter!=0) {
                    i++;
                    if (text.charAt(i)=='(') {
                        bracketsCounter+=1;
                    }
                    if (text.charAt(i)==')') {
                        bracketsCounter-=1;
                    }
                }
            }
            if (text.charAt(i)==sign) {
                return new String[]{text.substring(0,i),text.substring(i+1,text.length())};
            }
            i++;
        }
        return new String[]{text};
    }

    public static boolean checkedBrackets(String text) {
        int bracketsCounter=0;
        int i=0;
        while (i<text.length()) {
            if (text.charAt(i)=='(') {
                bracketsCounter+=1;
            }
            if (text.charAt(i)==')') {
                bracketsCounter-=1;
            }
            if (bracketsCounter<0) {
                return false;
            }
            i++;
        }
        return true;
    }
}