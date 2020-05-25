package ru.job4j.collections.tree;

import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<E>(root);
    }



    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> temp = null;
        if (findBy(parent).isPresent()) {
            temp = findBy(parent).get();
            if (!temp.children.contains(new Node<E>(child))) {
                temp.children.add(new Node<E>(child));
                result = true;
            }
        }
        return result;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (!(el.children.size() <= 2)) {
                result = false;
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }
}
