package com.sxp8h.redspotify.action;

import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.beans.Artist;
import com.sxp8h.redspotify.threads.ListThread;
import com.sxp8h.redspotify.utils.ip;

import java.util.HashMap;


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



public class ActionList {
    private static ListActivity listActivityInstance = ListActivity.getInstance();

    public static void list(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("ACTION", "ARTISTS.FIND_ALL");
        parameters.put("FORMAT", "JSON");

        ListThread as = new ListThread(parameters);
        as.execute(ip.IP_CONTROLLER);

    }
    public static void erase(Artist artist){
        if(artist != null && artist.getIdArtist() != 0){
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "ARTISTS.DELETE");
            parameters.put("FORMAT", "JSON");
            parameters.put("ID_ARTIST", String.valueOf(artist.getIdArtist()));

            ListThread as = new ListThread(parameters);

            as.execute(ip.IP_CONTROLLER);
        }
    }
    public static void create(Artist artist) {
        if (artist != null && artist.getIdArtist() != 0) {
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "ARTISTS.CREATE");
            parameters.put("FORMAT", "JSON");
            parameters.put("ID_ARTIST", String.valueOf(artist.getIdArtist()));
            parameters.put("NAME", artist.getName());
            parameters.put("URL", artist.getUrl());
            parameters.put("NATIONALITY", artist.getNationality());


            ListThread as = new ListThread(parameters);
            as.execute(ip.IP_CONTROLLER);
        }
    }

    public static void filter(String... filter){
        if(filter != null){
            int aux = 0;
            String name = (filter[0] == null || filter[0].equals("")) ? null : filter[0];
            String nationality = (filter[1] == null || filter[1].equals("")) ? null : filter[1];
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "ARTISTS.FIND_FILTER");
            parameters.put("FORMAT", "JSON");
            if(name != null){
                parameters.put("NAME", name);
            }else{
                parameters.put("NAME", "");
                aux++;
            }
            if(nationality != null){
                parameters.put("NATIONALITY", nationality);
            }else{
                parameters.put("NATIONALITY", "");
                aux++;
            }

            ListThread as = new ListThread(parameters);
            if(aux != 2){
                as.execute(ip.IP_CONTROLLER);
            }
            if(aux == 2){
                list();
            }

        }
    }
}
