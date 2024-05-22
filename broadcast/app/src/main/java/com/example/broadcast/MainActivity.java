package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private static final int MY_premissions_reguest_recive_sms=0;
TextView messageTv ,numberTv;

MyReceiver receiver=new MyReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        messageTv.setText(msg);
        numberTv.setText(phoneNo);
    }

};
protected void onResume() {
    super.onResume();
    registerReceiver(receiver,new IntentFilter(SMS_RECEIVED));

}

protected void onDestroy() {

    super.onDestroy();
    unregisterReceiver(receiver);
}
private static final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageTv=findViewById(R.id.message);
        numberTv=findViewById(R.id.number);


       // requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS},MY_premissions_reguest_recive_sms);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.RECEIVE_SMS)){

}else{

    ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.RECEIVE_SMS},MY_premissions_reguest_recive_sms);
}

}


    }//oncreate
public void onRequestPermisionsResult(int requestCode,String permissions[],int[] grantResults ){
switch(requestCode){

         case MY_premissions_reguest_recive_sms:{
              if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                  Toast.makeText(this,"Thank you for permission ^_^",Toast.LENGTH_LONG).show();

             }
              else {
                  Toast.makeText(this, "Well i cant do anythisng untill you premit me ^~^", Toast.LENGTH_LONG).show();
              }
         }
    }
    }

}