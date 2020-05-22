package ru.job4j.collections.tree;

import java.util.*;

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

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
