package com.example.bottomnamviagtionbar;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private RecyclerView advertsRecyclerView;
    private AdvertsAdapter advertsAdapter;
    private CenterZoomLayoutManager layoutManager;
    private Long wait = 12000L;
    private Long timeDelayed = 6000L;
    private int[] images = {R.drawable.baner3, R.drawable.baner4};

    private String[] titles = {
            "Car One",
            "Car Two"
    };
    private int[] image = {
            R.drawable.baner3,
            R.drawable.baner4
    };
    private TimerTask timerTask;
    private Timer timer;
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

        timer = new Timer(true);
        getAdverts();
        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new UnoFragment());
    }

    private void getAdverts() {
        advertsRecyclerView = findViewById(R.id.recyclerViewAdvert);
        layoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        advertsRecyclerView.setLayoutManager(layoutManager);
        advertsAdapter = new AdvertsAdapter(this, titles, image);
        advertsRecyclerView.setAdapter(advertsAdapter);
        advertsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstItemVisible != 0 && firstItemVisible % titles.length == 0) {
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }
            }
        });
        layoutManager.scrollToPosition((images.length / 2) - 2);
        advertsRecyclerView.post(
                new Runnable() {
                    @Override
                    public void run() {
                        int dx = (advertsRecyclerView.getWidth() - advertsRecyclerView.getChildAt(0).getWidth()) / 2;
                        advertsRecyclerView.scrollBy(-dx, 0);
                        LinearSnapHelper snapHelper = new LinearSnapHelper();
                        snapHelper.attachToRecyclerView(advertsRecyclerView);
                        setDelayTime(5000L);
                        setTouchwait(3000L);
                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    advertsRecyclerView.smoothScrollToPosition(((LinearLayoutManager) advertsRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() + 1);
                                } catch (Exception e) {
                                    //rec.setVisibility(View.GONE);
                                }
                            }
                        };
                        handler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    timer.schedule(timerTask, 0, timeDelayed);

                                } catch (IllegalStateException e) {
                                    timer.cancel();
                                }
                            }
                        };
                        try {
                            handler.postDelayed(runnable, timeDelayed);
                        } catch (Exception e) {
                        }

                        advertsRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                            @Override
                            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                                timerTask.cancel();
                                timer.cancel();
                                timer = new Timer(true);
                                timerTask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        try {

                                            advertsRecyclerView.smoothScrollToPosition(((LinearLayoutManager) advertsRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() + 1);

                                        } catch (Exception e) {
                                            // rec.setVisibility(View.GONE);
                                        }
                                    }
                                };
                                try {
                                    timer.schedule(timerTask, wait, timeDelayed);
                                } catch (IllegalStateException e) {
                                    timer.cancel();
                                }
                                return false;
                            }

                            @Override
                            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                            }

                            @Override
                            public void onRequestDisallowInterceptTouchEvent(boolean b) {
                            }
                        });
                    }

                    public void setDelayTime(Long duration) {
                        timeDelayed = duration;
                    }

                    public Long getDelayedTime() {

                        if (timeDelayed != null) {

                            return timeDelayed;

                        } else {

                            return 0L;

                        }
                    }
                });

    }

    public void setTouchwait(Long wait) {
        this.wait = wait;
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeTimer();
    }

    private void resumeTimer() {
        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {

                    advertsRecyclerView.smoothScrollToPosition(((LinearLayoutManager) advertsRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() + 1);

                } catch (Exception e) {
                    // rec.setVisibility(View.GONE);
                }
            }
        };
        try {
            timer.schedule(timerTask, wait, timeDelayed);
        } catch (IllegalStateException e) {
            timer.cancel();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerTask.cancel();
        timer.cancel();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.uno:
                    loadFragment(new UnoFragment());
                    return true;
                case R.id.dos:
                    loadFragment(new DosFragment());
                    return true;
//                case R.id.tres:
//                    loadFragment(new TresFragment());
//                    return true;
//                case R.id.cautro:
//                    loadFragment(new CautroFragment());
//                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
