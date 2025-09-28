package tdd.codekatas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FizzbuzzTest {

    @Test
    public void shouldReturnNormalStringRepresentationForNormalNumbers() {
        Fizzbuzz fizzbuzz = new Fizzbuzz();
        Assertions.assertEquals("1", fizzbuzz.play(1));
        Assertions.assertEquals("2", fizzbuzz.play(2));
    }

    @Test
    public void shouldReturnFizzForMultiplesOfThree() {
        Fizzbuzz fizzbuzz = new Fizzbuzz();
        Assertions.assertEquals("Fizz", fizzbuzz.play(3));
        Assertions.assertEquals("Fizz", fizzbuzz.play(6));
    }

    @Test
    public void shouldReturnBuzzForMultiplesOfFive() {
        Fizzbuzz fizzbuzz = new Fizzbuzz();
        Assertions.assertEquals("Buzz", fizzbuzz.play(5));
        Assertions.assertEquals("Buzz", fizzbuzz.play(10));
    }

    @Test
    public void shouldReturnFizzBuzzWhenNumberIsMultipleOfThreeAndFive() {
        Fizzbuzz fizzbuzz = new Fizzbuzz();
        Assertions.assertEquals("FizzBuzz", fizzbuzz.play(15));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForInvalidValue() {
        Fizzbuzz fizzbuzz = new Fizzbuzz();
        Assertions.assertThrows(IllegalArgumentException.class, () -> fizzbuzz.play(-15));
        Assertions.assertThrows(IllegalArgumentException.class, () -> fizzbuzz.play(0));
    }
}

class Fizzbuzz {
    public String play(int number) {
        if (number < 1) try {
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        if (number % 3 == 0 && number % 5 == 0) return "FizzBuzz";
        else if (number % 5 == 0) return "Buzz";
        else if (number % 3 == 0) return "Fizz";
        else
            return String.valueOf(number);
    }
}