package com.zeynepmervekoyuncu.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class FeedActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth=FirebaseAuth.getInstance(); //initialize ettim
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {      //option_menu'yu feedActivity'e baglamak icin yaptim
        MenuInflater menuInflater=getMenuInflater(); //xml ile buradaki kodu birbirine baglar
        menuInflater.inflate(R.menu.option_menu,menu); //option_menu'yu bagladiks
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {      //secilince ne olacagini yazdim
        if(item.getItemId()==R.id.add_post) { //yani kullanici add_post ayazan yere tikladiysa
            //upload activity'e gidilecek
            Intent intentToUpload = new Intent(FeedActivity.this, UploadActivity.class); //***************bura onemli, feedactivity'den Uploadactivity'e gecmeyi saglar*****************************
            startActivity(intentToUpload);
            //finish demiyoruz cunku kullanici signout yapmaktan vazgecip geri donebilir
        } else if (item.getItemId()==R.id.signout) {
            //signout islemleri
            auth.signOut(); //database'in de signout yaptigimizi bilmesi gerekiyor..

            Intent intentToMain = new Intent(FeedActivity.this, MainActivity.class); //***************************aynı islem****************************
            finish(); //kullanici cikis yaptigi icin finish de yazdik, geri donememesi lazim
        }
        return super.onOptionsItemSelected(item);
    }
}