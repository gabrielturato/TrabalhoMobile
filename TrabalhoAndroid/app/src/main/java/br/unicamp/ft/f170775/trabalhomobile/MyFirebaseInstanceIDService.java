package br.unicamp.ft.f170775.trabalhomobile;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIIDService";
    private static final String QUIZ_ENGAGE_TOPIC = "quiz_engage";

    public void onTokenRefresh() {
        // Colocando o token recebido no logcat
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);
        // Logo que um novo token for gerado, vamos subscrever para um,! tópico.
        FirebaseMessaging.getInstance().subscribeToTopic(QUIZ_ENGAGE_TOPIC);
    }
}
