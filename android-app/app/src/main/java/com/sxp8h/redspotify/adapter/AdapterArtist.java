package com.sxp8h.redspotify.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sxp8h.redspotify.CreateActivity;
import com.sxp8h.redspotify.ListActivity;
import com.sxp8h.redspotify.R;
import com.sxp8h.redspotify.action.ActionList;
import com.sxp8h.redspotify.beans.Artist;
import com.sxp8h.redspotify.threads.ListThread;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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



public class AdapterArtist extends ArrayAdapter<Artist> {

    private static ListActivity listActivityInstance = ListActivity.getInstance();
    private ArrayList<Artist> items;
    private Context context;

    public AdapterArtist(Context context, int fileDesing, ArrayList<Artist> artists) {
        super(context, R.layout.item_artist, artists);
        this.items = artists;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Artist o = items.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_artist, null);

            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.imgListLogo);
            holder.text = (TextView) convertView.findViewById(R.id.txtNombre);
            holder.nationality = (TextView) convertView.findViewById(R.id.txtNacionalidad);
            holder.erase = (ImageView) convertView.findViewById(R.id.imgListBorrar);
            holder.modify = (ImageView) convertView.findViewById(R.id.imgListModificar);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        holder.erase.setImageResource(R.drawable.file_delete);
        final Artist aux = o;
        holder.erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionList.erase(aux);
            }
        });

        holder.modify.setImageResource(R.drawable.file_edit);
        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigate = new Intent(listActivityInstance.getBaseContext(), CreateActivity.class);
                navigate.putExtra("ID_ARTIST", aux.getIdArtist());
                navigate.putExtra("NAME", aux.getName().toString());
                navigate.putExtra("URL", aux.getUrl().toString());
                navigate.putExtra("NATIONALITY", aux.getNationality().toString());
                navigate.putExtra("MODIFY", "true");
                listActivityInstance.startActivity(navigate);
            }
        });

        holder.text.setText(o.getName());
        holder.nationality.setText(o.getNationality());


        if (holder.icon != null) {
            new BitmapWorkerTask(holder.icon).execute(o.getUrl());
        }

        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView text;
        TextView nationality;
        ImageView erase;
        ImageView modify;
    }

    // ----------------------------------------------------
    // Load bitmap in AsyncTask
    // ref:
    // http://developer.android.com/training/displaying-bitmaps/process-bitmap.html
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView>
                imageViewReference;
        private String imageUrl;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            imageViewReference = new WeakReference<ImageView>
                    (imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            return loadImage(imageUrl);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView =
                        imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        private Bitmap loadImage(String URL) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                in = openHttpConnection(URL);
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e1) {
            }
            return bitmap;
        }

        private InputStream openHttpConnection(String strURL) throws IOException {
            InputStream inputStream = null;
            URL url = new URL(strURL);
            URLConnection conn = url.openConnection();

            try {
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                if (httpConn.getResponseCode() ==
                        HttpURLConnection.HTTP_OK) {
                    inputStream = httpConn.getInputStream();
                }
            } catch (Exception ex) {
            }
            return inputStream;
        }
    }

}
