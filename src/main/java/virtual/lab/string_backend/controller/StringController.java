package virtual.lab.string_backend.controller;
// import javax.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StringController {

    @GetMapping("/length")
    public int getStringLength(@RequestParam @NotBlank String input) {
        return input.length();
    }

    @GetMapping("/substring")
    public String getSubstring(@RequestParam @NotBlank String input, @RequestParam int beginIndex, @RequestParam int endIndex) {
        try {
            return input.substring(beginIndex, endIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid substring indices", e);
        }
    }

    @GetMapping("/uppercase")
    public String convertToUppercase(@RequestParam @NotBlank String input) {
        return input.toUpperCase();
    }

    @GetMapping("/lowercase")
    public String convertToLowercase(@RequestParam @NotBlank String input) {
        return input.toLowerCase();
    }

    @GetMapping("/reverse")
    public String reverseString(@RequestParam @NotBlank String input) {
        return new StringBuilder(input).reverse().toString();
    }

    @GetMapping("/concat")
    public String concatenateStrings(@RequestParam @NotBlank String input1, @RequestParam @NotBlank String input2) {
        return input1.concat(input2);
    }

    @GetMapping("/contains")
    public boolean checkContains(@RequestParam @NotBlank String input, @RequestParam @NotBlank String sequence) {
        return input.contains(sequence);
    }

    @GetMapping("/replace")
    public String replaceString(@RequestParam @NotBlank String input, @RequestParam @NotBlank String target, @RequestParam @NotBlank String replacement) {
        return input.replace(target, replacement);
    }

    @GetMapping("/split")
    public String[] splitString(@RequestParam @NotBlank String input, @RequestParam @NotBlank String delimiter) {
        return input.split(delimiter);
    }
    @GetMapping("/count") public long countOccurrences(@RequestParam @NotBlank String input, @RequestParam @NotBlank String target) { if (target.length() != 1) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Target must be a single character"); } return input.chars().filter(ch -> ch == target.charAt(0)).count(); }
     @GetMapping("/ispalindrome") public boolean isPalindrome(@RequestParam @NotBlank String input) { String cleanInput = input.replaceAll("\\s+", "").toLowerCase(); return cleanInput.equals(new StringBuilder(cleanInput).reverse().toString()); }
      @GetMapping("/longestword") public String findLongestWord(@RequestParam @NotBlank String input) { String[] words = input.split("\\s+"); String longestWord = ""; for (String word : words) { if (word.length() > longestWord.length()) { longestWord = word; } } return longestWord; }
}
