package com.example.objectanimator;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Activity has been created");
        super.onCreate(savedInstanceState);
        startStuff(savedInstanceState);

        TextView firstTextView = findViewById(R.id.firstTextView);
        TextView secondTextView = findViewById(R.id.secondTextView);
        TableLayout table = findViewById(R.id.imageTableLayout);

        startFadeInAnimation(firstTextView, R.anim.fade_in);
        applyTableRowAnimations(table, R.anim.custom_anim);
        startFadeInWithListener(secondTextView, R.anim.fade_in2);
    }

    private void startFadeInAnimation(TextView textView, int animationResource) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, animationResource);
        textView.startAnimation(fadeAnimation);
    }

    private void applyTableRowAnimations(TableLayout tableLayout, int animationRes) {
        Animation rowAnimation = AnimationUtils.loadAnimation(this, animationRes);
        LayoutAnimationController controller = new LayoutAnimationController(rowAnimation);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            row.setLayoutAnimation(controller);
        }
    }

    private void startFadeInWithListener(TextView textView, int animationRes) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(this, animationRes);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Vacio
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this, EmptyActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //Vacio
            }
        });
        textView.startAnimation(fadeAnimation);
    }

    public void startStuff(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView firstTextView = findViewById(R.id.firstTextView);
        firstTextView.clearAnimation();
        TextView secondTextView = findViewById(R.id.secondTextView);
        secondTextView.clearAnimation();
        TextView thirdTextView = findViewById(R.id.thirdTextView);
        thirdTextView.clearAnimation();

        TableLayout table = (TableLayout) findViewById(R.id.imageTableLayout);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
    }

}