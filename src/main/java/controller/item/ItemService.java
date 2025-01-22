package controller.item;

import model.Item;

import java.util.List;

public interface ItemService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(String itemCode);
    Item searchItem(String itemCode);
    List<Item> getAll();
}
