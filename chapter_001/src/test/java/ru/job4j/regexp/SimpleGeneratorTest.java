//package ru.job4j.Regexp;
//
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public class SimpleGeneratorTest {
//    @Test
//    public void whenPassNameAndSubjectThenReplaces() {
//        SimpleGenerator simpleGen = new SimpleGenerator();
//        Map<String, String> keys = Map.of("name", "Petr", "subject", "you");
//        String template = "I am ${name}, Who are ${subject}?";
//        String expected = "I am Petr, Who are you?";
//        String result = simpleGen.generate(template, keys);
//        assertThat(result, is(expected));
//    }
//}
