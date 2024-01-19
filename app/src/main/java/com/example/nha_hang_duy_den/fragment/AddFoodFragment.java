package com.example.nha_hang_duy_den.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddFoodFragment extends Fragment {

    Context context;
    EditText nameFood,priceFood;
    Button btnAdd, btnChooseImg;
    ImageView imageView;
    ProgressBar progressBar;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imgFilePath;
    private Bitmap imgToStore;
    private StorageReference storageReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);
        context = container.getContext();
        nameFood = view.findViewById(R.id.foodName);
        priceFood = view.findViewById(R.id.foodPrice);
        btnAdd = view.findViewById(R.id.btnAddFood);
        btnChooseImg = view.findViewById(R.id.btn_upload);
        imageView = view.findViewById(R.id.foodImg);
        progressBar = view.findViewById(R.id.progress_bar_food);
        //firebase
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
//        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE_REQUEST);

                }catch (Exception e) {
                    Toast.makeText(context.getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd.setOnClickListener(v -> addFood(nameFood.getText().toString().trim(),(priceFood.getText().toString().trim())));
        return view;
    }

    public void addFood(String name, String price) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price)){
            Toast.makeText(context.getApplicationContext(),"Tên hoặc giá chưa được nhập",Toast.LENGTH_SHORT).show();
        }
        Menu menu = new Menu();

//        StorageReference getImg = storageReference.child("uploads")
//                .child("1703684624209_084ac55c-f930-42df-9284-edb13d741b85");
//        getImg.getBytes(1024*1024)
//                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                    @Override
//                    public void onSuccess(byte[] bytes) {
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0 , bytes.length) ;
//                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
//                        imageView.setImageBitmap(resizedBitmap);
//                    }
//                });
        upLoadsFile(menu,name,price);

        nameFood.setText("");
        priceFood.setText("");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imgFilePath = data.getData();
//               imgToStore = MediaStore.Images.Media.getBitmap(context.getContentResolver(),imgFilePath);
//               imageView.setImageBitmap(imgToStore);
                Glide.with(context).load(imgFilePath).into(imageView);
                Toast.makeText(context,String.valueOf(imgFilePath) ,Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e) {
            Toast.makeText(context.getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void upLoadsFile(Menu menu,String name,String price) {
        if (imgFilePath != null) {

            StorageReference fileReference = storageReference.child(System.currentTimeMillis() +"."+ getFile(imgFilePath));
            fileReference.putFile(imgFilePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },1000);
                    AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
//                    String imgKey = databaseReference.push().getKey();
                    menu.setNameFood(name);
                    menu.setPriceFood(Integer.valueOf(price));
//                    menu.setImgKeyFood(String.valueOf(imgKey));

                    fileReference.getDownloadUrl().addOnSuccessListener(uri -> {

                        String imageUrl = uri.toString();
                        menu.setImgPathFood(imageUrl);
                        db.menuDao().insertMenu(menu);
//                    databaseReference.child(imgKey).setValue(menu);
                        Toast.makeText(context,"Upload thành công",Toast.LENGTH_SHORT).show();
                        Toast.makeText(context.getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        // Handle the image URL
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"upload không thành công",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });

        }else {
            Toast.makeText(context,"Không chọn file",Toast.LENGTH_SHORT).show();
        }

    }
    public String getFile(Uri uri) {
        ContentResolver resolver = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }
}