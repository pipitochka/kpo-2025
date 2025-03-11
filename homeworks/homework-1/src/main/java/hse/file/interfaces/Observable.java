package hse.file.interfaces;
;

public interface Observable {
    void export(Visitor observer, String fileName);
}
