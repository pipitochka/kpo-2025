package hse.file.interfaces;

/**
 * interface of observable.
 */
public interface Observable {
    void export(Visitor observer, String fileName);
}
