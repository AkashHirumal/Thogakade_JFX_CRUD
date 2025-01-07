package controller.item;

import model.Customer;
import model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    boolean addItem(Item item);

    boolean updateItem(Item item);

    boolean deleteItem(String id);

    Item searchItem(String id);



}
