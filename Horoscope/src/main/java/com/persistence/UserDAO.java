package com.persistence;

import com.models.User;
import com.utils.ConnectionManager;
import com.utils.CustomCRUDInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO implements CustomCRUDInterface <User> {

Connection connection;

    public UserDAO(){
        connection= ConnectionManager.getConnection();
    }


    @Override
    public Integer create(User user) throws  IndexOutOfBoundsException {
        try{ String sql= "INSERT INTO horoscope_table(user_id,first_name,last_name,email,zodiac_sign) Values(default,?,?,?,?)";
            PreparedStatement pstmt= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirst_name());
            pstmt.setString(2, user.getLast_name());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getZodiac_sign());
            pstmt.executeUpdate();
            ResultSet rs= pstmt.getGeneratedKeys();
            rs.next();
            return rs.getInt("user_id");





        }catch(Exception e){

            System.out.println("Error in the userDAO"+e.getMessage());
        }


        return null;
    }

    @Override
    public User read(Integer id) {

        try{

            String sql = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement pstmt = connection. prepareStatement(sql);

            pstmt.setInt(1, id);

            ResultSet rs= pstmt.executeQuery();

            User user = new User();

            while(rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setBirth_date(rs.getString("birth_date"));
                user.setZodiac_sign(rs.getString("zodiac_sign"));
                user.setMood(rs.getString("mood"));

            } return user;


            }catch(Exception e){

            System.out.println("This is the UserDAO"+ e.getMessage());
        }



        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
    public User loginUser(String email, String password){
        try {

            String sql= "SELECT * FROM users WHERE email= ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs=pstmt.executeQuery();



            if(rs.next()&& rs.getString("last_name").equals(password)){
                return new User(rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("birth_date"),
                        rs.getString("zodiac_sign"),
                        rs.getString("mood"));


            }
        } catch(Exception e){
            System.out.println("This is the the userDAO"+e.getMessage());
        }
        return null;
    }
}
