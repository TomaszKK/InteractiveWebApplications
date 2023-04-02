import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-fibonacci',
  templateUrl: './fibonacci.component.html',
  styleUrls: ['./fibonacci.component.css']
})
export class FibonacciComponent {
  @Input() inputNumber!: number;
  fibonacci!: number | null;
  errorMessage!: string | null;

  constructor() {}

  calculateFibonacci(inputnumber: HTMLInputElement): boolean {
    if (Number(inputnumber.value) < 0 || isNaN(Number(inputnumber.value))) {
      this.fibonacci = null;
      this.errorMessage = 'Please enter a positive number';
    } else {
      this.fibonacci = this.getFibonacci(Number(inputnumber.value));
      this.errorMessage = null;
    }
    return false;
    /*
      this.fibonacci = this.getFibonacci(Number(inputnumber.value));
      return false;
     */
  }

  getFibonacci(input: number): number {
    if (input == 0) {
      return 0;
    } else if (input == 1) {
      return 1;
    } else {
      let fib0: number = 0;
      let fib1: number = 1;
      for (let i: number = 2; i <= input; i++) {
        const fib: number = fib0 + fib1;
        fib0 = fib1;
        fib1 = fib;
      }
      return fib1;
    }
  }
}
