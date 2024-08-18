package org.example.dao;

import org.example.models.Order;
import org.example.models.Product;
import org.example.utils.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    Connection connection = null;
    CustomerDao customerDao = new CustomerDao();
    ProductDao productDao = new ProductDao();
    public OrderDao() {

        DbConnector connector = new DbConnector();
        this.connection = connector.getConnection();
        String sql =
                "create table if not exists orders(" +
                        "order_id bigserial primary key," +
                        "order_status varchar not null," +
                        "order_date date not null," +
                        "customer_id integer not null constraint fk_customer_order references customer(customer_id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Order order){
        String createOrder = "insert into orders(order_status, order_date, customer_id) values(?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createOrder);
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setDate(2, (Date) order.getDate());// Shauen Sie bitte
            preparedStatement.setInt(3, order.getCustomer().getCustomerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Order savedOrder = null;
            if(resultSet.next()){
                savedOrder = new Order();
                savedOrder.setOrderId(resultSet.getInt("order_id"));
                savedOrder.setDate(resultSet.getDate("order_date"));
                savedOrder.setProductList(order.getProductList());
                savedOrder.setCustomer(order.getCustomer());
            }
            // начинаем писать в таблицу many to many
            String createManyToManyRecord = "insert into orders_products(product_id, order_id) values(?,?);";
            for (Product product: savedOrder.getProductList()) {
                PreparedStatement preparedStatement1 = connection.prepareStatement(createManyToManyRecord);
                preparedStatement1.setInt(1, product.getProductId());
                preparedStatement1.setInt(2, savedOrder.getOrderId());
                preparedStatement1.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getById(int id) {
        String sql = "select* from order where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setCustomer(customerDao.getById(resultSet.getInt("customer_id")));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
            }
            String getProducts = "select* from order_products where order_id=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(getProducts);
            preparedStatement1.setInt(1, id);
            ResultSet resultSetProducts = preparedStatement1.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSetProducts.next()){
                products.add(productDao.getById(resultSetProducts.getInt("product_id")));
            }
            order.setProductList(products);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Order order){
        String updateOrder = "update orders set order_status=?, order_date=?, customer_id=? where product_id_?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateOrder);
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setDate(2, (Date) order.getDate());
            preparedStatement.setInt(3, order.getCustomer().getCustomerId());
            preparedStatement.setInt(4, order.getOrderId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOrder(int id){
        String sql = "delete from orders where order_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrdersProducts(int id){
        String sql = "delete from orders_products where order_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
