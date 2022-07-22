package com.servlets;

import com.models.User;
import com.persistence.UserDAO;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
            import java.io.BufferedReader;
            import java.io.IOException;
            import java.io.PrintWriter;
            import java.util.ArrayList;
            import java.util.List;


public class UserServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI().replace("/Horoscope/", "");

        switch (URI) {
            case "userlogin":


                processLoginForUser(req, resp);

                break;

            default:
                System.out.println("we're in the default get");
                break;
        }



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        String URI = req.getRequestURI().replace("/Horoscope/", "");

        System.out.println(URI);

        switch (URI) {
            case "createuser":

                createNewUser(req, resp);

                break;

            default:
                System.out.println("we're in the default post");
                break;
        }


    }


    private void processLoginForUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        BufferedReader br = req.getReader();

        StringBuilder sb = new StringBuilder();

        String line = br.readLine();

        // here we are reading through each line of the req.getReader
        //it can be one line or more than one line
        //creating a while loop is safer
        while (line != null) {
            sb.append(line);
            //just in case we have more than one line in our reader
            line = br.readLine();
        }

//        System.out.println(sb);
        String body = sb.toString();

        String[] sepByAmp = body.split(",");

        List<String> values = new ArrayList<String>();

        for (String pair : sepByAmp) { // each element in array looks like this
//            values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value -> [bob, pass]

            pair = pair.replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
//            System.out.println(pair);

            String target = pair.substring(pair.indexOf(":") + 1);
//            System.out.println(target);
            values.add(target); // we are trimming our string

        }
//
//        for (String value : values) {
//            System.out.println(value);
//        }
        String username = values.get(0); // bob
        String password = values.get(1); // pass

        User user = userDAO.loginUser(username, password);


        if (user != null) {
            resp.setStatus(200);

            // grab the session & add the user to the session
            //it manages whos logged in and whose logged out
            //created a session between our client and server
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // print the logged in user to the screen
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");

            ObjectMapper om = new ObjectMapper();
            // convert the object with the object mapper
            out.println(om.writeValueAsString(user));

            // log it!
//            System.out.println("The user " + username + " has logged in.");

        } else {
            resp.setStatus(204);
        }

    }





    private void createNewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader br = req.getReader();

        StringBuilder sb = new StringBuilder();

        String line = br.readLine();

        // here we are reading through each line of the req.getReader
        //it can be one line or more than one line
        //creating a while loop is safer
        while (line != null) {
            sb.append(line);
            //just in case we have more than one line in our reader
            line = br.readLine();
        }

        System.out.println(sb);
        String body = sb.toString();

        String[] sepByComma = body.split(",");

        List<String> values = new ArrayList<String>();

        for (String pair : sepByComma) { // each element in array looks like this
//           {"ticket_id":"1","description":"Gas at Esso Station","user_id":4}
            //System.out.println(pair);
            pair = pair.replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
            //System.out.println(pair);

            String target = pair.substring(pair.indexOf(":") + 1);
//            System.out.println(target);
            values.add(target); // we are trimming our string

        }


        String firstName = values.get(0);

        String lastName = values.get(1);
        String email= values.get(2);
                String zodiacSign = values.get(3);


        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(zodiacSign);


        User newUser = new User(firstName,lastName,email, zodiacSign);
        Integer id = userDAO.create(newUser);
        System.out.println(id);


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(id);

        PrintWriter out = resp.getWriter();
        out.println(json);

        resp.setStatus(200);
        resp.setContentType("application/json");
    }



}