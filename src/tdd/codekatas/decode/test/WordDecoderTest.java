package tdd.codekatas.decode.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tdd.codekatas.decode.java.WordDecoder;

import java.util.stream.Stream;

public class WordDecoderTest {
    private WordDecoder wordDecoder;

    @BeforeEach
    public void setUp() {
        wordDecoder = new WordDecoder();
    }

    @Test
    public void shouldThrowExceptionWhenInputIsNullOrEmpty() {
        String input = null;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> wordDecoder.decodeWord(input));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> wordDecoder.decodeWord(""));
    }

    private static Stream<Arguments> getSingleCharacterInput() {
        return Stream.of(
                Arguments.of("a", "z"),
                Arguments.of("b", "a"),
                Arguments.of("c", "b")
        );
    }

    @ParameterizedTest
    @MethodSource("getSingleCharacterInput")
    public void shouldDecodeSingleCharacter(String input, String output) {
        Assertions.assertEquals(output, wordDecoder.decodeWord(input));
    }

    @Test
    public void shouldReturnCorrectWordWhenInputStringIsGreaterThanOneCharacter() {
        WordDecoder wordDecoder = new WordDecoder();
        String input = "dn";
        String output = "cr";
        Assertions.assertEquals(output, wordDecoder.decodeWord(input));
    }

    @Test
    public void shouldDecodeWord() {
        WordDecoder wordDecoder = new WordDecoder();
        String input = "dnotq";
        String output = "crime";
        Assertions.assertEquals(output, wordDecoder.decodeWord(input));
    }

    @Test
    public void shouldDecodeWordComplex() {
        WordDecoder wordDecoder = new WordDecoder();
        String input = "flgxswdliefy";
        String output = "encyclopedia";
        Assertions.assertEquals(output, wordDecoder.decodeWord(input));
    }

}
