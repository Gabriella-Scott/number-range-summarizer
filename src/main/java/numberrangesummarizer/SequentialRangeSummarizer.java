package numberrangesummarizer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SequentialRangeSummarizer implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> numbers = new ArrayList<>();
        for (String token : input.split(",", -1)) {
            numbers.add(parseToken(token));
        }

        return numbers;
    }

    private Integer parseToken(String token) {
        String trimmed = token.trim();
        try {
            return Integer.valueOf(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + trimmed, e);
        }
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        if (input.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Input collection contains null values");
        }

        List<Integer> sorted = input.stream().distinct().sorted().collect(Collectors.toList());

        List<Range> ranges = new ArrayList<>();
        int start = sorted.get(0);
        int end = start;

        for (int i = 1; i < sorted.size(); i++) {
            int current = sorted.get(i);
            if (current == end + 1) {
                end = current;
            } else {
                ranges.add(new Range(start, end));
                start = current;
                end = start;
            }
        }
        ranges.add(new Range(start, end));
        return ranges.stream()
                .map(Range::toString)
                .collect(Collectors.joining(", "));
    }

}
