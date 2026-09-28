# Number Range Summarizer

Produces a comma delimited list of numbers, grouping sequential numbers into ranges.

## Requirements
- Java 8+
- Maven 3.6+

## Build and test
```
mvn clean test
```

## Assumptions
Each assumption below is covered by at least one unit test.

**Parsing input (`collect`)**
- `null`, empty or whitespace-only input returns an empty collection rather than throwing.
- Whitespace around numbers is ignored, so `" 1 , 2 "` is valid.
- Every token must be a valid 32-bit integer. Non-numeric tokens (`"1,a,3"`), empty tokens (`"1,,3"`), trailing commas (`"1,2,"`) and values outside the `int` range throw an `IllegalArgumentException` naming the invalid token.
- Negative numbers are supported.

**Summarising (`summarizeCollection`)**
- `null` or an empty collection returns an empty string.
- A collection containing a `null` element throws an `IllegalArgumentException`.
- Input order does not matter; numbers are sorted ascending before grouping.
- Duplicates are ignored, so `1,1,2` becomes `1-2`.
- Two consecutive numbers form a range, so `6,7` becomes `6-7`.
- Negative ranges keep the same format, so `-3,-2,-1` becomes `-3--1`.
- Output items are separated by a comma and a space, matching the specification.
- The input collection is not modified.

## Design
- `SequentialRangeSummarizer` implements the provided `NumberRangeSummarizer` interface and handles validation, parsing and grouping.
- `Range` is an immutable value object representing a start and end value. It rejects invalid ranges (start greater than end) and is responsible for its own formatting (`5` or `5-8`), keeping formatting separate from the grouping logic.


