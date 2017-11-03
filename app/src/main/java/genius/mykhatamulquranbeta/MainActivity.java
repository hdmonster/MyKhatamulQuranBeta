package genius.mykhatamulquranbeta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import genius.mykhatamulquranbeta.data.ApplicationConstants;
import genius.mykhatamulquranbeta.util.BookmarksManager;
import genius.mykhatamulquranbeta.util.QuranGalleryAdapter;
import genius.mykhatamulquranbeta.util.QuranSettings;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.R.attr.width;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
    public class MainActivity extends AppCompatActivity {

    protected GestureDetector gestureDetector;
    protected static final int BOOKMARK_SAFE_REGION = 15;

    protected Gallery gallery = null;
    protected ImageView btnBookmark = null;
    protected QuranGalleryAdapter galleryAdapter = null;
    protected boolean inReadingMode = false;
    protected SharedPreferences prefs;
    Context ctx;


    ViewPager viewPager;
    CustomSwipeAdpter adpter;

    Button buttonStart, buttonStop;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "RecordAnda";
    public static final int RequestPermissionCode = 1;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI contz rols to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        BookmarksManager.load(prefs);
        ctx = MainActivity.this;

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_page);
        adpter = new CustomSwipeAdpter(this);
        viewPager.setAdapter(adpter);
        viewPager.setCurrentItem(adpter.getCount() - 1);

        int page = loadPageState(savedInstanceState);
        renderPage(ApplicationConstants.PAGES_LAST - page);

        toggleMode();

        gestureDetector = new GestureDetector(new QuranGestureDetector());

        //toggleMode();

       /* book = (Button) findViewById(R.id.btnBook);
        book.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Toast.makeText(MainActivity.this, "Sorry, this feature is inbuild", Toast.LENGTH_LONG).show();

            }*/
           /* protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put(BookmarksActivity.page);
                return params;
            }*/


        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.btnRec).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.btnStop).setOnTouchListener(mDelayHideTouchListener);

        buttonStart = (Button) findViewById(R.id.btnRec);
        buttonStop = (Button) findViewById(R.id.btnStop);

        buttonStop.setEnabled(false);

        random = new Random();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    CreateRandomAudioFileName(5) + "Record.mp3";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        //TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);

                    Toast.makeText(MainActivity.this, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);


                Toast.makeText(MainActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public String CreateRandomAudioFileName(int string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int i = 0;
        while (i < string) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++;
        }
        return stringBuilder.toString();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Permission", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        galleryAdapter.emptyCache();
    }

    protected void initGalleryAdapter() {

    }

    protected void initComponents() {
        initGalleryAdapter();


        gallery = (Gallery) findViewById(R.id.view_page);
        gallery.setAdapter(galleryAdapter);
        gallery.setAnimationDuration(0);
        gallery.setSpacing(25);


        ImageView btnBookmark = (ImageView) findViewById(R.id.btnBookmark);
        btnBookmark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BookmarksManager.toggleBookmarkState(QuranSettings.getInstance().getLastPage(), prefs);
                adjustBookmarkView();
            }
        });
    }

    protected void goToNextPage() {
        int position = gallery.getSelectedItemPosition();
        if (position > 0)
            renderPage(position - 1);
    }

    protected void goToPreviousPage() {
        int position = gallery.getSelectedItemPosition();
        if (position < ApplicationConstants.PAGES_LAST - 1)
            renderPage(position + 1);
    }

    protected void renderPage(int position) {
        gallery.setSelection(position, true);
        adjustBookmarkView();
    }

    protected void adjustBookmarkView() {
        if (BookmarksManager.getInstance().contains(QuranSettings.getInstance().getLastPage())) {
            btnBookmark.setImageResource(R.drawable.bookmark);
        } else {
            btnBookmark.setImageResource(R.drawable.remove_bookmark);
        }
    }


    public class QuranGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return handleDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return handleSingleTap(e);
        }
    }


    protected int loadPageState(Bundle savedInstanceState) {
        int page = savedInstanceState != null ? savedInstanceState.getInt("page") : ApplicationConstants.PAGES_FIRST;
        if (page == ApplicationConstants.PAGES_FIRST) {
            Bundle extras = getIntent().getExtras();
            page = extras != null ? extras.getInt("page") : QuranSettings.getInstance().getLastPage();
        } else if (page == ApplicationConstants.NO_PAGE_SAVED) {
            page = ApplicationConstants.PAGES_FIRST;
        }

        return page;
    }

    public boolean handleSingleTap(MotionEvent e) {
        return false;
    }

    public boolean handleDoubleTap(MotionEvent e) {
        int sliceWidth = (int) (0.2 * width);

        // Skip bookmark region
        int bookmarkRegionY = btnBookmark.getTop() + btnBookmark.getHeight() + BOOKMARK_SAFE_REGION;
        int bookmarkRegionX = btnBookmark.getLeft() + btnBookmark.getWidth() + BOOKMARK_SAFE_REGION;
        if (e.getY() < bookmarkRegionY && e.getX() < bookmarkRegionX)
            return true;

        if (e.getX() < sliceWidth)
            goToNextPage();
        else if (e.getX() > (width - sliceWidth))
            goToPreviousPage();
        else toggleMode();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        QuranSettings.load(prefs);
    }

    @Override
    protected void onPause() {
        super.onPause();
        QuranSettings.save(prefs);
    }


    protected void toggleMode() {
        if (inReadingMode) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            //toolbar.setVisibility(View.VISIBLE);
            //btnLockOrientation.setVisibility(View.VISIBLE);
            btnBookmark.setVisibility(View.VISIBLE);
            //adjustLockView();
            adjustBookmarkView();
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

            //toolbar.setVisibility(View.INVISIBLE);
            //btnLockOrientation.setVisibility(View.INVISIBLE);
            btnBookmark.setVisibility(View.INVISIBLE);
        }
    }
}

