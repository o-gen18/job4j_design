package ru.job4j.design.srp;

import java.util.function.Predicate;

public class OldReportEngine implements ReportEngine {
    private Store store;

    public OldReportEngine(Store store) {
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
                    .append(employer.getSalary()).append(";").append(System.lineSeparator());
        }
        return text.toString();
    }
}
