package ru.job4j.design.srp;

import java.util.function.Predicate;
import java.util.List;

public interface Store {
    List<Employer> findBy(Predicate<Employer> filter);
}
