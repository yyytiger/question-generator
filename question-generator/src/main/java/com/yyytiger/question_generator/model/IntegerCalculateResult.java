package com.yyytiger.question_generator.model;

public class IntegerCalculateResult {
    private int result;
    private int remainder;

    public IntegerCalculateResult(){
        this(0);
    }

    public IntegerCalculateResult(int result){
        this(result, 0);
    }

    public IntegerCalculateResult(int result, int remainder) {
        this.result = result;
        this.remainder = remainder;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public String getDisplayText(){
        if(remainder == 0)
            return String.valueOf(result);
        else
            return String.format("%s……%s", result, remainder);
    }
}
