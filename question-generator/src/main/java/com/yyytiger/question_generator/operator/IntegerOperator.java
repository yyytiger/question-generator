package com.yyytiger.question_generator.operator;


import com.yyytiger.question_generator.calculator.IntegerCalculator;
import com.yyytiger.question_generator.calculator.impl.DivideCalculatorImpl;
import com.yyytiger.question_generator.calculator.impl.MinusCalculatorImpl;
import com.yyytiger.question_generator.calculator.impl.MultiCalculatorImpl;
import com.yyytiger.question_generator.calculator.impl.PlusCalculatorImpl;
import com.yyytiger.question_generator.model.IntegerCalculateResult;

public enum IntegerOperator {
    PLUS("＋", new PlusCalculatorImpl()),
    //PLUS("+", new PlusCalculatorImpl()),
    MINUS("－", new MinusCalculatorImpl()),
    //MINUS("-", new MinusCalculatorImpl()),
    MULTI("×", new MultiCalculatorImpl()),
    DIVIDE("÷", new DivideCalculatorImpl());

    private String text;
    private IntegerCalculator calculator;

    IntegerOperator(String text, IntegerCalculator calculator){
        this.text = text;
        this.calculator = calculator;
    }

    public String getText(){
        return this.text;
    }

    public IntegerCalculateResult calculate(int number1, int number2){
        return this.calculator.calculate(number1, number2);
    }
}
