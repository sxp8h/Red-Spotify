package com.sxp8h.redspotify.action;

import com.sxp8h.redspotify.CreateActivity;
import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.beans.Artist;
import com.sxp8h.redspotify.threads.CreateThread;
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



public class ActionCreate {
    private static CreateActivity createActivityInstance = CreateActivity.getInstance();

    public static void create(Artist artist){
        if(artist != null){
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "ARTISTS.CREATE");
            parameters.put("FORMAT", "JSON");
            parameters.put("NAME", artist.getName());
            parameters.put("URL", artist.getUrl());
            parameters.put("NATIONALITY", artist.getNationality());

            CreateThread as = new  CreateThread(parameters);
            as.execute(ip.IP_CONTROLLER);


        }
    }
    public static void modify(Artist artist){
        if(artist != null){
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "ARTISTS.MODIFY");
            parameters.put("FORMAT", "JSON");
            parameters.put("ID_ARTIST", artist.getIdArtist()+"");
            parameters.put("NAME", artist.getName());
            parameters.put("URL", artist.getUrl());
            parameters.put("NATIONALITY", artist.getNationality());

            CreateThread as = new  CreateThread(parameters);
            as.execute(ip.IP_CONTROLLER);


        }
    }
}
