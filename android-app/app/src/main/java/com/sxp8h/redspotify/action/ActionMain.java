package com.sxp8h.redspotify.action;

import com.sxp8h.redspotify.MainActivity;
import com.sxp8h.redspotify.beans.User;
import com.sxp8h.redspotify.threads.MainThread;
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



public class ActionMain {

    private static MainActivity mainActivityInstance = MainActivity.getInstance();

    public static void validateLogin(User user){
        if(user != null && !user.getEmail().equals("")){
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("ACTION", "USERS.LOGIN");
            parameters.put("FORMAT", "JSON");
            parameters.put("USER", user.getEmail());
            parameters.put("PASSWORD", user.getPassword());

            MainThread as = new MainThread(parameters);
            as.execute(ip.IP_CONTROLLER);
        }
    }
}
