package pl.gensty;

import java.awt.*;

public interface Displayer<T> {
    void display(Graphics g, T object);
}
