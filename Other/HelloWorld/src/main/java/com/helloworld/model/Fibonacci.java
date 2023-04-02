package com.helloworld.model;


import org.springframework.validation.BindingResult;

public class Fibonacci {
    private int number;
    public int getNumber() {
        return number;
    }

    public void setNumber(int n) throws IllegalArgumentException{
        if(n < 0) {
            System.out.println("Input must be positive integer 0");
            throw new IllegalArgumentException();
        }
        this.number = n;
    }

    public void setNumber(String n){
        try{
            int newnumber = Integer.parseInt(n);
            setNumber(newnumber);
        } catch (NumberFormatException e) {
            System.out.println("Input must be positive integer");
        }
    }

    public long getFibonacci() {
        return fibonacci();
    }

    private long fibonacci() {
            if (this.number == 0) {
                return 0;
            } else if (this.number == 1) {
                return 1;
            } else {
                long fib0 = 0;
                long fib1 = 1;
                for (int i = 2; i <= this.number; i++) {
                    long fib = fib0 + fib1;
                    fib0 = fib1;
                    fib1 = fib;
                }
                return fib1;
            }
    }
}
