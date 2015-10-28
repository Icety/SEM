package application.core.aliens;

/**
 * Interface for Interator Design Pattern.
 */
public interface Iterator {

    /**
     * Check whether there's a next item in the Iterator.
     * @return boolean value.
     */
    boolean hasNext();

    /**
     * Get the next object in the iterator.
     * @return the next object.
     */
    Object next();
}
