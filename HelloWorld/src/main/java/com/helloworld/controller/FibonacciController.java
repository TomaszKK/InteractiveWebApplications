package com.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.helloworld.model.Fibonacci;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FibonacciController {
    @RequestMapping("/fibonacci")
    public String fibonacci(Model model) {
        model.addAttribute("message", "Simple long from FibonacciController.");
        Fibonacci newFibonacci = new Fibonacci();
        model.addAttribute("fibonacci", newFibonacci);
        return "fibonacci";
    }

    @RequestMapping(value = "/addFibonacci.html", method = RequestMethod.POST)
    public String addFibonacci(@ModelAttribute("fibonacci") Fibonacci fibonacci, BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            FieldError error = result.getFieldError("number");
            if (error != null) {
                System.err.println("Error: " + error.getDefaultMessage());
            }
            return "redirect:fibonacci";
        }

        // Process the valid form data
        System.out.println(fibonacci.getFibonacci());
        return "redirect:fibonacci";
    }
}
