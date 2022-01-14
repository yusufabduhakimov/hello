package repository;

import entiti.Category;
import entiti.Response;
import utils.DbConfig;

import java.sql.*;
import java.util.List;

public class CategoryRepository {
    public static List<Category> getCategorise() throws SQLException {
        Connection ulanish = DbConfig.ulanish();

        System.out.println(ulanish);

        Statement statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from category");
        while (resultSet.next()){
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getString(2));
//            System.out.println(resultSet.getString(3));


        }
        return null;
    }

    public static  boolean addCategory() throws SQLException {
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into category value (?,?,?)");
        preparedStatement.setInt(1,100);
        preparedStatement.setString(2,"Nimadir");
        preparedStatement.setBoolean(3,false);
        return preparedStatement.execute();
    }

    public static Response callFunction(String name, String type, String n_name) throws SQLException {
        Response response = new Response();
        Connection connection= DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("call category_crud(?,?,?,?,?)");
        callableStatement.setString(1,name);
        callableStatement.setString(2,type);
        callableStatement.setString(3,n_name);
        callableStatement.registerOutParameter(4,Types.BOOLEAN);
        callableStatement.registerOutParameter(5,Types.VARCHAR);

        callableStatement.execute();

            response.setSuccess(callableStatement.getBoolean(1));
            response.setMessage(callableStatement.getString(2));

        return response;
    }
}
