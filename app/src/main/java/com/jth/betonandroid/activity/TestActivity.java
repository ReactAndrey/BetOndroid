package com.jth.betonandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.jth.betonandroid.R;

import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner mSpinnerInter;
    private Button btnUpdatep;
    private Button btnUpdatesize;
    private Button btnMinus;
    private Button btnPlus;
    private Button btnDiscard;
    private Button btnUpdateprice;
    private AutoCompleteTextView txtPrice;
    private AutoCompleteTextView txtSize;
    private TextView txtLiaval;
    private TextView lblTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        initView();

    }
    public void initView()
    {
        mSpinnerInter = findViewById(R.id.persisspinner);

        btnUpdatep = findViewById(R.id.btnupdatep);
        btnUpdatesize = findViewById(R.id.btnupdatesize);
        btnMinus = findViewById(R.id.btnminus);
        btnPlus = findViewById(R.id.btnplus);
        btnDiscard = findViewById(R.id.btndiscard);
        btnUpdateprice = findViewById(R.id.btnupdateprice);

        btnUpdatep.setOnClickListener(this);
        btnUpdatesize.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);
        btnUpdateprice.setOnClickListener(this);

        lblTitle =  findViewById(R.id.lbltitle);

        txtPrice =  findViewById(R.id.txtprice);
        txtSize =  findViewById(R.id.txtsize);
        txtLiaval = findViewById(R.id.txtliaval);

        txtSize.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    double d = Double.parseDouble(textView.getText().toString());
                    txtLiaval.setText(String.format("A$%.2f",d*2));
                    return true;
            }
        });
//        String[] arraySpinner = new String[] {
//                "International", "Romania"
//        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getApplicationContext().getResources().getStringArray(R.array.cancel_at_inplay));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerInter.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnupdatep:
                break;
            case R.id.btnupdatesize:
                break;
            case R.id.btnminus:
                txtPrice.setText(String.format("%.2f",Double.parseDouble(txtPrice.getText().toString())-1));
                break;
            case R.id.btnplus:
                txtPrice.setText(String.format("%.2f",Double.parseDouble(txtPrice.getText().toString())+1));
                break;
            case R.id.btndiscard:
                break;
            case R.id.btnupdateprice:
                break;
        }
    }
}
