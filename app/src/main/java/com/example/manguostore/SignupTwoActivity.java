package com.example.manguostore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SignupTwoActivity extends AppCompatActivity {

    //for the layouts to complete animation
    RelativeLayout rellay1, rellay2;

    //some firebase stuff
    private FirebaseAuth auth;
    public String id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference dr;

    //for the image upload
    private static final int PICK_IMAGE_REQUEST = 1;
    LinearLayout linearLayout;
    ImageView myImage;
    Uri imageUri;
    String id_image;
    StorageReference mStorageRef;
    FirebaseFirestore store = FirebaseFirestore.getInstance();
    // To Prevent reloading the same image while loading
    StorageTask mUploadTask;

    // here I am getting the data from SIgnupActivity using intents
    //String brandName = getIntent().getExtras().getString("name");
    //String email = getIntent().getExtras().getString("email");
    //String password = getIntent().getExtras().getString("password");

    //The other stuff needed
    ProgressBar mProgressBar;
    Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);

        createAccount = (Button) findViewById(R.id.create_ac_btn);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_two);
        linearLayout = (LinearLayout) findViewById(R.id.layout_image_two);
        myImage = (ImageView) findViewById(R.id.myimage_two);


        //for the image upload
        mStorageRef = FirebaseStorage.getInstance().getReference("ID_uploads");


    }


    public void getIdFromPhone(View view) {
        openFileChooser();
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            Picasso.with(this).load(imageUri).into(myImage);
        }
    }

    public void creatAccount(View view) {
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(SignupTwoActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
        } else {
            uploadFile();
        }
    }

    // Getting extention from file
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private  void uploadFile(){
        if (imageUri != null){
            //creates a big number thet is unique (milisecs)
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageUri));
            mUploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(SignupTwoActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            ID_Upload ID_upload = new ID_Upload(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });

        }else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }
}
