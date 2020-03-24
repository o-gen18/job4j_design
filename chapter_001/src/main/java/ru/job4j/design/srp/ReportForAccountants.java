package ru.job4j.design.srp;

import java.util.function.Predicate;
import java.text.DecimalFormat;

public class ReportForAccountants implements ReportEngine {
    private DocFormat docFormat;
    private Store store;

    public ReportForAccountants(Store store, DocFormat docFormat) {
        this.store = store;
        this.docFormat = docFormat;
    }

    @Override
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        text.append(docFormat.formatHead("Report"))
                .append(System.lineSeparator())
                .append(docFormat.formatBody("Name; Hired; Fired; Salary"))
                .append(System.lineSeparator());
        for (Employer employer : store.findBy(filter)) {
            text.append(employer.getName()).append(";")
                    .append(employer.getHired()).append(";")
                    .append(employer.getFired()).append(";")
                    .append(new DecimalFormat("0.0000").format(employer.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        text.append(docFormat.formatTail());
        return text.toString();
    }
}
