package controller.item;

import model.Customer;
import model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    boolean saveItem(Item item);

    boolean updateItem(Item item);

    boolean deleteItem(String itemId);

    Item searchItem(String itemId);

}
