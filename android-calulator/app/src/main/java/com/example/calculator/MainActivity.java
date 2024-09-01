package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtain Buttons from activity_main.xml
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);

        Button point = findViewById(R.id.point);
        Button delete = findViewById(R.id.delete);
        Button clear = findViewById(R.id.clear);
        Button sign = findViewById(R.id.sign);
        Button division = findViewById(R.id.division);
        Button multiplication = findViewById(R.id.multiplication);
        Button subtraction = findViewById(R.id.subtraction);
        Button addition = findViewById(R.id.addition);
        Button equal = findViewById(R.id.equal);

        //Listening for Button Presses
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

        point.setOnClickListener(this);
        delete.setOnClickListener(this);
        clear.setOnClickListener(this);
        sign.setOnClickListener(this);
        division.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        addition.setOnClickListener(this);
        equal.setOnClickListener(this);
    }




    String type = "First Operand";                 //Stores input type
    String prevType = null;                        //Stores previous input type
    String prevOperator;                           //Stores previous operator
    String operator;                               //Stores current operator
    Number Num1 = new Number();                    //New Number object
    Number Num2 = new Number();                    //New Number object
    Expression expression = new Expression();      //New Expression object
    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        //Obtain TextView from activity_main.xml
        TextView screen = findViewById(R.id.DataDisplay);
        TextView op_screen = findViewById(R.id.OpDisplay);

        //String will store the data input from the button presses.
        String dataIn = null;


        switch (v.getId()) {
            //Handling conversion of button presses (digits) to string data.
            case R.id.one:
                dataIn = "1";
                break;

            case R.id.two:
                dataIn = "2";
                break;

            case R.id.three:
                dataIn = "3";
                break;

            case R.id.four:
                dataIn = "4";
                break;

            case R.id.five:
                dataIn = "5";
                break;

            case R.id.six:
                dataIn = "6";
                break;

            case R.id.seven:
                dataIn = "7";
                break;

            case R.id.eight:
                dataIn = "8";
                break;

            case R.id.nine:
                dataIn = "9";
                break;

            case R.id.zero:
                dataIn = "0";
                break;

            //Handling conversion of button presses (operator) to string data.
            //Only allow Operands to be the first input.
            case R.id.addition:
                if(!type.equals("First Operand")) {
                    operator = dataIn = "+";
                }
                break;

            case R.id.subtraction:
                if(!type.equals("First Operand")) {
                    operator = dataIn = "-";
                }
                break;

            case R.id.multiplication:
                if(!type.equals("First Operand")) {
                    operator = dataIn = "×";
                }
                break;

            case R.id.division:
                if(!type.equals("First Operand")) {
                    operator = dataIn = "÷";
                }
                break;

            //Handling decimal points.
            case R.id.point:
                //If the Last input was an operator and point button is pressed then clear screen for new number.
                if(type.equals("operator")){
                    screen.setText("");
                }
                //If point is added to number (and doesn't already have a decimal point) then set contain's decimal to true.
                if (expression.getCurrentNumber() == 1 && type != "Result") {
                    if (!Num1.getDecimal()) {
                        dataIn = ".";
                        Num1.setDecimal(true);
                    }
                }

                if (expression.getCurrentNumber() == 2) {
                    if (!Num2.getDecimal()) {
                        dataIn = ".";
                        Num2.setDecimal(true);
                    }
                }
                break;
        }



           if(dataIn != null) {
               //Save the previous input type.
               prevType = type;

               //Get the current input type.
               type = getType(dataIn);


               //Reset the screen if a new expression is being computed (after pressing equal).
               if(prevType == "Result" && type == "Operand"){
                   screen.setText("");
                   screen.setText("");
                   prevType = "";
               }
               //Reset the screen if a new expression is being computed (after pressing operator).
               else if(prevType == "Operator" && type == "Operand"){
                    screen.setText("");
                }

               //If an operator is pressed and no first number exist then assign the first number.
               if (type == "Operator" && Num1.getValue() == null) {
                   //Make sure there is a number.
                   if(!screen.getText().toString().isEmpty() && !(getType(screen.getText().toString()) == "Decimal" && screen.getText().toString().length() == 1) && !(getType(screen.getText().toString()) == "Operator" && screen.getText().toString().length() == 1)) {
                       Num1.setValue(Double.valueOf(screen.getText().toString()));
                       expression.setNumberOfOperators(1);
                       prevOperator = operator;
                   }
               }

               //If an operator is pressed and the first number exist then assign the second number and compute result.
               else if (type == "Operator" && Num1.getValue() != null && prevType != "Operator"){
                   //Make sure there is a number.
                   if(!screen.getText().toString().isEmpty() && !(getType(screen.getText().toString()) == "Decimal" && screen.getText().toString().length() == 1) && !(getType(screen.getText().toString()) == "Operator" && screen.getText().toString().length() == 1)) {
                       Num2.setValue(Double.valueOf(screen.getText().toString()));
                       Double result = Calculate(prevOperator);

                       //Adjust the format based on the result value.
                       if (result % 1 == 0 ){
                           if(result.toString().length() < 5) {
                               screen.setText(String.valueOf(Math.round(result)));
                           }
                           else {
                               screen.setText(String.format("%.6e",result));
                           }
                       } else {
                           if(result.toString().length() < 5) {
                               screen.setText(String.valueOf(result));
                           }
                           else {
                               screen.setText(String.format("%.6e",result));
                           }
                       }

                       prevOperator = operator;
                       //Set Num1 as the result and erase Num2
                       Num1.setValue(result);
                       Num2.clearNumber();
                   }

               }
           }



        //Handling other inputs.
        switch (v.getId()) {
            //If clear button is selected then reset everything.
            case R.id.clear:
                screen.setText("");
                op_screen.setText("");
                Num1.clearNumber();
                Num2.clearNumber();
                operator=null;
                expression.setNumberOfOperators(0);
                type = "First Operand";
                break;

            //Changing number to negative or positive.
            case R.id.sign:
                if(expression.getCurrentNumber() == 1 && type != "Result"){
                    //Switch signs based on current state.
                    if(Num1.getIfPositive()){
                        String text = screen.getText().toString();
                        screen.setText("-");
                        screen.append(text);
                        Num1.setIfPositive(false);
                        type = "Sign";
                    }
                    else {
                        String text = screen.getText().toString().substring(1, screen.getText().length());
                        screen.setText(text);
                        Num1.setIfPositive(true);
                    }
            }

                if(expression.getCurrentNumber() == 2){
                    //If sign button is selected and last input type was operator then clear screen for new number.
                    if(type == "Operator" ){
                        screen.setText("");
                    }
                    //Switch signs based on current state.
                    if(Num2.getIfPositive()){
                        String text = screen.getText().toString();
                        screen.setText("-");
                        screen.append(text);
                        Num2.setIfPositive(false);
                        type = "Sign";
                    }
                    else {
                        String text = screen.getText().toString().substring(1, screen.getText().length());
                        screen.setText(text);
                        Num2.setIfPositive(true);
                    }
                }
                break;

            //Delete content on the furthest right only.
            case R.id.delete:
                if (screen.getText().length() > 0) {
                    //Get the type before deleting it.
                    type = getType(screen.getText().toString().substring(screen.getText().length()-1, screen.getText().length()));

                    //If a decimal is removed from a number then set contain's decimal to false
                    if(type.equals("Decimal")){
                        if(expression.getCurrentNumber() == 1){
                            Num1.setDecimal(false);
                        }
                        if(expression.getCurrentNumber() == 2){
                            Num2.setDecimal(false);
                        }
                    }
                    //If the negative sign is removed from a number then set it to positive.
                    if(type.equals("Operator")){
                        if(expression.getCurrentNumber() == 1){
                            Num1.setIfPositive(true);
                        }
                        if(expression.getCurrentNumber() == 2){
                            Num2.setIfPositive(true);
                        }

                    }
                    screen.setText(screen.getText().toString().substring(0, screen.getText().length() - 1));
                }
                break;


            //If the first number exist then assign the second number and compute result.
            case R.id.equal:

                if (Num1.getValue() != null && !type.equals("Operator")){
                    //sure there is a number.
                    if(!screen.getText().toString().isEmpty() && !(getType(screen.getText().toString()) == "Decimal" && screen.getText().toString().length() == 1) && !(getType(screen.getText().toString()) == "Operator" && screen.getText().toString().length() == 1)) {
                        op_screen.setText("=");
                        Num2.setValue(Double.valueOf(screen.getText().toString()));
                        Double result = Calculate(operator);

                        //Adjust the format based on the result value.
                        if (result % 1 == 0 ){
                            if(result.toString().length() < 5) {
                                screen.setText(String.valueOf(Math.round(result)));
                            }
                            else {
                                screen.setText(String.format("%.6e",result));
                            }
                        } else {
                            if(result.toString().length() < 5) {
                                screen.setText(String.valueOf(result));
                            }
                            else {
                            screen.setText(String.format("%.6e",result));
                            }
                        }

                        //Clear all numbers and reset expression.
                        Num1.clearNumber();
                        Num2.clearNumber();
                        expression.setNumberOfOperators(0);
                        type = "Result";
                    }

                }
                else{
                    screen.setText(screen.getText());
                }
                break;
        }

           //If the type is an operator then display it in the lower part of the screen else show in the upper part.
        if(dataIn != null && !type.equals("Operator")) {
            screen.append(dataIn);
        }
        if(dataIn != null && type.equals("Operator")) {
            op_screen.setText(dataIn);
        }
    }




    //Check and Return input type.
    public String getType(String Val){
        if (Val.contains("+") || Val.contains("-") || Val.contains("×") || Val.contains("÷")) {
            return "Operator";
        }
        else if(Val.contains(".")){
            return "Decimal";
        }
        else if(Val.contains("=")){
            return "Equal";
        }
        else {
            return "Operand";
        }
    }




    //Calculate and return the result.
    public Double Calculate(String operator){
        Double result = null;
        Double num1 = Num1.getValue();
        Double num2 = Num2.getValue();

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "×":
                result = num1 * num2;
                break;
            case "÷":
                result = num1 / num2;
                break;
        }
        return result;
    }
}
