package reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JsonConsoleReport {

    private static final Map<String, Object> report = new LinkedHashMap<>();

    private JsonConsoleReport() {
    }

    public static boolean add(String field, Object expected, Object actual) {

        Map<String, Object> values = new LinkedHashMap<>();

        boolean pass = expected.equals(actual);

        values.put("expected", expected);
        values.put("actual", actual);
        values.put("result", pass ? "PASS" : "FAIL");

        report.put(field, values);
        return pass;
    }

    public static void print() {

        boolean overall = report.values().stream().allMatch(item -> ((Map<?, ?>) item).get("result").equals("PASS"));

        report.put("Overall Result", overall ? "PASS" : "FAIL");

        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            System.out.println();
            System.out.println("========== JSON REPORT ==========");
            System.out.println(mapper.writeValueAsString(report));
            System.out.println("=================================");
            System.out.println();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public static void clear() {
        report.clear();
    }
}