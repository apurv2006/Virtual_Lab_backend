package virtual.lab.string_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringController {

    // Example method: Reverse a string
    @GetMapping("/reverse")
    public String reverseString(@RequestParam String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // Example method: Convert a string to uppercase
    @GetMapping("/uppercase")
    public String toUpperCase(@RequestParam String input) {
        return input.toUpperCase();
    }

    // Example method: Check if a string is a palindrome
    @GetMapping("/palindrome")
    public boolean isPalindrome(@RequestParam String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);
    }

    // Example method: Concatenate two strings
    @GetMapping("/concatenate")
    public String concatenateStrings(@RequestParam String str1, @RequestParam String str2) {
        return str1 + str2;
    }

    // Example method: Get the length of a string
    @GetMapping("/length")
    public int getStringLength(@RequestParam String input) {
        return input.length();
    }
}
