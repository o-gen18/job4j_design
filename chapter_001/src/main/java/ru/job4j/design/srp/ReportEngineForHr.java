package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngineForHr implements ReportEngine {
    private Store store;
    private DocFormat docFormat;

    public ReportEngineForHr(Store store, DocFormat docFormat) {
        this.store = store;
        this.docFormat = docFormat;
    }

    public class ComparatorSalaryDown implements Comparator<Employer> {
        @Override
        public int compare(Employer empl1, Employer empl2) {
            return Double.compare(empl2.getSalary(), empl1.getSalary());
        }
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append(docFormat.formatHead("Report"))
                .append(System.lineSeparator())
                .append(docFormat.formatBody("Name; Salary"))
                .append(System.lineSeparator());
        List<Employer> sorted = store.findBy(filter).stream().sorted(new ComparatorSalaryDown()).collect(Collectors.toList());
        for (Employer employer : sorted) {
            text.append(employer.getName()).append(";")
                    .append(employer.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append(docFormat.formatTail());
        return text.toString();
    }
}
