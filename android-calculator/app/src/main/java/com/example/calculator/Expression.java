package com.example.calculator;

class Expression {
    private int CurrentNumber = 1;      //Stores the current number


    void setNumberOfOperators(int NumberOfOperatorsIn){
        CurrentNumber = NumberOfOperatorsIn +1; //Determine current number based on operators
    }

    int getCurrentNumber(){
        return CurrentNumber;
    }
}
