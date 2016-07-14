package com.hwangjr.rxbus.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Activity to show the story.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Mam to birth Tom.
     */
    private CatMam catMam = new CatMam();
    /**
     * Mam to birth mouse.
     */
    private MouseMam mouseMam = new MouseMam();

    private TextView main;

    /**
     * Init view and bus provider.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = (TextView) findViewById(R.id.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Mouse mouse = mouseMam.birth();
//                Mouse mouse = new WhiteMouse();
////                mouse.squeak();
//                BusProvider.getInstance().post(Constants.EventType.TAG_STORY, mouse);
                Snackbar.make(view, "Birth a mouse and squeak ! ", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
//                Timber.e("Haha, i am " + mouse);
                startAct();
            }
        });

        BusProvider.getInstance().register(this);

//        BusProvider.getInstance().register(mouseMam);
//        BusProvider.getInstance().register(catMam.birth());
    }

    void startAct() {
        startActivity(new Intent(this, AActivity.class));
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag(Constants.EventType.TAG_NAME)}
    )
    public void getPerson(Person person) {
        Timber.e("main person: " + person.toString() + " On " + Thread.currentThread());
        main.setText(person.getName());
    }

    /**
     * Unregister the register object.
     */
    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(mouseMam);
        ArrayList<Cat> cats = catMam.getCats();
        for (Cat cat : cats) {
            BusProvider.getInstance().unregister(cat);
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
