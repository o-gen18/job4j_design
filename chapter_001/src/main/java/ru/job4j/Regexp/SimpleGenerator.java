package ru.job4j.Regexp;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleGenerator {
    private Matcher matcher;

    public String generate(String template, Map<String, String> mappings) {
        Set<Pattern> compiledKeys = mappings.keySet().stream().map(Pattern::compile).collect(Collectors.toSet());
        String changed = null;
        for (Pattern pattern : compiledKeys) {
            matcher = pattern.matcher(template);
            if(matcher.matches()) {
                changed = template.replace(pattern.toString(), mappings.get(pattern.toString()));
            }
        }
        return changed;
    }
}
