package com.innov.lottiedemoanim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView mLottieAnimation;
    private TextView mTv_toggle;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLottieAnimation = findViewById(R.id.lt_toggle);
        mLottieAnimation.setOnClickListener(v -> changeState(mLottieAnimation));
    }

    private void changeState(LottieAnimationView lottieAnimationView) {
        if (flag == 0) {
            lottieAnimationView.setMinAndMaxProgress(0f, 0.5f); //Here, calculation is done on the basis of start and stop frame divided by the total number of frames
            lottieAnimationView.playAnimation();
            flag = 1;
//            mTv_toggle.setText("On");
            //---- Your code here------
        } else {
            lottieAnimationView.setMinAndMaxProgress(0.5f, 1f);
            lottieAnimationView.playAnimation();
            flag = 0;
//            mTv_toggle.setText("Off");
            //---- Your code here------
        }
    }
}