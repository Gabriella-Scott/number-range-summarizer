package numberrangesummarizer;

public final class Range {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;

        if (start > end) {
            throw new IllegalArgumentException("Start cannot be greater than end");
        }
    }

    @Override
    public String toString() {
        if (start == end) {
            return String.valueOf(start);
        }
        return start + "-" + end;
    }
}