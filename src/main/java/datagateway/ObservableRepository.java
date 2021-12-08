package datagateway;

/**
 * Repository where observers are notified of Create and Update type mutations
 * @param <E> the exportable form of the element the repository manages
 */
public interface ObservableRepository<E> {

    void addCreationObserver(Observer<E> observer);

    void addUpdateObserver(Observer<E> observer);

    void addDeleteObservers(Observer<E> observer);

}
