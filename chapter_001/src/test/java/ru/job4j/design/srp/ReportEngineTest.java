package ru.job4j.design.srp;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Calendar;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
    @Test
    public void whenGenerateForHr() {
        MemStore store = new MemStore();
        Employer worker = new Employer("Petr", null, null, 100);
        Employer worker2 = new Employer("Dmitry", null, null, 120);
        Employer worker3 = new Employer("Ivan", null, null, 150);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        ReportEngineForHr engine = new ReportEngineForHr(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary").append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenGenerateForProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        Employer worker2 = new Employer("Oleg", now, now, 200);
        store.add(worker);
        store.add(worker2);
        ReportEngineForProgrammers engine = new ReportEngineForProgrammers(store);
        StringBuilder expected = new StringBuilder()
                .append("<div><h1>Report</h1><p>Name Hired Fired Salary").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";").append(System.lineSeparator()).append("<p><div>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}
