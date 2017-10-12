package papb.coba.parkinsonkit;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class TrainingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout mLinearLayout, mLinearLayout1;
    LinearLayout mLinearLayoutHeader, mLinearLayoutHeader1;
    ValueAnimator mAnimator, mAnimator1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_information) {
            Intent information_intent = new Intent(this, InformationActivity.class);
            startActivity(information_intent);
        } else if (id == R.id.nav_identification) {
            Intent identification_intent = new Intent(this, TremorIdentificationActivity.class);
            startActivity(identification_intent);
        } else if (id == R.id.nav_training) {
            Intent training_intent = new Intent(this, MainActivity.class);
            startActivity(training_intent);
        } else if (id == R.id.nav_community) {

        } else if (id == R.id.nav_share) {
            Intent komunitas_intent = new Intent(this, InformationKomunitas.class);
            startActivity(komunitas_intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
