package interfaces;


public interface IObjectRepository {
    void add(Object object);

    void update(Object object);

    void delete(Object object);

    Object getById(int id);

    Iterable<Object> getAll();
}
