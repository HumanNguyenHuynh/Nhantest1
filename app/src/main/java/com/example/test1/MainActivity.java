package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private ListView lvList;
    private boolean isListVisible = true;
    private int animDuration = 2; // milliseconds
    private void showListView() {
        if (isListVisible) return;
        isListVisible = true;
        lvList.setVisibility(View.VISIBLE);

        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(lvList, "alpha", 0f, 1f);
        fadeAnim.setDuration(animDuration);
        fadeAnim.start();
    }
    private void hideListView() {
        if (!isListVisible) return;
        isListVisible = false;
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(lvList, "alpha", 1f, 0f);
        fadeAnim.setDuration(animDuration);
        fadeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                lvList.setVisibility(View.GONE);
            }
        });
        fadeAnim.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ivImage;
        ivImage = findViewById(R.id.ivImage);
        lvList = findViewById(R.id.lvList);

        Animation animationOut;
        // load animations
        animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lvList.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        // show/hide list view
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isListVisible) {
                    hideListView();
                } else {
                    showListView();
                }
            }
        });

        // populate list view
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvList.setAdapter(adapter);
    }
}

