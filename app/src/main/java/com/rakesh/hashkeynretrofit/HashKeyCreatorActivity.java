package com.rakesh.hashkeynretrofit;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rakesh.hashkeynretrofit.util.Constant;
import com.rakesh.hashkeynretrofit.util.HashGenerationException;
import com.rakesh.hashkeynretrofit.util.HashGeneratorUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 10-02-2017.
 */

public class HashKeyCreatorActivity extends AppCompatActivity {

    private Button btn_video, btn_audio, btn_image;
    private TextView txt_filesize, txt_md5, txt_sha256, txt_anothermd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_key);

        btn_video = (Button) findViewById(R.id.btn_video);
        btn_audio = (Button) findViewById(R.id.btn_audio);
        btn_image = (Button) findViewById(R.id.btn_image);

        txt_filesize = (TextView) findViewById(R.id.txt_filesize);
        txt_md5 = (TextView) findViewById(R.id.txt_md5);
        txt_sha256 = (TextView) findViewById(R.id.txt_sha256);
        txt_anothermd5 = (TextView) findViewById(R.id.txt_anothermd5);

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in, Constant.videoPicker);
            }
        });

        btn_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_upload = new Intent(Intent.ACTION_GET_CONTENT);
                intent_upload.setType("audio/*");
                startActivityForResult(intent_upload, Constant.audioPicker);
            }
        });

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Constant.imagePicker);
            }
        });
    }

    private void findHashFromFilePath(String filePath) {
        try {
            Log.e("File Path: ", "" + filePath);
            File file = new File(filePath);

            String md5Hash = HashGeneratorUtils.generateMD5(file);
            System.out.println("MD5 Hash: " + md5Hash);

            String sha1Hash = HashGeneratorUtils.generateSHA1(file);
            System.out.println("SHA-1 Hash: " + sha1Hash);

            String sha256Hash = HashGeneratorUtils.generateSHA256(file);
            Log.e("SHA-256 Hash From File:", "" + sha256Hash);

        } catch (HashGenerationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constant.videoPicker) {

            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();
                Log.e("Uri:", "" + uri);
                //String filepath = getRealPathFromURI(uri);
                //Log.e("filepath: ",filepath);
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    findFileSize(inputStream);
                    findMD5FromInputStream(inputStream);
                    findSha256FromInputStream(inputStream);
                    findTimeToGetCheckSumMD5(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

        if (requestCode == Constant.audioPicker) {

            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();
                Log.e("Uri:", "" + uri);
                //String filepath = getRealPathFromURI(uri);
                //Log.e("filepath: ",filepath);
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    findFileSize(inputStream);
                    findMD5FromInputStream(inputStream);
                    findSha256FromInputStream(inputStream);
                    findTimeToGetCheckSumMD5(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

        if (requestCode == Constant.imagePicker) {

            if (resultCode == RESULT_OK) {

                Uri uri = data.getData();
                Log.e("Uri:", "" + uri);
                //String filepath = getRealPathFromURI(uri);
                //Log.e("filepath: ",filepath);
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    findFileSize(inputStream);
                    findMD5FromInputStream(inputStream);
                    findSha256FromInputStream(inputStream);
                    findTimeToGetCheckSumMD5(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void findFileSize(InputStream inputStream) {
        try {
            double sizeOfInputStram = inputStream.available();
            double sizeInMB = (sizeOfInputStram / 1024) / 1024;
            Log.e("file size in mb:", "" + sizeInMB);
            txt_filesize.setText("File Size in MB: " + sizeInMB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findMD5FromInputStream(InputStream inputStream) {
        try {
            String md5Hash = HashGeneratorUtils.generateMD5(inputStream);
            Log.e("MD5 Hash:", "" + md5Hash);
            txt_md5.setText("MD5 Hash: " + md5Hash);
        } catch (HashGenerationException ex) {
            ex.printStackTrace();
        }
    }

    private void findSha256FromInputStream(InputStream inputStream) {
        try {
            String sha256Hash = HashGeneratorUtils.generateSHA256(inputStream);
            Log.e("SHA-256 Hash:", "" + sha256Hash);
            txt_sha256.setText("SHA-256 Hash: " + sha256Hash);
        } catch (HashGenerationException ex) {
            ex.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    void findTimeToGetCheckSumMD5(InputStream inputStream) {
        long startTime = System.nanoTime();
        findCheckSum(inputStream);
        long stopTime = System.nanoTime();
        long totaltime = stopTime - startTime;
        Log.e("time togetchecksum(ms):", "" + TimeUnit.MILLISECONDS.convert(totaltime, TimeUnit.NANOSECONDS));
        //txt_anothermd5.setText("time to get checksum in ms: " + TimeUnit.MILLISECONDS.convert(totaltime, TimeUnit.NANOSECONDS));
    }


    private void findCheckSum(InputStream inputStream) {
        try {
            String md5Origin = "";//original file's md5 checksum
            String md5Checksum = HashGeneratorUtils.md5(inputStream);
            Log.e("before md5:", md5Checksum);
            //file is valid
            txt_anothermd5.setText("MD5: " + md5Checksum);
            if (md5Checksum.equals(md5Origin)) {
                Log.e("md5:", md5Checksum);
                //txt_checksum2.setText("MD5: " + md5Checksum);
            }
        } catch (Exception e) {
            Log.e("catch:", e.toString());
        }
    }
}