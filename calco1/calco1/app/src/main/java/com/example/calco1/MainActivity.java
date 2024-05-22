package com.example.calco1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.text.method.DigitsKeyListener;
//import android.util.Log;
//import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{//, CompoundButton.OnCheckedChangeListener
    private RadioButton Binary, Decimal, Octal, Hexa;
    private EditText f;//= findViewById(R.id.f);
    private TextView bans, dans, oans, hans;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.w("hello", "Back Pressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Binary = findViewById(R.id.binary);
        Decimal = findViewById(R.id.Decimal);
        Octal = findViewById(R.id.Octal);
        Hexa = findViewById(R.id.Hexa);
        bans = findViewById(R.id.bans);
        dans = findViewById(R.id.dans);
        oans = findViewById(R.id.oans);
        hans = findViewById(R.id.hans);
        f = findViewById(R.id.f);
        Binary.setOnClickListener(this);
        Decimal.setOnClickListener(this);
        Octal.setOnClickListener(this);
        Hexa.setOnClickListener(this);

        //Binary.setOnCheckedChangeListener(this);
      //  Decimal.setOnCheckedChangeListener(this);
        //Octal.setOnCheckedChangeListener(this);
      //  Hexa.setOnCheckedChangeListener(this);
        f.setOnKeyListener(this);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Numbering System Conversion");

        f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (Decimal.isChecked()) {
                    Decimal.callOnClick();
                }
                if (Binary.isChecked()) {
                    Binary.callOnClick();
                }
                if (Octal.isChecked()) {
                    Octal.callOnClick();
                }
                if (Hexa.isChecked()) {
                    Hexa.callOnClick();
                }
            }
        });
    }

    private int CurrentNumber = 0;

    @Override

    public void onClick(View e) {

        String ss = f.getText().toString();
        if (ss.isEmpty())
            ss = "0";

        int N = 0;
        if (e == Decimal)
            N = Integer.parseInt(ss, 10);
        else if (e == Binary)
            N = Integer.parseInt(ss, 2);
        else if (e == Octal)
            N = Integer.parseInt(ss, 8);
        else if (e == Hexa)
            N = Integer.parseInt(ss, 16);

        dans.setText(Integer.toString(N));
        bans.setText(Integer.toBinaryString(N));
        oans.setText(Integer.toOctalString(N));
        hans.setText(Integer.toHexString(N).toUpperCase());


    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        String s = f.getText().toString();
        if (s.isEmpty()) {
            f.setText("0");
        }
        if (Decimal.isChecked()) {
            f.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        } else if (Binary.isChecked()) {
            f.setKeyListener(DigitsKeyListener.getInstance("01"));
        } else if (Octal.isChecked()) {
            f.setKeyListener(DigitsKeyListener.getInstance("01234567"));
        } else if (Hexa.isChecked()) {
            f.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEF"));
        }
        return false;
    }

/*private int  Number=0;
    @Override
    public void onCheckedChanged(CompoundButton ie, boolean b) {



        if (((RadioButton)ie).isSelected()==false)
        {
            String ss=f.getText().toString();
            if ( ss.isEmpty())
                ss="0";
            if ((RadioButton) ie==Decimal){
                Number=Integer.parseInt(ss,10);
                System.out.println("gui1.Gui1.itemStateChanged()"+Number);


            }
            else if ((RadioButton)ie==Binary){
                Number=Integer.parseInt(ss,2);
                System.out.println("gui1.Gui1.itemStateChanged()"+Number);
            }
            else if ( (RadioButton)ie==Octal){
                Number=Integer.parseInt(ss,8);
                System.out.println("gui1.Gui1.itemStateChanged()"+Number);
            }
            else if ( (RadioButton)ie==Hexa){
                Number=Integer.parseInt(ss,16);
                System.out.println("gui1.Gui1.itemStateChanged()"+Number);
            }

        }
        else if  (((RadioButton)ie).isSelected()==true)
        {
            if ((RadioButton) ie==Decimal)
                f.setText(Integer.toString(Number));
            else if ((RadioButton)ie==Binary)
                f.setText(Integer.toBinaryString(Number));
            else if ((RadioButton) ie==Octal)
                f.setText(Integer.toOctalString(Number));
            else if ((RadioButton) ie==Hexa)
                f.setText(Integer.toHexString(Number));

        }
    }*/

}