package com.manikganj.districtapp.ads;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdmobAds {


    private AdMobCallBack adMobCallBack;
    private static InterstitialAd minterstitialAd;


    public AdmobAds() {
    }


    public AdmobAds(AdMobCallBack adMobCallBack) {
        this.adMobCallBack = adMobCallBack;
    }







    public static void sdkInitialize(Context context) {
        if (!Constant.IS_ADS_ON) return;

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(context, initializationStatus -> {
                    });
                })
                .start();
    }


    public static void setBanner(LinearLayout adContainerView, Context context) {

        if (!Constant.IS_ADS_ON) return;

        AdView adView = new AdView(context);
        adView.setAdUnitId(Constant.BANNER_ADS);
        adView.setAdSize(AdSize.BANNER);

        adContainerView.removeAllViews();
        adContainerView.addView(adView);


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }





    public static void load_Interstitial_ads(Context context){
        if (!Constant.IS_ADS_ON) return;


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, Constant.Interstitial_ads, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                minterstitialAd = interstitialAd;


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {


                minterstitialAd = null;

            }
        });



    }


    public void showinterstitialAd(Activity activity,boolean isReload){
        if (!Constant.IS_ADS_ON){
            notifyDismiss();
            return;
        }

        if (minterstitialAd==null){
            notifyDismiss();
            return;
        }

        minterstitialAd.show(activity);

        minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {



                if (isReload){
                    minterstitialAd = null;
                    load_Interstitial_ads(activity);
                }

                notifyDismiss();


            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
               notifyDismiss();
            }
        });

    }


    private void notifyDismiss(){
        if (adMobCallBack!=null){
            adMobCallBack.onDismiss();
        }
    }








}
