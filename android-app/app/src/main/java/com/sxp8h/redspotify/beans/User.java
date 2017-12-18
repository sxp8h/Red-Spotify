package com.sxp8h.redspotify.beans;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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


public class User {

    // TABLE User
    private final static String TABLE_NAME = "RED_USERS";
    private final static String ID_USER = "ID_USER";
    private final static String NAME = "NAME";
    private final static String SURNAME = "SURNAME";
    private final static String EMAIL = "EMAIL";
    private final static String PASSWORD = "PASSWORD";

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

    public static ArrayList<User> getArrayListFromJSON(JSONArray serverResult) {
        ArrayList<User> listClients = null;
        try{
            if(serverResult != null && serverResult.length()>0){
                listClients = new ArrayList<>();
                for (int i = 0; i < serverResult.length(); i++){
                    JSONObject jsonDecoded = serverResult.getJSONObject(i);

                    User client = new User();
                    client.setIdUser(jsonDecoded.getInt(ID_USER));
                    client.setName(jsonDecoded.getString(NAME));
                    client.setSurname(jsonDecoded.getString(SURNAME));
                    client.setEmail(jsonDecoded.getString(EMAIL));
                    client.setPassword(jsonDecoded.getString(PASSWORD));

                    listClients.add(client);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listClients;
    }
}

