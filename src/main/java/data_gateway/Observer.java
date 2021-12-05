package data_gateway;

/**
 * An object that is able to observe a {@link ObservableRepository}
 * @param <E> the entity (reader) that has undergone a change
 */
public interface Observer<E> {
    void notifyObserver(E entity);
}
