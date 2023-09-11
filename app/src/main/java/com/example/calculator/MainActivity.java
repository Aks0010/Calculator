package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView ans_dis, input_dis;
    MaterialButton btn_C, btn_AC;
    MaterialButton btn_divide, btn_mul, btn_sub, btn_add, btn_equal;
    MaterialButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ans_dis = findViewById(R.id.ans_dis);
        input_dis = findViewById(R.id.input_dis);
        assignId(btn_AC, R.id.btn_AC);
        assignId(btn_C, R.id.btn_C);
        assignId(btn_divide, R.id.btn_div);
        assignId(btn_mul, R.id.btn_mul);
        assignId(btn_sub, R.id.btn_minus);
        assignId(btn_add, R.id.btn_plus);
        assignId(btn_equal, R.id.btn_equal);
        assignId(btn_0, R.id.btn_0);
        assignId(btn_1, R.id.btn_1);
        assignId(btn_2, R.id.btn_2);
        assignId(btn_3, R.id.btn_3);
        assignId(btn_4, R.id.btn_4);
        assignId(btn_5, R.id.btn_5);
        assignId(btn_6, R.id.btn_6);
        assignId(btn_7, R.id.btn_7);
        assignId(btn_8, R.id.btn_8);
        assignId(btn_9, R.id.btn_9);
        assignId(btn_dot, R.id.btn_dot);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    MaterialButton button = (MaterialButton) view;
    String btnText  = button.getText().toString();
    String to_get_ans = input_dis.getText().toString();
   if(btnText.equals("AC")){
       input_dis.setText("");
       ans_dis.setText("0");
       return;
   }
   if(btnText.equals("=")){
       input_dis.setText(ans_dis.getText());
       ans_dis.setText("");
       return;
   }
   if(btnText.equals("C")){
       if (to_get_ans.length() != 0){

           to_get_ans = to_get_ans.substring(0,to_get_ans.length()-1);
       }
      else input_dis.setText("");
   }
   else {
       to_get_ans = to_get_ans+btnText;
   }

    input_dis.setText(to_get_ans);
   String answer = ans(to_get_ans);
   if(!answer.equals("ERROR")){
       ans_dis.setText(answer);
   }

    }
    String ans(String input){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
          String answer =  context.evaluateString(scriptable,input,"Javascript",1,null).toString();
         if(answer.endsWith(".0")){
             answer =answer.replace(".0","");
         }
          return answer; }
        catch (Exception e){
         return "ERROR";
        }

    }
}