package com.example.manguostore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class StoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spCategory;
    Spinner spSubCategory;
    Spinner spSize;
    LinearLayout size, shoesize, viewGroup,quantity, amount;
    String category;
    String sub_category;
    String fit;
    EditText shoe_size, number, price;
    ProgressBar mProgressBar;
    // for getting image from phone
    private static final int PICK_IMAGE_REQUEST = 1;
    LinearLayout linearLayout;
    Button upload, checkUploads;
    ImageView myImage;
    Uri imageUri;
    String id;
    StorageReference storageReference;
    FirebaseFirestore store = FirebaseFirestore.getInstance();
    StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        spCategory = (Spinner) findViewById(R.id.category_spin);
        spSubCategory = (Spinner) findViewById(R.id.subcategory_spin);
        spSize = (Spinner) findViewById(R.id.size_spin);
        size = (LinearLayout) findViewById(R.id.size);
        shoesize = (LinearLayout) findViewById(R.id.shoesize);
        viewGroup = (LinearLayout) findViewById(R.id.line_store);
        quantity = (LinearLayout) findViewById(R.id.quantity);
        amount = (LinearLayout) findViewById(R.id.amount);
        linearLayout = (LinearLayout) findViewById(R.id.layout_image);
        upload = (Button) findViewById(R.id.upload);
        checkUploads = (Button) findViewById(R.id.show_uploads);
        myImage = (ImageView) findViewById(R.id.myimage);
        shoe_size = (EditText) findViewById(R.id.shoe_size);
        number = (EditText) findViewById(R.id.number);
        price = (EditText) findViewById(R.id.price);
        mProgressBar = findViewById(R.id.progress_bar);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        //adapter to link data to the spinner
        ArrayAdapter<CharSequence> adCategory = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adCategory);
        spCategory.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        category = parent.getItemAtPosition(position).toString();
        if(category.equals("Apparel")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.apparel,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Footwear")){
            size.setVisibility(view.INVISIBLE);
            shoesize.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.foot,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } if (category.equals("Sportswear")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sport,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Traditional")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.traditional,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Formal Wear")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.formal,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Accessories")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.accessories,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Watches and Jewelry")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.INVISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.accessories,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Cosmetics")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.INVISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cosmetics,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Vintage and Secondhand")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vintage,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getFromPhone(View view) {
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

    public void upload(View view) {
        String shoe_size_string = shoe_size.getText().toString().trim();
        String number_string = number.getText().toString().trim();
        String price_string = price.getText().toString().trim();
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(StoreActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
        }else {
            if (category.equals("Select")) {
                Toast.makeText(this, "Category must be Selected", Toast.LENGTH_SHORT).show();
            } else if (sub_category.equals("Select")) {
                Toast.makeText(this, "Sub-category must be Selected", Toast.LENGTH_SHORT).show();
            } else if (!category.equals("Footwear")) {
                if (fit.equals("Select")) {
                    Toast.makeText(this, "Size must be Selected", Toast.LENGTH_SHORT).show();
                }else if(number_string.equals("")){
                    Toast.makeText(this, "Number of items must be Indicated", Toast.LENGTH_SHORT).show();
                }else if (price_string.equals("")) {
                    Toast.makeText(this, "Price of items must be Indicated", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            } else if (category.equals("Footwear")) {
                if (shoe_size_string.equals("")) {
                    Toast.makeText(this, "Shoe Size must be Indicated", Toast.LENGTH_SHORT).show();
                } else if(number_string.equals("")){
                    Toast.makeText(this, "Number of items must be Indicated", Toast.LENGTH_SHORT).show();
                }else if (price_string.equals("")) {
                    Toast.makeText(this, "Price of items must be Indicated", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        }
    }
    private String getFileExtenstion(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(){
        if(imageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtenstion(imageUri));
            mUploadTask = fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(StoreActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                    Upload upload = new Upload(category.trim(),sub_category.trim(),fit.trim(),shoe_size.getText().toString().trim(),
                            number.getText().toString().trim(), price.getText().toString().trim(),
                            taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                    id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    store.collection(id).document().set(upload);
                }
            }). addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(StoreActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }). addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //this line of code below calculates the progress actually
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) progress);
                }
            });
        }else{
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }
}
