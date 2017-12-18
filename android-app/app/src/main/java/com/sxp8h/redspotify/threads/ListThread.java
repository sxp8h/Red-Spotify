package com.sxp8h.redspotify.threads;

import android.os.AsyncTask;

import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.R;
import com.sxp8h.redspotify.adapter.AdapterArtist;
import com.sxp8h.redspotify.beans.Artist;
import com.sxp8h.redspotify.beans.User;
import com.sxp8h.redspotify.utils.Post;
import com.sxp8h.redspotify.utils.ip;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import static com.sxp8h.redspotify.ListActivity.lv;


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



public class ListThread extends AsyncTask<String, Integer, Boolean> {

    // ListActivityContext
    private static ListActivity listActivityInstance = ListActivity.getInstance();


    private HashMap<String, String> parametros;

    // Server response is on json and allocated on this arrayList
    private ArrayList<Artist> lista = null;


    public ListThread(HashMap<String, String> parametros){
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
        super.onPostExecute(aBoolean);

        if(aBoolean){
            if (lista != null && lista.size() > 0){
                for(Artist artist : lista){
                    String url = ip.IP_IMAGE_ROUTE;
                    artist.setUrl(url + artist.getUrl());
                }

                AdapterArtist adapterArtist = new AdapterArtist(listActivityInstance.getBaseContext(), R.layout.item_artist, lista);
                lv.setAdapter(adapterArtist);
            }
        }

    }
}
