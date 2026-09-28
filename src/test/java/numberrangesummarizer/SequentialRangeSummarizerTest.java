package numberrangesummarizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
