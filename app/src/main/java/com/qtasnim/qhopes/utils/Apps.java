package com.qtasnim.qhopes.utils;


/*
 * ******************************************************
 *  * Copyright (c) 2019. All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Created by: Yogi Dewansyah<yodeput@gmail.com>
 *  ******************************************************
 */

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;



public class Apps extends Application {

    public static final String TAG = Apps.class.getSimpleName();
    private static Apps mInstance;

    public static synchronized Apps getInstance() {
        return mInstance;
    }

    private FirebaseAnalytics mFirebaseAnalytics;

    public static void hideSoftKeyboard(Activity pActivity) {
        if (pActivity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager)
                pActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View viewWithCurrentFocus = pActivity.getCurrentFocus();
        if (inputMethodManager != null && viewWithCurrentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    viewWithCurrentFocus.getWindowToken(), 0);
        }
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    public static String getDayName(String day) {
        switch (day) {
            case "Sunday":
                return "Minggu";
            case "Monday":
                return "Senin";
            case "Tuesday":
                return "Selasa";
            case "Wednesday":
                return "Rabu";
            case "Thursday":
                return "Kamis";
            case "Friday":
                return "Jumat";
            case "Saturday":
                return "Sabtu";
        }

        return "Worng Day";
    }

    public static String getMonthName(String month) {
        switch (month) {
            case "January":
                return "Januari";
            case "February ":
                return "Februari";
            case "March":
                return "Maret";
            case "April":
                return "April";
            case "May":
                return "Mei";
            case "June":
                return "Juli";
            case "July":
                return "Juli";
            case "August":
                return "Agustus";
            case "September":
                return "September";
            case "October":
                return "Oktober";
            case "November":
                return "Nopember";
            case "December":
                return "Desember";
        }

        return "Worng Month Code";
    }

    public static String getMonthNameByInt(int month) {
        switch (month) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Maret";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Juli";
            case 7:
                return "Juli";
            case 8:
                return "Agustus";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "Nopember";
            case 12:
                return "Desember";
        }

        return "Worng Month Code";
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i(TAG, "***** IP=" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mInstance = this;

    }

    public static String getCurrentDay() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        return dayFormat.format(calendar.getTime());

    }

    public void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.GRAY); // optional
        }
    }

    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    public String getDateTime() {
        Date date = new Date();
        DateFormat hari = new SimpleDateFormat("EEEE", Locale.US);
        String dayName = getDayName(hari.format(date));
        DateFormat tanggal = new SimpleDateFormat("dd", Locale.US);
        String dayDate = tanggal.format(date);
        DateFormat bulan = new SimpleDateFormat("MMMM", Locale.US);
        String monthName = getMonthName(bulan.format(date));
        DateFormat tahun = new SimpleDateFormat("yyyy", Locale.US);
        String year = tahun.format(date);

        String now = dayName + ", <b>" + dayDate + " " + monthName + " " + year + "</b>";
        return now;
    }

    public String getMonth() {
        Date date = new Date();
        DateFormat dateFormat1 = new SimpleDateFormat("MMMM", Locale.US);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy", Locale.US);

        String month = getMonthName(dateFormat1.format(date));
        String dateNow = dateFormat2.format(date);
        dateNow = "<b>" + dateNow + "</b>";
        dateNow = month + ", " + dateNow;
        return dateNow;
    }

    public String getDateTime2() {
        Date date = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.US);

        String dateNow = dateFormat2.format(date);
        return dateNow;
    }

    public void clearPref() {
        SharedPreferences preferences = getSharedPreferences("sibangunMobile_pref", 0);
        preferences.edit().clear().apply();
    }

    public void saveSS(View v, String file_name) {
        try {

            File newfile1 = new File(Environment.getExternalStorageDirectory() + "/Pictures/");
            newfile1.mkdir();
            File newfile = new File(Environment.getExternalStorageDirectory() + "/Pictures/siDEVI/");
            newfile.mkdir();
            String mPath = newfile.toString() + "/" + file_name + ".jpeg";

            // create bitmap screen capture
            v.setDrawingCacheEnabled(true);
            //Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
            Bitmap bitmap1 = loadBitmapFromView(v, v.getWidth(), v.getHeight());
            v.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap1.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    public String takeSS(View v, String file_name) {
        Date now = new Date();
        android.text.format.DateFormat.format("hh:mm:ss", now);
        String a = "";
        try {

            File newfile = new File(Environment.getExternalStorageDirectory() + "/Pictures/siDEVI/");
            newfile.mkdir();
            String mPath = newfile.toString() + "/" + file_name + ".jpeg";

            // create bitmap screen capture
            v.setDrawingCacheEnabled(true);
            //Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
            Bitmap bitmap1 = loadBitmapFromView(v, v.getWidth(), v.getHeight());

            v.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap1.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //setting screenshot in imageview
            String filePath = imageFile.getPath();

            Bitmap ssbitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            a = filePath;
            return a;
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
        return a;
    }

    public String device_id() {
        return Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


    public String appVersion() {
        String version = "";
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            int verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public boolean isEmpty(AppCompatEditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public String JpgToString(String path) {
        String base64 = "";
        Bitmap bmp = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bos);

        InputStream inputStream = new ByteArrayInputStream(bos.toByteArray());
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        base64 = Base64.encodeToString(bytes, Base64.DEFAULT);

        return base64;
    }

    public String getType(String src) {
        switch (src) {
            case "Kunjungan":
                return "1";
            case "Event":
                return "3";
            case "Pelatihan":
                return "2";
            case "Administrasi":
                return "4";
        }
        return "1";
    }

    public String urlFixer(String url) {
        String temp = url;
        if (url.contains("\\")) {
            temp = url.replace("\\", "");
        }
        return temp;
    }

    public String greetings() {
        String greetings = "";
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        if (hours >= 1 && hours <= 12) {
            greetings = "Selamat Pagi";
        } else if (hours >= 11 && hours <= 14) {
            greetings = "Selamat Siang";
        } else if (hours >= 14 && hours <= 18) {
            greetings = "Selamat Sore";
        } else if (hours >= 18 && hours <= 24) {
            greetings = "Selamat Malam";
        }
        return greetings;
    }


    public String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(tomorrow);
    }

    public String getBesokDate() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date day = new Date();
        String datetime = dateFormat.format(tomorrow);
        String dayName = getDayName(sdf_.format(day));

        return dayName+", "+datetime;
    }

}
