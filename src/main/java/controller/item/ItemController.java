package controller.item;

import model.Item;

import java.util.List;

public class ItemController implements ItemService{
    @Override
    public List<Item> getAll() {
        return List.of();
    }

    @Override
    public boolean saveItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String itemId) {
        return false;
    }

    @Override
    public Item searchItem(String itemId) {
        return null;
    }
}
