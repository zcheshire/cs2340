package com.gitrekt.water;

import android.content.Context;

import com.gitrekt.water.controller.LoginActivity;
import com.gitrekt.water.controller.RegisterActivity;
import org.junit.Test;
import static org.junit.Assert.*;
import com.gitrekt.water.model.User;
import android.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final int TIMEOUT = 200;
    private RegisterActivity<String> register = new RegisterActivity<String>();
    @Test
    public void checkUserr() throws Exception {
        ArrayList<String> arr = new ArrayList<>();
                String user = "zac";
        arr.clear();
        arr.add("zacc");
        arr.add("jeff");
        arr.add("sammi");
        arr.add("jerod");
        arr.add("gyro");
        //assertEquals(true, user.equals(arr.get(0)));
        assertEquals(true, register.checkUser(arr, user));

    }
}