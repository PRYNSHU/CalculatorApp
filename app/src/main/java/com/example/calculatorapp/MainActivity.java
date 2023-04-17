package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtres , txtshow;
    MaterialButton btnC , btnOpenBracket , btnCloseBracket , btndivide ,btnmultiple , btnminus, btnadd , btnequalto , btnallClear;
    MaterialButton btn1 , btn2 ,btn3 ,btn4 ,btn5 ,btn6 ,btn7 ,btn8 ,btn9 ,btn0 ,btndot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign ids
        assignId();

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;  //view to btn form
        String btntxt = button.getText().toString(); //btn clicked , stores to btntxt
        String data = txtshow.getText().toString();

        if(btntxt.equals("AC")){
            //all clear the textbox
            txtshow.setText("");
            txtres.setText("0");
            return;
        }

        if(btntxt.equals("C")){
            //clear the last string
            data = data.substring(0 , data.length() -1);
        }else{
             //concatinate the data string whenever the btn is clicked
            data = data + btntxt;
        }

        String finalres = getdata(data);

        if(btntxt.equals("=")){
            //ans printing
            txtres.setText(finalres);
            return;
        }
        txtshow.setText(data); //showing the whole string in txtshow box

        if(!finalres.equals("ERR")){
            txtres.setText(finalres);
        }

    }

    String getdata(String data){

        //don't know , whats going on
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable s = context.initStandardObjects();
            String finalres = context.evaluateString(s , data , "java" , 1 , null).toString();
            return finalres;
        }catch (Exception e){
            return "ERR";
        }
    }



    //assign ids by using fun
    void ids(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    void assignId(){
        txtres = findViewById(R.id.result);
        txtshow = findViewById(R.id.show);

        ids(btn0 , R.id.btn_0);
        ids(btn1 , R.id.btn_1);
        ids(btn2 , R.id.btn_2);
        ids(btn3 , R.id.btn_3);
        ids(btn4 , R.id.btn_4);
        ids(btn5 , R.id.btn_5);
        ids(btn6 , R.id.btn_6);
        ids(btn7 , R.id.btn_7);
        ids(btn8 , R.id.btn_8);
        ids(btn9 , R.id.btn_9);
        ids(btndot , R.id.btn_dot);

        ids(btnC , R.id.btn_c);
        ids(btnallClear , R.id.btn_allclear);
        ids(btnOpenBracket , R.id.btn_openbracket);
        ids(btnCloseBracket, R.id.btn_closebracket);

        ids(btnadd , R.id.btn_add);
        ids(btnminus , R.id.btn_minus);
        ids(btnmultiple , R.id.btn_multiply);
        ids(btndivide , R.id.btn_divide);
        ids(btnequalto, R.id.btn_equalsTo);
    }

}