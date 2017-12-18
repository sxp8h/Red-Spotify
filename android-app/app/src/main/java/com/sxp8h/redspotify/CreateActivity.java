package com.sxp8h.redspotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sxp8h.redspotify.action.ActionCreate;
import com.sxp8h.redspotify.beans.Artist;

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


public class CreateActivity extends AppCompatActivity {

    private static CreateActivity createActivity;
    public static CreateActivity getInstance(){
        return createActivity;
    }

    private EditText txtCreateName;
    private EditText txtCreateUrl;
    private EditText txtCreateNationality;
    private Button btnCreateArtist;
    private boolean modify;
    private int idArtist;
    private String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        createActivity = this;

        txtCreateName = (EditText) findViewById(R.id.txtCreateName);
        txtCreateUrl = (EditText) findViewById(R.id.txtCreateUrl);
        txtCreateNationality = (EditText) findViewById(R.id.txtCreateNationality);
        btnCreateArtist = (Button) findViewById(R.id.btnCreateArtist);

        validate();
        if(modify){
            btnCreateArtist.setText("Modify Artist");
        }
        btnCreateArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Artist aux = new Artist();
                aux.setIdArtist(idArtist);
                aux.setName(txtCreateName.getText().toString());
                aux.setUrl(txtCreateUrl.getText().toString());
                aux.setNationality(txtCreateNationality.getText().toString());
                if(modify) {
                    ActionCreate.modify(aux);
                }else{
                    ActionCreate.create(aux);
                }
            }
        });
    }

    private void validate(){
        Bundle data = getIntent().getExtras();

        String name = data != null ? data.getString("NAME") : null;
        String url = data != null ? data.getString("URL") : null;
        String nationality = data != null ? data.getString("NATIONALITY") : null;

        if(name != null && !name.isEmpty()) {
            txtCreateName.setText(name);
            idArtist = data.getInt("ID_ARTIST");
            modify = true;
        }

        if(url != null && !url.isEmpty()) {
            txtCreateUrl.setText(url);
            modify = true;
        }

        if(nationality != null && !nationality.isEmpty()) {
            txtCreateNationality.setText(nationality);
            modify = true;
        }
    }
}
