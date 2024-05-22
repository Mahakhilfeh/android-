package com.example.thirdhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private Button ok, cancel;
    private EditText lo,la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = findViewById(R.id.ok);
        lo=findViewById(R.id.lo);
        la=findViewById(R.id.la);
        cancel = findViewById(R.id.cancel);
        ActionBar bar = getSupportActionBar();

        bar.setTitle("process....");

        bar.setDisplayHomeAsUpEnabled(true);
        ok.setOnClickListener(this);


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        Log.i("home", " onCreateOptionsMenu is callled");
        MenuInflater IN = getMenuInflater();

        IN.inflate(R.menu.main_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        Log.i("home", " onPrepareOptionsMenu is callled " + menu.size());

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.i("home", " onOptionsItemSelected is callled");
        getSupportActionBar().setTitle(item.getTitle());
        if (item.getItemId() == R.id.sms) {
            Intent x = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:0592698487"));
            x.putExtra("sms_body", " this is by sms mesg");
            startActivity(x);

        } else if (item.getItemId() == R.id.email) {

            Intent x = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
            x.putExtra(Intent.EXTRA_SUBJECT, "testing email msg");
            x.putExtra(Intent.EXTRA_TEXT, "hello world, good morning all");
            x.putExtra(Intent.EXTRA_CC, new String[]{"maha.sami2003@gmail.com", "palgatesoft@yahoo.com", "s12028106@stu.najah.edu"});
            x.putExtra(Intent.EXTRA_BCC, new String[]{"maha.sami2003@gmail.com", "palgatesoft@yahoo.com", "s12028106@stu.najah.edu"});
            startActivity(x);

        } else if (item.getItemId() == R.id.map) {

            //Intent x = new Intent(MainActivity.this ,MapsActivity.class);
            //startActivity(x);
           // double latitude = c;
           // double longitude = 35.25444;
            String latitude= la.getText().toString();
            String longitude=lo.getText().toString();
            String label = "Googleplex"; // Optional label for the location marker

            String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + label + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps"); // Specify package to ensure it opens in Google Maps
            startActivity(intent);

        } else if (item.getItemId() == R.id.pic) {

            checkCameraPermission();

        }


        return super.onOptionsItemSelected(item);
    }
//ok.setOnClickListener(this);

    @Override
    public void onClick(View view) {

        if (view == ok) {
            checkCameraPermission();


        }


    }

    public void takePicture(View view) {
        checkCameraPermission();
    }
    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            startCameraActivity();
        }
    }
    private void startCameraActivity() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(cameraIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, start your camera activity
                startCameraActivity();
            } else {
                // Permission was denied, show a message to the user
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}







