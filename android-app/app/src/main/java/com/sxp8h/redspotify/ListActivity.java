package com.sxp8h.redspotify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sxp8h.redspotify.action.ActionList;
import com.sxp8h.redspotify.adapter.AdapterArtist;

import java.util.List;

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


public class ListActivity extends AppCompatActivity {

    private static ListActivity listActivity = null;
    public static ListActivity getInstance(){
        return listActivity;
    }

    public static ListView lv;
    private EditText txtNombreFiltrar;
    private EditText txtNacionalidadFiltrar;
    private ImageView imgListCreate;
    private ImageView imgListFilter;
    private AdapterArtist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        listActivity = this;

        lv = (ListView) findViewById(R.id.lstView);
        txtNacionalidadFiltrar = (EditText) findViewById(R.id.txtNacionalidadFiltrar);
        txtNombreFiltrar = (EditText) findViewById(R.id.txtNombreFiltrar);
        imgListCreate = (ImageView) findViewById(R.id.imgListCreate);
        imgListFilter = (ImageView) findViewById(R.id.imgListFiler);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),  "Ha pulsado el item " + i, Toast.LENGTH_SHORT).show();
            }
        });

        imgListCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivity = new Intent(getBaseContext(), CreateActivity.class);
                startActivity(createActivity);
            }
        });
        imgListFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAux = txtNombreFiltrar.getText().toString();
                String nacionalidadAux = txtNacionalidadFiltrar.getText().toString();

                String name = (nombreAux == null || nombreAux.equals("")) ? null : nombreAux;
                String nationality = (nacionalidadAux == null || nacionalidadAux.equals("")) ? null : nacionalidadAux;;
                ActionList.filter(name, nationality);
            }
        });
        ActionList.list();
    }
}
