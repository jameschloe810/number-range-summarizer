package com.example.ranges;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return Collections.emptyList();

        try {
            return Stream.of(trimmed.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::valueOf)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format", e);
        }
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        if (input.isEmpty()) return "";

        List<Integer> nums = input.stream().sorted().collect(Collectors.toList());
        StringBuilder result = new StringBuilder();

        int start = nums.get(0);
        int prev = start;

        for (int i = 1; i < nums.size(); i++) {
            int curr = nums.get(i);
            if (curr == prev + 1) { prev = curr; continue; }
            appendRange(result, start, prev);
            start = prev = curr;
        }
        appendRange(result, start, prev);
        return result.toString();
    }

    private static void appendRange(StringBuilder sb, int start, int end) {
        if (sb.length() > 0) sb.append(", ");
        if (start == end) sb.append(start);
        else sb.append(start).append("-").append(end);
    }
}
