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



public class Artist {

    // TABLE Artist
    private final static String TABLE_NAME = "RED_ARTISTS";
    private final static String ID_ARTIST = "ID_ARTIST";
    private final static String NAME = "NAME";
    private final static String URL = "URL";
    private final static String NATIONALITY = "NATIONALITY";

    // Atributes
    private int idArtist;
    private String name;
    private String url;
    private String nationality;

    // Constructor
    public Artist(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Artist(String name, String url, String nationality) {
        this.name = name;
        this.url = url;
        this.nationality = nationality;
    }
    public Artist() {}

    // Getter and Setter
    @Override
    public String toString() {
        return "Artist{" + "idArtist=" + idArtist + ", name=" + name + ", url=" + url + ", nationality=" + nationality + '}';
    }

    public int getIdArtist() {
        return idArtist;
    }
    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String toArrayJson(ArrayList<Artist> list){
        String resp = "";
        int i = 0 ;
        resp+="[";
        for (Artist artist: list) {
            resp+="{";
            resp+= "'" + ID_ARTIST + "'";
            resp+= ":";
            resp+= "'" + artist.getIdArtist() + "'";
            resp+=",";
            resp+= "'" + NAME +"'";
            resp+= ":";
            resp+="'" + artist.getName() + "'";
            resp+=",";
            resp+= "'" + URL +"'";
            resp+= ":";
            resp+="'" + artist.getUrl() + "'";
            resp+=",";
            resp+= "'" + NATIONALITY +"'";
            resp+= ":";
            resp+="'" + artist.getNationality() + "'";
            resp+="}";
            resp += (i+1==list.size())?"":",";
            i++;
        }
        resp+="]";

        return resp;
    }

    public static ArrayList<Artist> getArrayListFromJSON(JSONArray serverResult) {
        ArrayList<Artist> listClients = null;
        try{
            if(serverResult != null && serverResult.length()>0){
                listClients = new ArrayList<>();
                for (int i = 0; i < serverResult.length(); i++){
                    JSONObject jsonDecoded = serverResult.getJSONObject(i);

                    Artist client = new Artist();
                    client.setIdArtist(jsonDecoded.getInt(ID_ARTIST));
                    client.setName(jsonDecoded.getString(NAME));
                    client.setUrl(jsonDecoded.getString(URL));
                    client.setNationality(jsonDecoded.getString(NATIONALITY));
                    listClients.add(client);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listClients;
    }
}
