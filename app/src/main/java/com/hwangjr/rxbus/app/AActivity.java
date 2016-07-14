package com.hwangjr.rxbus.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import timber.log.Timber;

/**
 * Created by wangyd on 16/7/7.
 */
public class AActivity extends Activity {
    TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_layout);
        a = (TextView) findViewById(R.id.a_text);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct();
            }
        });
        BusProvider.getInstance().register(this);

    }

    void startAct() {
        startActivity(new Intent(this, BActivity.class));
    }


    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag(Constants.EventType.TAG_NAME)}
    )
    public void getPerson(Person person) {
        Timber.e("person: " + person.toString() + " On " + Thread.currentThread());
        a.setText(person.getName());
    }
}
