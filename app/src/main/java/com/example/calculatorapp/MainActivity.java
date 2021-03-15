// Thien Le, T00640125, COMP 2160

package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static final String resultString= "result",
            oldNumberString= "oldNum",
            opString="op" ,
            isNewString= "boolean",
            isCalculatedString = "boolean",
            beforeIsNumberString = "boolean";
    EditText text;
    boolean isNewOp = false, isCalculated = false, beforeIsNumber = false;
    String op = "";
    Double oldNum = 0.0;
    Double result = 0.0;
    DecimalFormat fmt = new DecimalFormat("#.####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.resultTextView);
    }

    public void numberOnclick(View view) {
        if(isNewOp)
            text.setText("");
        isNewOp = false;
        String number = text.getText().toString();

        switch (view.getId())
        {
            case R.id.number0:
                number+="0";
                break;

            case R.id.number1:
                number+="1";
                break;

            case R.id.number2:
                number+="2";
                break;

            case R.id.number3:
                number+="3";
                break;

            case R.id.number4:
                number+="4";
                break;

            case R.id.number5:
                number+="5";
                break;

            case R.id.number6:
                number+="6";
                break;

            case R.id.number7:
                number+="7";
                break;

            case R.id.number8:
                number+="8";
                break;

            case R.id.number9:
                number+="9";
                break;

            case R.id.decimalButton:
                number+=".";
                break;
        }

        text.setText(number);
        beforeIsNumber = true;
    }

    public void operatorEvent(View view) {
        isNewOp=true;
        if(beforeIsNumber)
        {
            if(isCalculated)
            {
                solve();
                oldNum=result;
                text.setText(fmt.format(oldNum)+"");
            }
            else
            {
                oldNum=Double.parseDouble(text.getText().toString());
            }
        }

        switch(view.getId())
        {
            case R.id.plusButton:
                op="+";
                break;

            case R.id.minusButton:
                op="-";
                break;

            case R.id.multiplyButton:
                op="*";
                break;

            case R.id.divideButton:
                op="/";
                break;

            case R.id.powerButton:
                op="^";
                break;
        }
        isCalculated=true;
        beforeIsNumber=false;
    }

    public void percentageEvent(View view) {
        double num = Double.parseDouble(text.getText().toString())/100;
        text.setText(num+"");
        isNewOp=true;
    }

    public void solve() {
        String newNumber=text.getText().toString();
        switch (op) {
            case "+":
                double sum = oldNum + Double.parseDouble(newNumber);
                result = sum;
                break;

            case "-":
                double minus = oldNum - Double.parseDouble(newNumber);
                result = minus;
                break;

            case "*":
                double multiply = oldNum * Double.parseDouble(newNumber);
                result = multiply;
                break;

            case "/":
                if (Double.parseDouble(newNumber) == 0) {
                    text.setText("Error");
                    return;
                } else {
                    double divide = oldNum / Double.parseDouble(newNumber);
                    result = divide;
                }
                break;

            case "^":
                double pow = Math.pow(oldNum,Double.parseDouble(newNumber));
                result = pow;
                break;
        }

    }

    public void equalEvent(View view)
    {

        if(isCalculated==true)
        {
            solve();
            text.setText(fmt.format(result)+"");
        }
        isNewOp=true;
        isCalculated=false;
        beforeIsNumber=true;
    }


    public void buttonCEvent(View view) {
        op="";
        oldNum=0.0;
        result=0.0;
        text.setText("0");
        isNewOp=true;
        isCalculated=false;
        beforeIsNumber=false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putDouble(resultString, result);
        savedInstanceState.putDouble(oldNumberString, oldNum);
        savedInstanceState.putString(opString, op);
        savedInstanceState.putBoolean(isCalculatedString, isCalculated);
        savedInstanceState.putBoolean(isNewString, isNewOp);
        savedInstanceState.putBoolean(beforeIsNumberString, beforeIsNumber);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        result = savedInstanceState.getDouble(resultString);
        oldNum = savedInstanceState.getDouble(oldNumberString);
        op = savedInstanceState.getString(opString);
        isCalculated = savedInstanceState.getBoolean(isCalculatedString);
        beforeIsNumber = savedInstanceState.getBoolean(beforeIsNumberString);
        isNewOp = savedInstanceState.getBoolean(isNewString);
    }
}