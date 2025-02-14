package zoo.interfaces;

import zoo.domains.Zoo;

/**
 * interface of file reader.
 */
public interface InterfaceFileReader {

    Zoo readFromFile(String fileName);
}
