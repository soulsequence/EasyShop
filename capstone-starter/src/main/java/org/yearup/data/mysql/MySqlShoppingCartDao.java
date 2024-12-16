package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }



    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart;

        String sql = "SELECT * FROM shoppingCart " +
                "WHERE user_id = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return null;
    }

    private ShoppingCartItem extractItemFromResultSet(ResultSet rs) throws SQLException {
        int idFromDB = rs.getInt("product_id");
        int quantity = rs.getInt("quantity");
        return null;
    }
}
