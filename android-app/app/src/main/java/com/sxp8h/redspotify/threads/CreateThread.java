package com.sxp8h.redspotify.threads;

import android.content.Intent;
import android.os.AsyncTask;

import com.sxp8h.redspotify.CreateActivity;
import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.beans.Artist;
import com.sxp8h.redspotify.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
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



public class CreateThread extends AsyncTask<String, Integer, Boolean> {

    // ListActivityContext
    private static CreateActivity createActivityInstance = CreateActivity.getInstance();


    private HashMap<String, String> parametros;

    // Server response is on json and allocated on this arrayList
    private ArrayList<Artist> lista = null;


    public CreateThread(HashMap<String, String> parametros){
        this.parametros = parametros;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String urlServer = params[0];

        Post post = new Post();

        JSONArray result = post.getServerDataPost(parametros, urlServer);
        lista = Artist.getArrayListFromJSON(result);

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            Intent createActivity = new Intent(createActivityInstance.getBaseContext(), ListActivity.class);
            createActivityInstance.startActivity(createActivity);
        }

    }
}
