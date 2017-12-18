package com.sxp8h.redspotify.threads;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.MainActivity;
import com.sxp8h.redspotify.beans.User;
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



public class MainThread extends AsyncTask<String, Integer, Boolean>{

    // MainActivity context
    private static MainActivity mainActivityInstance = MainActivity.getInstance();

    // Hashmap parameters <key, value>
    private HashMap<String, String> params = null;

    // Server response is on json and allocated on this arrayList
    private ArrayList<User> listUser = null;

    // ProgressBar
    private ProgressDialog progressDialog = new ProgressDialog(MainActivity.getInstance().getApplicationContext());

    // JSONArray for response from the server
    private JSONArray serverResult;

    // Constructor
    public MainThread(HashMap<String, String> params) {
        this.params = params;
    }

    @Override
    protected Boolean doInBackground(String... parameters) {
        String urlServer = parameters[0];

        Post post = new Post();
        serverResult = post.getServerDataPost(params, urlServer);

        listUser = User.getArrayListFromJSON(serverResult);
        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            if (listUser != null && listUser.size() > 0){
                User user = listUser.get(0);
                Intent listActivity = new Intent(MainActivity.getInstance().getBaseContext(), ListActivity.class);
                mainActivityInstance.startActivity(listActivity);
            }

        }
    }

}
