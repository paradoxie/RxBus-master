package com.hwangjr.rxbus.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wangyd on 16/7/7.
 */
public class BActivity extends Activity {
    TextView b;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_layout);
        b = (TextView) findViewById(R.id.b_text);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person = new Person();
                person.setName("wangyd");
                BusProvider.getInstance().post(Constants.EventType.TAG_NAME, person);
                finish();
            }
        });

    }


}
