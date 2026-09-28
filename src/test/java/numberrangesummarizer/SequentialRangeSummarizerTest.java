package numberrangesummarizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequentialRangeSummarizerTest {
    private NumberRangeSummarizer summarizer;

    @BeforeEach
    void setUp() {
        summarizer = new SequentialRangeSummarizer();
    }

    private String summarize(String input) {
        Collection<Integer> numbers = summarizer.collect(input);
        return summarizer.summarizeCollection(numbers);
    }

    @Test
    @DisplayName("Sample from the specification")
    void summarizeSampleInput() {
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", summarize("1,3,6,7,8,12,13,14,15,21,22,23,24,31"));
    }

    @Test
    void summarize_removesDuplicates() {
        assertEquals("1-3", summarize("1,1,2,3,3"));
    }

    @Test
    void collect_rejectsNonNumericToken() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect("1,a,3"));
    }

    @Test
    void summarizeCollection_sortsUnsortedInput() {
        assertEquals("1-3", summarizer.summarizeCollection(Arrays.asList(3, 1, 2)));
    }

    @Test
    void summarizeCollection_rejectsNullElement() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.summarizeCollection(Arrays.asList(1, null, 3)));
    }

    @Test
    void collect_returnsEmptyForNullOrEmptyInput() {
        assertTrue(summarizer.collect(null).isEmpty());
        assertTrue(summarizer.collect("").isEmpty());
        assertTrue(summarizer.collect(" ").isEmpty());
    }

    @Test
    void collect_parsesInputWithSpaces() {
        assertEquals(Arrays.asList(1, 2), summarizer.collect(" 1 , 2 "));
    }

    @Test
    void collect_rejectsEmptyToken() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect("1,,3"));
    }

    @Test
    void collect_rejectsTrailingComma() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect("1,2,"));
    }

    @Test
    void summarizeCollection_variousCases() {
        assertEquals("", summarizer.summarizeCollection(null));
        assertEquals("", summarizer.summarizeCollection(Arrays.asList()));
        assertEquals("5", summarizer.summarizeCollection(Arrays.asList(5)));
        assertEquals("6-7", summarizer.summarizeCollection(Arrays.asList(6, 7)));
        assertEquals("-3--1", summarizer.summarizeCollection(Arrays.asList(-3, -2, -1)));
        assertEquals("2147483646-2147483647",
                summarizer.summarizeCollection(Arrays.asList(Integer.MAX_VALUE - 1, Integer.MAX_VALUE)));
    }

}
