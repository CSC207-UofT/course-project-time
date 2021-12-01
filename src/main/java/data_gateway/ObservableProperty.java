package data_gateway;

import java.util.ArrayList;
import java.util.List;

public class ObservableProperty<E, T> {

    private final List<PropertyObserver<E, T>> observers = new ArrayList<>();

    public void addObserver(PropertyObserver<E, T> observer) {
        observers.add(observer);
    }

    public void notifyObservers(E entity, T value) {
        for (PropertyObserver<E, T> observer : observers)
            observer.notify(entity, value);
    }


}
