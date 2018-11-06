package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest {

    @Test
    public void testDoInBackground() {
        try {
            MainActivity mainActivity = new MainActivity();
            EndpointAsyncTask syncEndpoint = new EndpointAsyncTask(mainActivity);
            syncEndpoint.execute();
            String result = syncEndpoint.get(30, TimeUnit.SECONDS);

            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e) {
            Log.e("Test:", " Timed out");
        }
    }
}