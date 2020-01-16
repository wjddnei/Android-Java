package com.example.toeholdTalk.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;
import com.example.toeholdTalk.Util.RealPathUtil;
import com.example.toeholdTalk.Retrofit.UserClient;

import java.io.File;
import java.io.IOException;

import io.socket.client.Socket;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileUpdateActivity extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_CODE=1234;
    private  static final int PICK_IMAGE_REQUEST=2345;
    Socket socket;

    ImageButton btn_upload;
    ImageView iv;

    private Uri filePath;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        btn_upload=findViewById(R.id.btn_upload);;
        iv=findViewById(R.id.iv);
        socket = wSocket.get();
        requireStoragePermission();

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
    }

    private void requireStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Send Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data.getData()!=null){
            filePath=data.getData();
            System.err.println(filePath);
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
            uploadFile(filePath);
        }
    }


    public void uploadFile(Uri fileUri){
        System.out.println(RealPathUtil.getRealPath(this, fileUri));

        File file=new File(RealPathUtil.getRealPath(this, fileUri));
        RequestBody requestId=RequestBody.create(MediaType.parse("multipart/form-data"), MyInfo.getMyId());
        final RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part requestImage=MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        Retrofit builder=new Retrofit.Builder().baseUrl("http://45.32.38.196:5000/").addConverterFactory(GsonConverterFactory.create()).build();

        builder.create(UserClient.class).uploadImage(requestImage, requestId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "upload succeed", Toast.LENGTH_SHORT).show();
                    socket.emit("requestFriendList", MyInfo.getMyId());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "on Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
