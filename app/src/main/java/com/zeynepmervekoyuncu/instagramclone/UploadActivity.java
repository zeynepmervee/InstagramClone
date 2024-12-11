package com.zeynepmervekoyuncu.instagramclone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.zeynepmervekoyuncu.instagramclone.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {
    Uri imageData;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    private ActivityUploadBinding binding;
    Bitmap selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_upload); //bu satiri sildik
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view); //aldigimiz gorunumu verebildik boylece

    }

    public void selectImageClicked(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){ //izin verilmemişse
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view, "Permission needed for gallery.",Snackbar.LENGTH_INDEFINITE).setAction("Give permission.", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //ask permission
                    }
                }).show();
            } else {
                //ask permission
            }
        } else { //izin verilmişse, galeriye intent yapariz
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //bu intent için resul launcher tanımlıyoruz başa
        }
    }
    public void uploadButtonClicked(View view){

    }

    private void registerLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { //yeni bir aktivite başlatacağız, ama sonuç için başlatacağız. sonuç:galeriye gidip o veriyi almak
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null) {
                        imageData = intentFromResult.getData(); //bunu yapabilmek için yukarıda Uri imageData degiskenini olustururuz. firebase bizden URI isteyecek
                        binding.imageView.setImageURI(imageData); //sadece bu satır sayesinde asagidaki islemleri yapmamıza gerek yok

                       /* try {
                            if(Build.VERSION.SDK_INT >= 28){
                                ImageDecoder.Source source = ImageDecoder.createSource(UploadActivity.this.getContentResolver(),imageData);
                                selectedImage = ImageDecoder.decodeBitmap(source);
                                binding.imageView.setImageBitmap(selectedImage);

                            }else{
                                selectedImage = MediaStore.Images.Media.getBitmap(UploadActivity.this.getContentResolver(),imageData);
                                binding.imageView.setImageBitmap(selectedImage);

                            }
                        } catch(Exception e){
                            e.printStackTrace();
                        } */

                    }
                }


            }
        });
    }

}