package com.innov.lottiedemoanim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.innov.lottiedemoanim.Adapters.AssetsAdapterLottie;
import com.innov.lottiedemoanim.Models.AssetsModelLottie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<AssetsModelLottie> assetsModelLotties = new ArrayList<>();
    private LottieAnimationView
            mAnimView,
            mToggle1,
            mToggle2;

    private TextView tv_fileName;

    // Index Toggle Button
    private int
            autoStart,
            autoLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_fileName = findViewById(R.id.tv_filename);
        RecyclerView mRecyclerView = findViewById(R.id.rv_assets_lottie);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(
                        getBaseContext(),
                        RecyclerView.HORIZONTAL,
                        false
                );

        AssetsAdapterLottie assetsAdapterLottie =
                new AssetsAdapterLottie(
                        setAssetList(),
                        getBaseContext(),
                        MainActivity.this
                );

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(assetsAdapterLottie);

        mAnimView = findViewById(R.id.lottie_viewer);
        mToggle1 = findViewById(R.id.toggle_auto);
        mToggle2 = findViewById(R.id.toggle_loop);

        Button btn_play = findViewById(R.id.btn_play_anim);
        Button btn_stop = findViewById(R.id.btn_stop_anim);

        setmToggle(mToggle1);
        setmToggle(mToggle2);

        mToggle1.setOnClickListener(v -> autoStart = changeState(mToggle1, autoStart));
        mToggle2.setOnClickListener(v -> autoLoop = changeState(mToggle2, autoLoop));

        btn_play.setOnClickListener(v -> playAnim());
        btn_stop.setOnClickListener(v -> stopAnim());
    }

    public void setAnimationView(String s){
        mAnimView.setAnimation(s);
        if (autoStart == 1){
            mAnimView.playAnimation();
        }
        if (autoLoop == 1){
            mAnimView.loop(true);
        }
        if (autoLoop == 0){
            mAnimView.loop(false);
        }
        tv_fileName.setText("Filename: "+s);
    }

    private void playAnim(){
        mAnimView.playAnimation();
    }

    private void stopAnim(){
        mAnimView.cancelAnimation();
    }

    private void setmToggle(LottieAnimationView mView){
        mView.setAnimation("1.json");
        mView.setSpeed(3F);
    }

    private List<AssetsModelLottie> setAssetList(){
        try {
            String[] str = getAssets().list("");
            if (assetsModelLotties.isEmpty()){
                for (String s : str) {
                    if (s.endsWith("json")) {
                        Log.d("stringEnds", s);
                        AssetsModelLottie assetsModelLottie = new AssetsModelLottie(s);
                        assetsModelLotties.add(assetsModelLottie);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assetsModelLotties;
    }

    private int changeState(
            LottieAnimationView lottieAnimationView,
            int flag
    ) {
        if (flag == 0) {
            lottieAnimationView.setMinAndMaxProgress(0f, 0.5f);
            lottieAnimationView.playAnimation();
            flag = 1;
        } else {
            lottieAnimationView.setMinAndMaxProgress(0.5f, 1f);
            lottieAnimationView.playAnimation();
            flag = 0;
        }
        return flag;
    }
}