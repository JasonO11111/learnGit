package com.example.network;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    private EditText edit_studentNumber;
    private EditText edit_parentNumber;
    private EditText edit_studentName;
    private TextView text_parentNumberHint;
    private TextView text_studentNumberHint;
    private TextView numberHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        edit_studentNumber = findViewById(R.id.edit_studentNumber);
        edit_parentNumber = findViewById(R.id.edit_parentNumber);
        edit_studentName = findViewById(R.id.edit_studentName);
        text_studentNumberHint = findViewById(R.id.text_studentNumberHint);
        text_parentNumberHint = findViewById(R.id.text_parentNumberHint);
        setListen1();
        setListen2();

        //设置禁止输入表情
        InputFilter inputFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if(emojiMatcher.find()){
                    return "";
                }
                return null;
            }
        };
        edit_studentName.setFilters(new InputFilter[]{inputFilter,new InputFilter.LengthFilter(5) });
    }


    public void setListen1(){
        edit_studentNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    String num = edit_studentNumber.getText().toString();
                    boolean judge = isMobile(num);
                    if (judge == false && (num.length()==11)){
                        text_studentNumberHint.setVisibility(View.VISIBLE);
                    }else if(num.isEmpty()){
                        text_studentNumberHint.setVisibility(View.INVISIBLE);
                    }
                    judge();
            }
        });
    }

    public void setListen2(){
        edit_parentNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String num = edit_parentNumber.getText().toString();
                boolean judge = isMobile(num);
                if (judge  == false && (num.length()==11)){
                    text_parentNumberHint.setVisibility(View.VISIBLE);
                }else if(num.isEmpty()){
                    text_parentNumberHint.setVisibility(View.INVISIBLE);
                }
                judge();
            }
        });
    }

    public void judge (){
        numberHint = findViewById(R.id.text_numberHint);
        String num1 = edit_studentNumber.getText().toString();
        String num2 = edit_parentNumber.getText().toString();
        if(num1.equals(num2)&&num1!=null&&num2!=null){
            numberHint.setVisibility(View.VISIBLE);
        }else if(num1.length()==11||num2.length()==11&&num1.isEmpty()||num2.isEmpty()){
            numberHint.setVisibility(View.INVISIBLE);
        }
    }


    public static boolean isMobile(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

}
