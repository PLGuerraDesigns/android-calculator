package com.example.calculator;

class Number {
    private Double Value = null;                //Stores the value of the number
    private Boolean IsPositiveNumber = true;    //States whether the number is positive or not
    private Boolean ContainsDecimal = false;    //States whether the number contains a decimal point


    Double getValue()
    {
        return Value;
    }
    Boolean getIfPositive(){
        return IsPositiveNumber;
    }
    Boolean getDecimal()
    {
        return ContainsDecimal;
    }


    void setValue(Double val)
    {
        Value = val;
    }
    void setDecimal(Boolean Decimal)
    {
        ContainsDecimal = Decimal;
    }
    void setIfPositive(Boolean IsPositiveIn)
    {
        IsPositiveNumber = IsPositiveIn;
    }


    void clearNumber()
    {
        Value = null;
        IsPositiveNumber = true;
        ContainsDecimal = false;
    }

}

