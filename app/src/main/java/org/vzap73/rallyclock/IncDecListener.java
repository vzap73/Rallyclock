package org.vzap73.rallyclock;

import android.view.View;
import android.widget.EditText;

/**
 * Created by vzaporozhets on 05.11.2015.
 */
public class IncDecListener implements View.OnClickListener {
    private EditText text;
    private int step;

    IncDecListener(EditText text, int step) {
        this.text = text;
        this.step = step;
    }
    @Override
    public void onClick(View v) {
        text.setText(Integer.toString(Integer.valueOf(text.getText().toString()) + step));
    }
}
