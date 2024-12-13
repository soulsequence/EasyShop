package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories(String name, String description)
    {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories " +
                "WHERE (name = ? OR ? = '') " +
                " And (description = ? OR ? = '')";

        name = name == null ? "" : name;
        description = description == null ? "" : description;

        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, name);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, description);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Category category = extractCategoryFromResultSet(rs);
                    categories.add(category);
                }
            }

        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        Category category = null;
        String query = "SELECT * FROM Categories WHERE category_id = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, categoryId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    category = extractCategoryFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

    private Category extractCategoryFromResultSet(ResultSet rs) throws SQLException {
        int idFromDB = rs.getInt("category_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        return new Category(idFromDB, name, description);
    }

}
