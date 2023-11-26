package Dao;

import models.Child;
import models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepository implements Repository{
    private Connection connection;

    public DBRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Child findChild(int id) {
        String query = "select * from kindergarten.children WHERE id = " + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            Child child = new Child(null, 0, null, 0,0);
            while (resultSet.next()) {
                int childId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                child.setId(childId);
                child.setName(name);
                child.setAge(age);
                child.setGender(gender);
                child.setHeight(height);
                child.setWeight(weight);
            }
            return child;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Child> findChildren() {
        String query = "select * from kindergarten.children";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Child> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                list.add(new Child(id, name, age, gender, height, weight));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveChild(Child child) {
        String query = "INSERT into kindergarten.children(name, age ,gender, height, weight) VALUES (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1,child.getName());
            preparedStatement.setInt(2,child.getAge());
            preparedStatement.setString(3,child.getGender());
            preparedStatement.setDouble(4,child.getHeight());
            preparedStatement.setDouble(5,child.getWeight());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);}

    }


    @Override
    public void updateChild(int id, Child child) {
        String query = "UPDATE kindergarten.children SET name = ?, age = ?, gender= ?, height= ?, weight= ?  WHERE ID=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setString(1,child.getName());
            preparedStatement.setInt(2,child.getAge());
            preparedStatement.setString(3,child.getGender());
            preparedStatement.setDouble(4,child.getHeight());
            preparedStatement.setDouble(5,child.getWeight());
            preparedStatement.setDouble(6,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);}
    }

    @Override
    public void removeChild(int id) {
        try(Statement statement = connection.createStatement();) {
            statement.execute("DELETE FROM kindergarten.children WHERE id=" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group findGroup(int id) {
        String query = "select * from kindergarten.groups WHERE id = " + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            Group group = new Group(0,null, 0);
            while (resultSet.next()) {
                int groupId = resultSet.getInt("id");
                String name = resultSet.getString("group_name");
                int number = resultSet.getInt("number");
                group.setId(groupId);
                group.setName(name);
                group.setNumber(number);
            }
            return group;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> findGroups() {
        String query = "select * from kindergarten.groups";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Group> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("group_name");
                int number = resultSet.getInt("number");
                list.add(new Group(id, name,number));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGroup(Group group) {
        String query = "INSERT into kindergarten.groups(group_name, number) VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1,group.getName());
            preparedStatement.setInt(2,group.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);}
    }

    @Override
    public void updateGroup(int id, Group group) {
        String query = "UPDATE kindergarten.groups SET group_name = ?, number = ? WHERE ID=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1,group.getName());
            preparedStatement.setInt(2,group.getNumber());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);}
    }

    @Override
    public void removeGroup(int id) {
        try(Statement statement = connection.createStatement();) {

            statement.execute("DELETE FROM kindergarten.groups WHERE id=" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(int childId, int groupId) {
        String query = "UPDATE kindergarten.children SET group_id = ? WHERE ID=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1,groupId);
            preparedStatement.setInt(2,childId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);}

    }

    @Override
    public void removeFromGroup(int childId) {
        String query = "UPDATE kindergarten.children SET group_id = ? WHERE ID=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1,0);
            preparedStatement.setInt(2,childId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);}
    }

    @Override
    public void show() {
        String query = "SELECT name, group_name from `groups` g right join children c on g.id = c.group_id";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                String groupName = rs.getString("group_name");
                System.out.println(name + ":" + groupName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show(int groupId) {
        String query = "SELECT name, group_name from `groups` g join children c on g.id = c.group_id WHERE g.id =" + groupId;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String groupName = rs.getString("group_name");
                System.out.println(name + ":" + groupName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
