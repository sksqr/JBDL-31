package com.example.L09jdbcdemo;

import com.mysql.cj.jdbc.StatementImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PersonDao {

    private static Logger logger = LoggerFactory.getLogger(PersonDao.class);

    public Person updatePerson(Integer id, Person person){
        Connection conn = null;
        boolean autoCommit=false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "app1", "Jbdl@1234");
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String updateQ ="update person set name=? , email=?, phone=? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateQ);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getEmail());
            preparedStatement.setString(3,person.getPhone());

            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException exc) {
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            try {
                conn.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }


    public Person getPersonById(int id){
        Person person =null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "app1", "Jbdl@1234");
//            String prepQuery ="select * from person where id=? and name=?";
            String prepQuery ="select * from person where id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
            preparedStatement.setInt(1,id);
//            preparedStatement.setString(2,"Shashi");

//            Statement statement = conn.createStatement();
//            String query ="select * from person where id="+id;
//            ResultSet rs = statement.executeQuery(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("Name"));
                person.setEmail(rs.getString("email"));
                person.setPhone(rs.getString("phone"));
//                person.setId(rs.getInt(1));
//                person.setName(rs.getString(2));
//                person.setEmail(rs.getString(3));
//                person.setPhone(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person createPerson(Person person){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_system", "app1", "Jbdl@1234");
//            Statement statement = conn.createStatement();
//            String insertQuery = "insert into person values (null,'"+person.getName()+"','"+person.getEmail()+"',null,null)";
//            boolean status = statement.execute(insertQuery);

            String prepQuery ="insert into person values (null, ?, ?,?,null)";
            PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getEmail());
            preparedStatement.setString(3,person.getPhone());
            preparedStatement.execute();
            person.setId((int) ((StatementImpl) preparedStatement).getLastInsertID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
/*
single API
//Transaction
Start
- update address
- update person
End
fine: commit
error: rollback


 */