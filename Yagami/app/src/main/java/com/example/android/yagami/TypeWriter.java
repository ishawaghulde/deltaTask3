package com.example.android.yagami;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;


public class TypeWriter extends android.support.v7.widget.AppCompatTextView {
    private CharSequence chars;
    private int index;
    private long delay = 250;
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
            String str = chars.toString();
            int p = str.indexOf(' ', index) - index;
            int q = str.indexOf('.', index) - index;
            if(p < 0 && q>0){
                p = q;
                setText(chars.subSequence(0, index+=p+1));
            }
            else if(p>0 && q>0){
                p = java.lang.Math.min(p,q);
                setText(chars.subSequence(0, index+=p+1));
            }
            else if(p>0 && q<0){
                setText(chars.subSequence(0, index+=p+1));
            }
            else if(p<0 && q<0 && index<chars.length()){
                p = chars.length()- index;
                setText(chars.subSequence(0,index+=p));
            }


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

}
