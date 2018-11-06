package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    protected Context context;
    private DataRecieveInterface dataRecieveInterface;

    public interface DataRecieveInterface {
        void onDataReceived(String data);
    }

    public EndpointAsyncTask(DataRecieveInterface dataRecieveInterface) {
        this.dataRecieveInterface = dataRecieveInterface;
    }


    public EndpointAsyncTask(MainActivity mainActivity) {
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    .setRootUrl("https://builditbigger-215608.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }
        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Create Intent to launch JokeFactory Activity
        dataRecieveInterface.onDataReceived(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
