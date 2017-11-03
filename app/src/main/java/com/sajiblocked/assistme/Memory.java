package com.sajiblocked.assistme;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class Memory implements Serializable  {
    private String title, msg;
    private long time;

    public Memory( long time, String title, String msg) {
        this.title = title;
        this.msg = msg;
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }

    public long getTime() {
        return time;
    }

    public String getMemoryTime(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
        , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time));
    }
}
