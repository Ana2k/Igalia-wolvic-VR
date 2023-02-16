package com.igalia.wolvic.ui.widgets.prompts;

import android.content.Context;
import android.util.AttributeSet;
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

    private AudioEngine mAudio;
    private Button mCancelButton, mOkButton;

    public ColorPromptWidget(Context aContext) {
        super(aContext);
        initialize(aContext);
    }

    public ColorPromptWidget(Context aContext, AttributeSet aAttrs) {
        super(aContext, aAttrs);
        initialize(aContext);
    }

    public ColorPromptWidget(Context aContext, AttributeSet aAttrs, int aDefStyle) {
        super(aContext, aAttrs, aDefStyle);
        initialize(aContext);
    }

    protected void initialize(Context aContext) {
        inflate(aContext, R.layout.prompt_color, this);

        //implementing ColorPickerView
        ColorPickerViewListener colorPickerViewListener;
        Context context = getContext();
        ColorPickerViewListener colorListener;
        ColorPickerView colorPickerView = new ColorPickerView.Builder(context).build();
        colorPickerView = findViewById(R.id.colorPickerView);

        ColorPickerView finalColorPickerView = colorPickerView;
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                LinearLayout linearLayout = findViewById(R.id.promptColorLayout);
                linearLayout.setBackgroundColor(color);
                try {
                    finalColorPickerView.selectByHsvColor(color);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        final AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
        colorPickerView.attachAlphaSlider(alphaSlideBar);
        final BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlideBar);
        colorPickerView.attachBrightnessSlider(brightnessSlideBar);
        colorPickerView.setHsvPaletteDrawable();
        colorPickerView.setLifecycleOwner((LifecycleOwner) this);

//        colorPickerView.selectByHsvColor(color);

        mAudio = AudioEngine.fromContext(aContext);
        mLayout = findViewById(R.id.layout);
        mTitle = findViewById(R.id.title);
        mCancelButton = findViewById(R.id.cancelButton);
        mOkButton = findViewById(R.id.okButton);
        mCancelButton.setOnClickListener(v -> {
            if (mAudio != null) {
                mAudio.playSound(AudioEngine.Sound.CLICK);
            }
            if (mPromptDelegate != null && mPromptDelegate instanceof ColorPromptDelegate) {
                mPromptDelegate.dismiss();
            }
            hide(REMOVE_WIDGET);
        });

        mOkButton.setOnClickListener(v -> {
            if (mAudio != null) {
                mAudio.playSound(AudioEngine.Sound.CLICK);
            }
            if (mPromptDelegate != null && mPromptDelegate instanceof ColorPromptDelegate) {
                ((ColorPromptDelegate) mPromptDelegate).confirm("#FFBF00");
            }
            hide(REMOVE_WIDGET);
        });
    }

    public interface ColorPromptDelegate extends PromptDelegate {
        void confirm(@NonNull final String color);
    }
}