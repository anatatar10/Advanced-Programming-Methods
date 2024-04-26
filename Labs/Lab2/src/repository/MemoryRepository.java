package repository;

import domain.Identifiable;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepository<T extends Identifiable<?>, U> implements IRepository<T,U> {

    private Map<U, T> items = new HashMap<>();

    @Override
    public void addItem(T item) throws DuplicateItemException {
        if(items.containsKey(item.getId()))
            throw new DuplicateItemException("An item with " + item.getId() + " already exists.");
        items.put((U) item.getId(), item);
    }

    @Override
    public void deleteItemById(U id) throws ItemNotFound {
        if(items.containsKey(id))
        {
            items.remove(id);
            return;
        }
        throw new ItemNotFound("Item " + id + " not found");
    }

    @Override
    public T getItemById(U id) throws ItemNotFound {
        if(!items.containsKey(id))
        {
            throw new ItemNotFound("Item with id " + id + " not found");
        }
        return items.get(id);

    }

    @Override
    public void updateItemById(U id, T newItem) throws ItemNotFound{
        if(items.containsKey(id))
        {
            items.replace(id, newItem);
        }
        throw new ItemNotFound("Item with id " + id + " not found");
    }

    @Override
    public Iterable<T> getAllItems() {
        return items.values();
    }

}
