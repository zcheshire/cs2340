package com.gitrekt.water;

import com.gitrekt.water.controller.LoginActivity;
import com.gitrekt.water.controller.RegisterActivity;
<<<<<<< HEAD
import android.database.sqlite.SQLiteDatabase;
import com.gitrekt.water.model.UserReaderContract;
import com.gitrekt.water.model.UserReaderDbHelper;

import org.junit.Test;
=======

>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
import static org.junit.Assert.*;

import com.gitrekt.water.controller.SettingsActivity;
import com.gitrekt.water.model.User;

<<<<<<< HEAD
import android.view.View;
import org.junit.After;
import org.junit.Before;
=======
>>>>>>> 2aa37df7640bed18e7c9d8a1d44653d4ce49edc5
import org.junit.Test;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("unchecked")
public class ExampleUnitTest {
    private static final int TIMEOUT = 200;
    private final RegisterActivity<String> register = new RegisterActivity<>();
    private final LoginActivity<User> login = new LoginActivity();
    private final SettingsActivity<User> settings = new SettingsActivity();



//Sammi hudock Junit Test
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
        assertEquals(true, register.checkUser(arr, user));


    }
    //Zachary Cheshire Junit Test
    @Test
    public void login() throws Exception {
        ArrayList<User> userArr = new ArrayList<>();
        User user2 = new User("zac", "123");
        userArr.clear();
        userArr.add(new User("zac", "13"));
        userArr.add(new User("jeff", "124"));
        userArr.add(new User("sammi", "125"));
        userArr.add(new User("doe", "126"));
        userArr.add(new User("jerod", "127"));
        userArr.add(new User("zac", "127"));


        //assertEquals(true, user.equals(arr.get(0)));

        assertEquals(true, login.validateLogin(userArr, user2));
    }
    //JerodRay Junity Test
    @Test
    public void checkExisting() throws Exception {
        ArrayList<User> userArr = new ArrayList<>();
        User user2 = new User("zac", "123");
        userArr.clear();
        userArr.add(new User("zac", "13"));
        userArr.add(new User("jeff", "124"));
        userArr.add(new User("sammi", "125"));
        userArr.add(new User("doe", "126"));
        userArr.add(new User("jerod", "127"));
        userArr.add(new User("ac", "127"));


        //assertEquals(true, user.equals(arr.get(0)));

        assertEquals(true, settings.changeName(user2.getUserName(), user2, userArr));
    }

}