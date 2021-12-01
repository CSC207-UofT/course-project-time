package data_gateway;

public interface PropertyObserver<E, T> {
    void notify(E entity, T value);
}
