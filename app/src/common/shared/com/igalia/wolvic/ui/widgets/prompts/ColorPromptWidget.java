package com.igalia.wolvic.ui.widgets.prompts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.igalia.wolvic.R;
import com.igalia.wolvic.audio.AudioEngine;
import com.skydoves.colorpickerview.AlphaTileView;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.flag.FlagView;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.listeners.ColorPickerViewListener;
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager;
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
        new ColorPickerView.Builder(getContext()).build();

        ColorPickerView colorPickerView =  findViewById(R.id.colorPickerView);
        colorPickerView.setFlagView(new CustomFlag(getContext(), R.layout.layout_flag));
        ColorPickerPreferenceManager manager = ColorPickerPreferenceManager.getInstance(getContext());

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
                manager.clearSavedAllData();
            }
            hide(REMOVE_WIDGET);
        });

        mOkButton.setOnClickListener(v -> {
            if (mAudio != null) {
                mAudio.playSound(AudioEngine.Sound.CLICK);
            }
            if (mPromptDelegate != null && mPromptDelegate instanceof ColorPromptDelegate) {
                String colorCodeHex = "#"+colorPickerView.getColorEnvelope().getHexCode().substring(2,8);
                manager.saveColorPickerData(colorPickerView);
                ((ColorPromptDelegate) mPromptDelegate).confirm(colorCodeHex);
            }
            hide(REMOVE_WIDGET);
        });
    }

    public interface ColorPromptDelegate extends PromptDelegate {
        void confirm(@NonNull final String color);
    }
    public class CustomFlag extends FlagView {

        private TextView textView;
        private AlphaTileView alphaTileView;

        public CustomFlag(Context context, int layout) {
            super(context, layout);
            textView = findViewById(R.id.flag_color_code);
            alphaTileView = findViewById(R.id.alpha_tile_view);
        }

        @Override
        public void onRefresh(ColorEnvelope colorEnvelope) {
            textView.setText("#" + colorEnvelope.getHexCode());
            alphaTileView.setPaintColor(colorEnvelope.getColor());
        }
    }
}