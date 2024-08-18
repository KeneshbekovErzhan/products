package org.example.dao;

import org.example.models.Product;
import org.example.utils.DbConnector;
import java.sql.*;

public class ProductDao {
    Connection connection = null;
    public ProductDao() {

        DbConnector connector = new DbConnector();
        this.connection = connector.getConnection();
        String sql =
                "create table if not exists product(" +
                        "product_id bigserial primary key," +
                        "product_name varchar not null," +
                        "description varchar," +
                        "price double precision not null);";
        String productSql =
                "create table if not exists orders_products(" +
                        "product_id integer not null constraint fk_product_order references product(product_id)," +
                        "order_id integer not null constraint fk_order_product references orders(order_id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.execute(productSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public boolean createProduct(Product product){
        String sql = "insert into product(product_name, description, price) values (?, ?, ?);";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                return  preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public Product getById(int id){
        String sql = "select* from product where product_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setProductName(resultSet.getString("product_name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    return product;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        public void updateProduct(Product product){
            String sql = "update product set product_name=?, description=?, price=? where product_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getProductId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void deleteProduct(int id){
            String sql = "delete from product where product_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}
