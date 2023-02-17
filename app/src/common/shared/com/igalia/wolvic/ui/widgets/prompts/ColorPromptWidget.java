package com.igalia.wolvic.ui.widgets.prompts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.igalia.wolvic.R;
import com.igalia.wolvic.audio.AudioEngine;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.listeners.ColorPickerViewListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

public class ColorPromptWidget extends PromptWidget {
//current gradation of logs 0-> 6,7,8,9,10,11,12
    private AudioEngine mAudio;
    private Button mCancelButton, mOkButton;

    public ColorPromptWidget(Context aContext) {
        super(aContext);
        Log.d("HARICP","line 0");
        initialize(aContext);
        Log.d("HARICP","line 1");
    }

    public ColorPromptWidget(Context aContext, AttributeSet aAttrs) {
        super(aContext, aAttrs);
        Log.d("HARICP","line 2");
        initialize(aContext);
        Log.d("HARICP","line 3");
    }

    public ColorPromptWidget(Context aContext, AttributeSet aAttrs, int aDefStyle) {
        super(aContext, aAttrs, aDefStyle);
        Log.d("HARICP","line 4");
        initialize(aContext);
        Log.d("HARICP","line 5");
    }

    protected void initialize(Context aContext) {
        Log.d("HARICP","line 6");
        inflate(aContext, R.layout.prompt_color, this);
        Log.d("HARICP","line 7");

        //implementing ColorPickerView
        new ColorPickerView.Builder(getContext()).build();
        Log.d("HARICP","line 8");
        ColorPickerView colorPickerView =  findViewById(R.id.colorPickerView);
        Log.d("HARICP","line 9");


//        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
        Log.d("HARICP","line 10");
        //banaya hi nhi hai attatch kr rhi ho :) Maza aa gaya Ana Hehe :) T_T
        //dukh dard kasht peeda.....
//        colorPickerView.attachAlphaSlider(alphaSlideBar);
        Log.d("HARICP","line 11");
//        final BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlideBar);
        Log.d("HARICP","line 12");

//        colorPickerView.attachBrightnessSlider(brightnessSlideBar);
        Log.d("HARICP","line 13");
        colorPickerView.setHsvPaletteDrawable();
        Log.d("HARICP","line 14");
        colorPickerView.setLifecycleOwner((LifecycleOwner) this);
        Log.d("HARICP","line 15");

        mAudio = AudioEngine.fromContext(aContext);
        Log.d("HARICP","line 16");
        mLayout = findViewById(R.id.layout);
        Log.d("HARICP","line 17");
        mTitle = findViewById(R.id.title);
        Log.d("HARICP","line 18");
        mCancelButton = findViewById(R.id.cancelButton);
        Log.d("HARICP","line 19");
        mOkButton = findViewById(R.id.okButton);
        Log.d("HARICP","line 20");
        mCancelButton.setOnClickListener(v -> {
            Log.d("HARICP","line 21");
            if (mAudio != null) {
                Log.d("HARICP","line 22");
                mAudio.playSound(AudioEngine.Sound.CLICK);
                Log.d("HARICP","line 23");
            }
            if (mPromptDelegate != null && mPromptDelegate instanceof ColorPromptDelegate) {
                Log.d("HARICP","line 24");
                mPromptDelegate.dismiss();
                Log.d("HARICP","line 25");
            }
            Log.d("HARICP","line 26");
            hide(REMOVE_WIDGET);
            Log.d("HARICP","line 27");
        });

        mOkButton.setOnClickListener(v -> {
            Log.d("HARICP","line 28");
            if (mAudio != null) {
                Log.d("HARICP","line 29");
                mAudio.playSound(AudioEngine.Sound.CLICK);
                Log.d("HARICP","line 30");
            }
            if (mPromptDelegate != null && mPromptDelegate instanceof ColorPromptDelegate) {
                Log.d("HARICP","line 31");
                ((ColorPromptDelegate) mPromptDelegate).confirm("#FFBF00");
                Log.d("HARICP","line 32");
            }
            hide(REMOVE_WIDGET);
            Log.d("HARICP","line 33");
        });
    }

    public interface ColorPromptDelegate extends PromptDelegate {
        void confirm(@NonNull final String color);
    }
}