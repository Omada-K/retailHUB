package com.dao;
import com.controller.DataBaseConfig;
import com.model.Product;
import com.model.ProductCategory;
import java.sql.*;
import java.util.ArrayList;

/**
 * DAO for accessing 'product category' table.
 */

public class ProductCategoryDAO {
  /**
   * Fetches all product records.
   *
   * @return list of Products
   * @throws SQLException if database access error occurs
   */

  public ArrayList<ProductCategory> getData() throws SQLException{
    ArrayList<ProductCategory> productCategory = new ArrayList<>();
    String query = "SELECT * FROM PRODUCT_CATEGORY";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt=conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        productCategory.add(new ProductCategory(
                rs.getInt("categoryId"),
                rs.getString("category")
        ));
      }
    }
    return productCategory;

  }


    public static void createItem (ProductCategory productCategory) throws SQLException {
    String insertSql = "INSERT INTO PRODUCT_CATEGORY (CATEGORY) VALUES (?,)";

    try (Connection conn = DataBaseConfig.getConnection();
       PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
    insertStmt.setInt(1, productCategory.getCategoryId());
    insertStmt.setString(2, productCategory.getCategory());
    insertStmt.executeUpdate();
    } catch (SQLException e) {
    e.printStackTrace();
    }
  }



//edit item
  public static void updateItem (ProductCategory productCategory) throws SQLException {
    String updateSql = "UPDATE PRODUCT_CATEGORY SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
      try (Connection conn = DataBaseConfig.getConnection();
       PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
        insertStmt.setString(1, productCategory.getCategory());
        insertStmt.setInt(2, productCategory.getCategoryId());
      } catch (SQLException e) {
      e.printStackTrace();
    }
  }

//delete item
  public static void deleteItem (Object selectedProduct) throws SQLException {
    String deleteSql = "DELETE FROM PRODUCT_CATEGORY WHERE CATEGORY_ID = ?";
    ProductCategory category = (ProductCategory) selectedProduct;
  try (Connection conn = DataBaseConfig.getConnection();
       PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
    insertStmt.setInt(1, category.getCategoryId());
    insertStmt.executeUpdate();
  } catch (SQLException e) {
    e.printStackTrace();
   }
  }
}