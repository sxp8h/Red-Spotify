package beans;

import java.util.ArrayList;

import interfaces.beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionOracle;

//
//  ::::::::  :::    ::: :::::::::   ::::::::  :::    :::
// :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+:
// +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+
// +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++
//        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+
// #+#    #+# #+#    #+# #+#        #+#    #+# #+#    #+#
//  ########  ###    ### ###         ########  ###    ###
//
//          --------Created by SXP8H--------

public class User implements beans<User, Integer>{
    // Active or Deactive debug mode
    private static final boolean debug = false;
    
    // Connection 
    private ConnectionOracle database = new ConnectionOracle();
    private Connection con;
    
    // TABLE User
    private final String TABLE_NAME = "RED_USERS";
    private final String ID_USER = "ID_USER";
    private final String NAME = "NAME";
    private final String SURNAME = "SURNAME";
    private final String EMAIL = "EMAIL";
    private final String PASSWORD = "PASSWORD";

    // Sql headers
    private final String SQL_CREATE = "INSERT INTO " + TABLE_NAME + " ( "+ NAME + ", " + SURNAME + ", " + EMAIL +", " + PASSWORD + " ) VALUES ";
    private final String SQL_READ = "SELECT * FROM " + TABLE_NAME;
    private final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET ";
    private final String SQL_DELETE = "DELETE FROM " + TABLE_NAME;

    // Atributes
    private int idUser;
    private String name;
    private String surname;
    private String email;
    private String password;

    // Constructor 
    public User() {}
    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    // Getter and Setters
    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password + '}';
    }

    public int getIdUser() {
            return idUser;
    }
    public void setIdUser(int idUser) {
            this.idUser = idUser;
    }
    public String getName() {
            return name;
    }
    public void setName(String name) {
            this.name = name;
    }
    public String getSurname() {
            return surname;
    }
    public void setSurname(String surname) {
            this.surname = surname;
    }
    public String getEmail() {
            return email;
    }
    public void setEmail(String email) {
            this.email = email;
    }
    public String getPassword() {
            return password;
    }
    public void setPassword(String password) {
            this.password = password;
    }
    public String toArrayJson(ArrayList<User> list){
        String resp = "";
        int i = 0 ;
        resp+="[";
        for (User user : list) {
            resp+="{";
            resp+= "'" + ID_USER + "'";
            resp+= ":";
            resp+= "'" + user.getIdUser() + "'";
            resp+=",";
            resp+= "'" + NAME +"'";
            resp+= ":";
            resp+="'" + user.getName() + "'";
            resp+=",";
            resp+= "'" + SURNAME + "'";
            resp+= ":";
            resp+="'" + user.getSurname()+ "'";
            resp+=",";
            resp+= "'" + EMAIL + "'";
            resp+= ":";
            resp+="'" + user.getEmail() + "'";
            resp+=",";
            resp+= "'" + PASSWORD +"'";
            resp+= ":";
            resp+="'" + user.getPassword() + "'";
            resp+="}";
            resp += (i+1==list.size())?"":",";
            i++;
        }
        resp+="]";
        
        return resp;
    }

    //Methods
    @Override
    public int create(User user) {
        int result;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        // int idUser = user.getIdUser();
        String name = (user.getName() == null || user.getName().equals("")) ? "<EMPTY_NAME>" : user.getName();
        String surname = (user.getSurname()  == null || user.getSurname().equals("")) ? "<EMPTY_SURNAME>" : user.getSurname();
        String email = (user.getEmail() == null || user.getEmail().equals("")) ? "<EMPTY_EMAIL>" : user.getEmail();
        String password = (user.getPassword() == null || user.getPassword().equals("")) ? "<EMPTY_PASSWORD>" : user.getPassword();
        
//        System.out.println(name);
//        System.out.println(surname);
//        System.out.println(email);
//        System.out.println(password);

        String sql = SQL_CREATE;
        sql += "(?, ?, ?, ?)";
         
//        System.out.println(sql);

        try {
            
            con = database.connect();
            PreparedStatement createSt = con.prepareStatement(sql);
            createSt.setString(1, name);
            createSt.setString(2, surname);
            createSt.setString(3, email);
            createSt.setString(4, password);
            result = createSt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
        return result;
    }

    @Override
    public ArrayList read() {
        ArrayList<User> result = new ArrayList();
        ResultSet rs;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        String sql = SQL_READ;
         
//        System.out.println(sql);

        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);
            rs = readSt.executeQuery();
            
            while(rs.next()){
                User aux = new User();
                
                aux.setIdUser(rs.getInt(ID_USER));
                aux.setName(rs.getString(NAME));
                aux.setSurname(rs.getString(SURNAME));
                aux.setEmail(rs.getString(EMAIL));
                aux.setPassword(rs.getString(PASSWORD));
                
                result.add(aux);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }
    
    public ArrayList read(int id) {
        ArrayList<User> result = new ArrayList();
        ResultSet rs;
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        String sql = SQL_READ + " WHERE " + ID_USER + " = ?";
         
//        System.out.println(sql);

        try {
            con = database.connect();
            PreparedStatement readSt = con.prepareStatement(sql);
            readSt.setInt(1, id);
            rs = readSt.executeQuery();
            
            while(rs.next()){
                User aux = new User();
                
                aux.setIdUser(rs.getInt(ID_USER));
                aux.setName(rs.getString(NAME));
                aux.setSurname(rs.getString(SURNAME));
                aux.setEmail(rs.getString(EMAIL));
                aux.setPassword(rs.getString(PASSWORD));
                
                result.add(aux);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
    }
    
    @Override
    public int update(User user) {
        // UPDATE table_name
        // SET column1 = value1, column2 = value2, ...
        // WHERE condition; 
        
        int result;
        
        int idUser = user.getIdUser();
        String name = (user.getName() == null || user.getName().equals("")) ? "<EMPTY_NAME>" : user.getName();
        String surname = (user.getSurname()  == null || user.getSurname().equals("")) ? "<EMPTY_SURNAME>" : user.getSurname();
        String email = (user.getEmail() == null || user.getEmail().equals("")) ? "<EMPTY_EMAIL>" : user.getEmail();
        String password = (user.getPassword() == null || user.getPassword().equals("")) ? "<EMPTY_PASSWORD>" : user.getPassword();
        
        System.out.println(name);
        System.out.println(surname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(idUser);

        String sql = SQL_UPDATE;
        sql += " " + NAME + " = ?, " + SURNAME + " = ?, " + EMAIL + " = ?, " + PASSWORD + " = ?";
        sql += " WHERE " + ID_USER + " = ?";
         
        System.out.println(sql);

        try {
            
            con = database.connect();
            PreparedStatement updateSt = con.prepareStatement(sql);
            updateSt.setString(1, name);
            updateSt.setString(2, surname);
            updateSt.setString(3, email);
            updateSt.setString(4, password);
            updateSt.setInt(5, idUser);
            result = updateSt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
        return result;
    }
    
    @Override
    public int delete(Integer id) {
        int result;
        String sql = SQL_DELETE + " WHERE " + ID_USER + " = ?";
         
        System.out.println(sql);
        System.out.println("id === " + id);

        try {
            con = database.connect();
            PreparedStatement deleteSt = con.prepareStatement(sql);
            deleteSt.setInt(1, id);
            result = deleteSt.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        return result;
    }

    @Override
    public int exist(Integer id) {
            // TODO
            return 0;
    }
    
    public static void main(String[] args) {
        if(debug){
            System.out.println("##########################################################");
            System.out.println("#  ::::::::  :::    ::: :::::::::   ::::::::  :::    ::: #");
            System.out.println("# :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+: #");
            System.out.println("# +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+ #");
            System.out.println("# +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++ #");
            System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
            System.out.println("#        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+ #");
            System.out.println("#  ########  ###    ### ###         ########  ###    ### #");
            System.out.println("##########################################################");
            System.out.println("------------------- TEST USERS ---------------------------");
            System.out.println("\n\n");
            System.out.println("  @ Create [WORK]");

            User user = new User();
            user.setName("prueba");
            user.setSurname("prueba");
            user.setEmail("prueba@gmail.com");
            user.setPassword("1234");
            
            System.out.println("    ~CREATED USERS: " + user.create(user));
            System.out.println("\n");System.out.println("\n");

            
            
            ArrayList<User> vA = new ArrayList<User>();
            System.out.println("  @ Read (all) [WORK]");
            vA = user.read();
            
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ USERS:");
                for (Iterator<User> i = vA.iterator(); i.hasNext();) {
                    User us = i.next();
                    System.out.println(us.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            vA.clear();
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Read (only one id[id=1]) [WORK]");
            vA = user.read(1);
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ USERS:");
                for (Iterator<User> i = vA.iterator(); i.hasNext();) {
                    User us = i.next();
                    System.out.println(us.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            vA.clear();
            System.out.println("\n");System.out.println("\n");
        
            System.out.println("  @ Read (only one id[id=3]) [WORK]");
            vA = user.read(3);
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~READ USERS:");
                for (Iterator<User> i = vA.iterator(); i.hasNext();) {
                    User us = i.next();
                    System.out.println(us.toString());
                }
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            vA.clear();
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Update (id=3) [WORK]");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("    ~UPDATED USERS :");
                user.setIdUser(3);
                user.setName("pruebaUpdate");
                user.setSurname("pruebaUpdate");
                user.setEmail("pruebaU@gmail.com");
                user.setPassword("4321");
                System.out.println(user.update(user));
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                user.setIdUser(3);
                user.setName("prueba");
                user.setSurname("prueba");
                user.setEmail("prueba@gmail.com");
                user.setPassword("1234");
                user.update(user);
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

            User userDel = new User();
            userDel.setName("AUTODELETE");
            userDel.setSurname("AUTODELETE");
            userDel.setEmail("AUTODELETE");
            userDel.setPassword("AUTODELETE");
            userDel.create(userDel);

            // Getting last id 
            int value = 0;
            ResultSet rs;
            String sql = "SELECT * FROM RED_USERS ";

            try {
                ConnectionOracle databaseStatic = new ConnectionOracle();
                Connection conStatic;
                conStatic = databaseStatic.connect();
                PreparedStatement readSt = conStatic.prepareStatement(sql);
                rs = readSt.executeQuery();

                while(rs.next()){
                    User aux = new User();

                    aux.setIdUser(rs.getInt("ID_USER"));
                    aux.setName(rs.getString("NAME"));
                    aux.setSurname(rs.getString("SURNAME"));
                    aux.setEmail(rs.getString("EMAIL"));
                    aux.setPassword(rs.getString("PASSWORD"));

                    if(rs.next()){
                        userDel.setIdUser(rs.getInt("ID_USER")+1);
                        System.out.println(rs.getInt("ID_USER"));
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\n");System.out.println("\n");
            
            System.out.println("  @ Delete (id===???) [WORK]");
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("    ~DELETED USERS :");
                System.out.println(userDel.delete(userDel.getIdUser()));
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
