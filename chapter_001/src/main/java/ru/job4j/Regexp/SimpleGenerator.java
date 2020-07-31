//package ru.job4j.Regexp;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//public class SimpleGenerator {
//    private Matcher matcher;
//
//    public String generate(String template, Map<String, String> mappings) {
//        String changed = null;
//        for (String word : template.split("[ ,?]")) {
//            for (String key : mappings.keySet()) {
//                if(word.matches(key)) {
//                    changed = template.replace(word, mappings.get(key));
//                    mappings.remove(key, mappings.get(key));
//                    break;
//                }
//            }
//        }
//        return changed;
//    }
//}
