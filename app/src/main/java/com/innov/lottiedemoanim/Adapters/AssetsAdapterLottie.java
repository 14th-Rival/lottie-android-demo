package com.innov.lottiedemoanim.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.innov.lottiedemoanim.Interfaces.OnClickListener;
import com.innov.lottiedemoanim.MainActivity;
import com.innov.lottiedemoanim.Models.AssetsModelLottie;
import com.innov.lottiedemoanim.R;

import java.util.ArrayList;
import java.util.List;

public class AssetsAdapterLottie extends RecyclerView.Adapter<AssetsAdapterLottie.LottieViewHolder> {

    private OnClickListener onClickListener;
    private List<AssetsModelLottie> assetsModelLotties = new ArrayList<>();
    private Context mContext;
    private int selectedItem;
    private MainActivity mMainActivity;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AssetsAdapterLottie(List<AssetsModelLottie> assetsModelLotties, Context mContext, MainActivity mainActivity) {
        this.assetsModelLotties = assetsModelLotties;
        this.mContext = mContext;
        this.mMainActivity = mainActivity;
        selectedItem = 0;
    }

    @Override
    public int getItemCount() {
        return assetsModelLotties.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LottieViewHolder holder, int position) {
        AssetsModelLottie currentItem = assetsModelLotties.get(position);

        try {
            holder.mAssetsHolder.setAnimation(currentItem.getAssetsItem());
            holder.mAssetsHolder.playAnimation();
            holder.mAssetsHolder.loop(true);

            if (selectedItem == holder.getAdapterPosition()) {
                holder.mAssetsHolder.setBackground(
                        ContextCompat.getDrawable(
                                holder.mAssetsHolder.getContext(),
                                R.drawable.bg_asset_selected)
                );
                mMainActivity.setAnimationView(currentItem.getAssetsItem());
            }

            else {
                holder.mAssetsHolder.setBackground(
                        ContextCompat.getDrawable(
                                holder.mAssetsHolder.getContext(),
                                R.drawable.bg_asset_not_selected
                        )
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(v -> {
            int previousItem = selectedItem;
            selectedItem = holder.getAdapterPosition();

            notifyItemChanged(previousItem);
            notifyItemChanged(holder.getAdapterPosition());
        });

    }

    @NonNull
    @Override
    public LottieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lottie_assets, parent, false);
        LottieViewHolder lottieViewHolder = new LottieViewHolder(view, onClickListener);
        return lottieViewHolder;
    }

    public static class LottieViewHolder extends RecyclerView.ViewHolder{
        private LottieAnimationView mAssetsHolder;
        public LottieViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            mAssetsHolder = itemView.findViewById(R.id.assets_holder);

            itemView.setOnClickListener(v -> {
                if (onClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        onClickListener.onClick(v, position);
                    }
                }
            });
        }
    }
}
