package com.example.ranges;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerTest {

    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

    @Test
    void exampleInput() {
        Collection<Integer> numbers = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result);
    }

    @Test
    void emptyInput() {
        assertTrue(summarizer.collect("").isEmpty());
        assertEquals("", summarizer.summarizeCollection(summarizer.collect("")));
    }

    @Test
    void invalidTokenThrows() {
        assertThrows(IllegalArgumentException.class, () -> summarizer.collect("1, x, 3"));
    }
}
