package papb.coba.parkinsonkit;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Created by NADYA ARIZKA on 14/12/2015.
 */
public class VideoThumbnail extends AppCompatActivity {

    LinearLayout mLinearLayout, mLinearLayout1;
    LinearLayout mLinearLayoutHeader, mLinearLayoutHeader1;
    ValueAnimator mAnimator, mAnimator1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_thumbnail);

        //BUAT EXPANDABLE 1
        mLinearLayout = (LinearLayout) findViewById(R.id.expandable);
        mLinearLayoutHeader = (LinearLayout) findViewById(R.id.header);

        ////Add onPreDrawListener
        ////MLINEARLAYOUT EXP1
        mLinearLayout.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        mLinearLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                        mLinearLayout.setVisibility(View.GONE);

                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        mLinearLayout.measure(widthSpec, heightSpec);

                        mAnimator = slideAnimator(0, mLinearLayout.getMeasuredHeight());
                        return true;
                    }
                });

        ////MLINEARLAYOUTHEADER EXP1
        mLinearLayoutHeader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLinearLayout.getVisibility() == View.GONE) {
                    expand();
                } else {
                    collapse();
                }
            }
        });

        //BUAT EXPANDABLE 2
        mLinearLayout1 = (LinearLayout) findViewById(R.id.expandable1);
        mLinearLayoutHeader1 = (LinearLayout) findViewById(R.id.header1);

        ////Add onPreDrawListener
        //// MLINEARLAYOUT EXP2
        mLinearLayout1.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        mLinearLayout1.getViewTreeObserver().removeOnPreDrawListener(this);
                        mLinearLayout1.setVisibility(View.GONE);

                        final int widthSpec1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        mLinearLayout1.measure(widthSpec1, heightSpec1);

                        mAnimator1 = slideAnimator1(0, mLinearLayout1.getMeasuredHeight());
                        return true;
                    }
                });

        //// MLINEARLAYOUTHEADER EXP2
        mLinearLayoutHeader1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLinearLayout1.getVisibility() == View.GONE) {
                    expand1();
                } else {
                    collapse1();
                }
            }
        });
    }

    private void expand() {
        //set Visible
        mLinearLayout.setVisibility(View.VISIBLE);
        mAnimator.start();
    }

    private void collapse() {
        int finalHeight = mLinearLayout.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                mLinearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = mLinearLayout.getLayoutParams();
                layoutParams.height = value;
                mLinearLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    //Expand video2
    private void expand1() {
        //set Visible
        mLinearLayout1.setVisibility(View.VISIBLE);
        mAnimator1.start();
    }

    private void collapse1() {
        int finalHeight1 = mLinearLayout1.getHeight();

        ValueAnimator mAnimator1 = slideAnimator1(finalHeight1, 0);

        mAnimator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                mLinearLayout1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAnimator1.start();
    }

    private ValueAnimator slideAnimator1(int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams1 = mLinearLayout1.getLayoutParams();
                layoutParams1.height = value;
                mLinearLayout1.setLayoutParams(layoutParams1);
            }
        });
        return animator;
    }


    public void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.video1:
                Intent vidIntent1 = new Intent(this, VideoViewActivity.class);
                startActivity(vidIntent1);
                break;

            case R.id.video2:
                Intent vidIntent2 = new Intent(this, VideoViewActivity.class);
                startActivity(vidIntent2);
                break;

        }

    }
}
