package ru.job4j.design.srp;

import java.util.function.Predicate;
import java.text.DecimalFormat;

public class ReportForAccountants implements ReportEngine {
    private Store store;

    public ReportForAccountants(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        for (Employer employer : store.findBy(filter)) {
            text.append(employer.getName()).append(";")
                    .append(employer.getHired()).append(";")
                    .append(employer.getFired()).append(";")
                    .append(new DecimalFormat("0.0000").format(employer.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
