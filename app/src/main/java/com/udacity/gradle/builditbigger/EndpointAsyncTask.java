package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.vivek.panchal.jokefactory.DisplayJokeActivity;

import java.io.IOException;


class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-215608.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApiService = builder.build();
        }

        context = params[0];


        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        // Put the string in the envelope
        intent.putExtra(DisplayJokeActivity.JOKE_KEY, result);
        context.startActivity(intent);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
