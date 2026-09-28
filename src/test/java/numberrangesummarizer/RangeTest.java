package numberrangesummarizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RangeTest {

    @Test
    void toString_singleNumber() {
        Range range = new Range(5, 5);
        assertEquals("5", range.toString());
    }

    @Test
    void toString_range() {
        Range range = new Range(5, 8);
        assertEquals("5-8", range.toString());
    }

    @Test
    void constructor_invalidRange_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Range(8, 5));
    }
}
