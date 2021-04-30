package com.example.lendingapp.PostAdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lendingapp.Model.Model;
import com.example.lendingapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdHardwareDetails extends AppCompatActivity {

    private EditText adtitle;
    private EditText addescription;
    private EditText adprice;
    private EditText adlocation;
    private Button uploadbtn;
    private ImageView imageView;
    private Uri imageUri;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseRef = db.getReference().child("Hardwares");
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("Image");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_hardware_details);

        getSupportActionBar().hide(); // it is to hide the title bar of this particular page only


        adtitle = (EditText)findViewById(R.id.AdTitle);
        addescription = (EditText)findViewById(R.id.AdDescription);
        adprice = (EditText)findViewById(R.id.Price);
        adlocation = (EditText)findViewById(R.id.Location);
        uploadbtn = (Button)findViewById(R.id.btnNext);
        imageView = (ImageView)findViewById(R.id.imageSelected);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }


    private  String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(imageUri != null){
            StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()+ "." + getFileExtension(imageUri)) ;
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AdHardwareDetails.this, "Upload Successful", Toast.LENGTH_LONG).show();
                    Model upload = new Model(adtitle.getText().toString().trim(),addescription.getText().toString().trim(),
                            adprice.getText().toString().trim(),adlocation.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AdHardwareDetails.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    //double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                }
            });
        }else{
            Toast.makeText(AdHardwareDetails.this, "No file Selected", Toast.LENGTH_LONG).show();
        }
    }


}