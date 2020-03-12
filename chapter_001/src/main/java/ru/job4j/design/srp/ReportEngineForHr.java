package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngineForHr extends ReportEngine{
    private Store store;

    public ReportEngineForHr(Store store) {
        this.store = store;
    }

    public class ComparatorSalaryDown implements Comparator<Employer> {
        @Override
        public int compare(Employer empl1, Employer empl2) {
            return Double.compare(empl2.getSalary(), empl1.getSalary());
        }
    }

    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary").append(System.lineSeparator());
        List<Employer> sorted = store.findBy(filter).stream().sorted(new ComparatorSalaryDown()).collect(Collectors.toList());
        for(Employer employer : sorted) {
            text.append(employer.getName()).append(";")
                    .append(employer.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
