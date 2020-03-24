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
        ReportEngine engine = new OldReportEngine(store);
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
    public void whenGenerateDefaultForHr() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocDefault();
        Employer worker = new Employer("Petr", null, null, 100);
        Employer worker2 = new Employer("Dmitry", null, null, 120);
        Employer worker3 = new Employer("Ivan", null, null, 150);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new ReportEngineForHr(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("Report").append(System.lineSeparator())
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
    public void whenGenerateHTMLForHR() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocFormatHTML();
        Employer worker = new Employer("Petr", null, null, 100);
        Employer worker2 = new Employer("Dmitry", null, null, 120);
        Employer worker3 = new Employer("Ivan", null, null, 150);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new ReportEngineForHr(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("<div><h1>Report</h1>").append(System.lineSeparator())
                .append("<p>Name; Salary").append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator())
                .append("</p></div>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenGenerateDefaultForProgrammers() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocDefault();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        Employer worker2 = new Employer("Oleg", now, now, 200);
        store.add(worker);
        store.add(worker2);
        ReportEngine engine = new ReportEngineForProgrammers(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("Report").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenGenerateJSONForProgrammers() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocJson();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        Employer worker2 = new Employer("Oleg", now, now, 200);
        store.add(worker);
        store.add(worker2);
        ReportEngine engine = new ReportEngineForProgrammers(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("[ { \"Name\" : \"Report\" }").append(System.lineSeparator())
                .append("{ \"Name; Hired; Fired; Salary").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";").append(System.lineSeparator())
                .append(" \" } ]");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenGenerateDefaultForAccountants() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocDefault();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100.12345678987654321);
        store.add(worker);
        ReportEngine engine = new ReportForAccountants(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("Report").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append("100,1235").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenGenerateXMLForAccountants() {
        MemStore store = new MemStore();
        DocFormat docFormat = new DocXml();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100.12345678987654321);
        store.add(worker);
        ReportEngine engine = new ReportForAccountants(store, docFormat);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><name>Report</name>").append(System.lineSeparator())
                .append("<lines>Name; Hired; Fired; Salary</lines>")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append("100,1235").append(";")
                .append(System.lineSeparator())
                .append("</document>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

}
