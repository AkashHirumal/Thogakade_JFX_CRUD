package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return false;
    }

    @Override
    public Item searchItem(String itemCode) {

        String SQL = "SELECT * FROM item WHERE code=" + "'" + itemCode + "'";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.next();
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();

        String SQL = "SELECT * FROM item";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            while (resultSet.next()) {
                itemList.add(
                        new Item(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4)
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemList;
    }

    public ObservableList<String> getItemCodes(){
        ObservableList<String> itemCodes = FXCollections.observableArrayList();

        List<Item> all = getAll();
        all.forEach(item -> {
            itemCodes.add(item.getItemCode());
        });

        return itemCodes;
    }

}
