package fpoly.phongndtph56750.myapplication;

import android.app.Application;
import android.content.Context;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fpoly.phongndtph56750.myapplication.prefs.DataStoreManager;

public class MyApplication extends Application {

    private static final String FIREBASE_URL = "https://democinema-18986-default-rtdb.firebaseio.com";

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DataStoreManager.init(getApplicationContext());
        FirebaseApp.initializeApp(this);
    }

    public DatabaseReference getFoodDatabaseReference() {
        return FirebaseDatabase.getInstance(FIREBASE_URL).getReference("/food");
    }

    public DatabaseReference getCategoryDatabaseReference() {
        return FirebaseDatabase.getInstance(FIREBASE_URL).getReference("/category");
    }

    public DatabaseReference getMovieDatabaseReference() {
        return FirebaseDatabase.getInstance(FIREBASE_URL).getReference("/movie");
    }

    public DatabaseReference getBookingDatabaseReference() {
        return FirebaseDatabase.getInstance(FIREBASE_URL).getReference("/booking");
    }

    public DatabaseReference getQuantityDatabaseReference(long foodId) {
        return FirebaseDatabase.getInstance().getReference("/food/" + foodId + "/quantity");
    }
}

