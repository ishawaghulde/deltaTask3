package com.example.android.yagami;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;


public class TypeWriter extends android.support.v7.widget.AppCompatTextView {
    private CharSequence chars;
    private int index;
    private long delay = 50;
    public TypeWriter(Context context){
        super(context);
    }
    public TypeWriter(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    private Handler handler = new Handler();
    private Runnable characterAdder = new Runnable(){

        @Override
        public void run() {
            setText(chars.subSequence(0, index++));
            if(index <= chars.length()){
                handler.postDelayed(characterAdder, delay);
            }
        }
    };

    public void animateText(CharSequence txt){
        chars = txt;
        index = 0;
        setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, delay);
    }

    public void setChars(){
        handler.removeCallbacks(characterAdder);
    }

    public void setCharactrDelay(long m){
        delay = m;
    }
}
