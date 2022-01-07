package com.mycompanynew.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okio.ByteString;


public class Tools {

    public final static Pattern INDIAN_MOBILE_NUMBER = Pattern.compile("^[6-9]\\d{9}$");
    private static final String IMAGEPATHTOSAVE = "/download";
    private static final float maxHeight = 1280.0f;
    private static final float maxWidth = 1280.0f;
    private Context context;
    private Dialog dialog;
    PreferenceManager preferenceManager;

    public Tools() {
    }

    public Tools(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        preferenceManager = new PreferenceManager(context);
    }

    public static boolean checkMobileNumber(String value) {
       /*Pattern ps = Pattern.compile("^[1-9][0-9]{9}$");
        Matcher ms = ps.matcher(value);
        boolean bs = ms.matches();
        if (bs) {
            return true;
        }
        return bs;*/
        return value != null && value.length() > 7 && value.length() < 16;
    }




    public static int dpToPx(Context c, int dp) {
        if (c != null) {
            Resources r = c.getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        } else {
            return 0;
        }
    }

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }

    public static void toast(Context ctx, String msg, int type) {
        //type 0 info, 1 for Error ,2 for Sucess,3 for warning

        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();

        /*if (type == 0) {
            Toasty.info(ctx, msg, Toast.LENGTH_LONG, true).show();
        } else if (type == 1) {
            Toasty.error(ctx, msg, Toast.LENGTH_LONG, true).show();
        } else if (type == 2) {
            Toasty.success(ctx, msg, Toast.LENGTH_LONG, true).show();
        } else if (type == 3) {
            Toasty.warning(ctx, msg, Toast.LENGTH_LONG, true).show();
        } else {
            Toast.makeText(ctx, "" + msg, Toast.LENGTH_LONG).show();
        }*/
    }

    public static String capFirstLetter(String str) {
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        return cap;
    }

    public static String toCamelCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {

                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;

                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public static String capitalize(String capString) {

        if (capString == null || capString.length() < 1) {
            return "";
        }

        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    public static String getFileNameFromURL(String url) {
        if (url == null) {
            return "";
        }
        try {
            URL resource = new URL(url);
            String host = resource.getHost();
            if (host.length() > 0 && url.endsWith(host)) {
                // handle ...example.com
                return "";
            }
        } catch (MalformedURLException e) {
            return "";
        }

        int startIndex = url.lastIndexOf('/') + 1;
        int length = url.length();

        // find end index for ?
        int lastQMPos = url.lastIndexOf('?');
        if (lastQMPos == -1) {
            lastQMPos = length;
        }

        // find end index for #
        int lastHashPos = url.lastIndexOf('#');
        if (lastHashPos == -1) {
            lastHashPos = length;
        }

        // calculate the end index
        int endIndex = Math.min(lastQMPos, lastHashPos);
        return url.substring(startIndex, endIndex);
    }

    public static Bitmap handleSamplingAndRotationBitmap(Context context, Uri selectedImage)
            throws IOException {
        int MAX_HEIGHT = 1024;
        int MAX_WIDTH = 1024;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream imageStream = context.getContentResolver().openInputStream(selectedImage);
        BitmapFactory.decodeStream(imageStream, null, options);
        assert imageStream != null;
        imageStream.close();

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        imageStream = context.getContentResolver().openInputStream(selectedImage);
        Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

        img = rotateImageIfRequired(context, img, selectedImage);
        return img;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    public static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) throws IOException {

        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23) {
            assert input != null;
            ei = new ExifInterface(input);
        } else
            ei = new ExifInterface(Objects.requireNonNull(selectedImage.getPath()));

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static String getIds(HashMap<String, String> hashMap) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = hashMap.keySet();
        for (String key : keys) {
            sb.append(key + ",");
        }

        return removeLastCharacter(sb.toString());

    }
    public static String getIdsFromString(ArrayList<String> ids) {
        StringBuilder sb = new StringBuilder();
        for (String key : ids) {
            sb.append(key + ",");
        }

        return removeLastCharacter(sb.toString());

    }
    public static String getIdsInteger(HashMap<Integer, String> hashMap) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> keys = hashMap.keySet();
        for (Integer key : keys) {
            sb.append(key + ",");
        }

        return removeLastCharacter(sb.toString());
    }

    public static String removeLastCharacter(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    @SuppressLint("HardwareIds")
    private static String getDeviceID(@NonNull Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    @NonNull
    private static String getPrecision(double d) {
        try {
            return String.format(Locale.ENGLISH, "%.2f", Double.valueOf(d));
        } catch (Exception unused) {
            return "0.00";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String addDayInCurrentDate(String dateNew, int days) {
        SimpleDateFormat sdf = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dateNew));
            } catch (Exception e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, days);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            return sdf1.format(c.getTime());
        } else {
            return dateNew;
        }
    }

    public static String getReverseDate(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = "-";
        String[] split = str.split(str2);
        if (split.length <= 2) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(split[2]);
        sb.append(str2);
        sb.append(split[1]);
        sb.append(str2);
        sb.append(split[0]);
        return sb.toString();
    }

    public static Date convertStringDateToDate(String str) {
        DateFormat format = null;
        Date date = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    public static long getRoundingOffDownValue(double d, long j) {
        double d2 = (double) j;
        Double.isNaN(d2);
        double d3 = d % d2;
        return d3 > 0.0d ? Math.round(d - d3) : Math.round(d);
    }

    public static float getRoundingOffValue(double d, float f) {
        double d2 = f;
        Double.isNaN(d2);
        double d3 = d % d2;
        if (d3 > 0.0d) {
            Double.isNaN(d2);
            d += d2 - d3;
        }
        return (float) Math.round(d);
    }

    public static float getRoundingOffValue(float f, float f2) {
        double d = f % f2;
        if (d <= 0.0d) {
            return (float) Math.round(f);
        }
        double d2 = f;
        double d3 = f2;
        Double.isNaN(d3);
        Double.isNaN(d);
        double d4 = d3 - d;
        Double.isNaN(d2);
        return (float) Math.round(d2 + d4);
    }

    public static long getRoundingOffValue(double d, int i) {
        double d2 = i;
        Double.isNaN(d2);
        double d3 = d % d2;
        if (d3 <= 0.0d) {
            return Math.round(d);
        }
        Double.isNaN(d2);
        return Math.round(d + (d2 - d3));
    }

    public static long getRoundingOffValue(double d, long j) {
        double d2 = (double) j;
        Double.isNaN(d2);
        double d3 = d % d2;
        if (d3 <= 0.0d) {
            return Math.round(d);
        }
        Double.isNaN(d2);
        return Math.round(d + (d2 - d3));
    }

    public static String getSimplifiedUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String replace = str.replace("\\", "/");
        String str2 = " ";
        return replace.contains(str2) ? replace.replace(str2, "%20") : replace;
    }

    public static void hideSoftKeyboard(@NonNull Activity activity) {
        @SuppressLint("WrongConstant")
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (activity.getCurrentFocus() != null && inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(@NonNull Activity activity, View view) {
        @SuppressLint("WrongConstant")
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (activity.getCurrentFocus() != null && inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    public static void hideSoftKeyboard(@NonNull Activity activity, View view) {
        if (view != null) {
            @SuppressLint("WrongConstant") InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            if (activity.getCurrentFocus() != null && inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean isValidGSTNumber(@NonNull String str) {
        if (str.length() != 15) {
            return false;
        }
        return Pattern.compile("[0-9]{2}[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}[A-Za-z1-9]{1}Z{1}[0-9A-Za-z]{1}").matcher(str).matches();
    }

    public static boolean isValidLatLng(double d, double d2) {
        return d >= -90.0d && d <= 90.0d && d2 >= -180.0d && d2 <= 180.0d;
    }

    public static boolean isValidPanNumber(@NonNull String str) {
        return Pattern.compile("[A-Z,a-z]{5}[0-9]{4}[A-Z,a-z]").matcher(str).matches();
    }

    public static boolean isValidUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            return Patterns.WEB_URL.matcher(str.toLowerCase()).matches();
        }
        return false;
    }

    public static boolean isValidEmail(String str) {
        if (!TextUtils.isEmpty(str)) {
            return Patterns.EMAIL_ADDRESS.matcher(str.toLowerCase()).matches();
        }
        return false;
    }

    private static String getLastCharacterFromString(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() == i) {
            return str;
        }
        if (str.length() > i) {
            str = str.substring(str.length() - i);
        }
        return str;
    }

    public static String mobileNoValidation(@NonNull String str) {
        String str2 = "";
        return !TextUtils.isEmpty(str) ? getLastCharacterFromString(str.replaceAll("\\s+", str2).replace("-", str2).replace("(", str2).replace(")", str2).replace("+", str2).replaceAll("[^\\d.]", str2).replaceFirst("^0*", str2), 10) : str2;
    }


    public static String basic(String username, String password, Charset charset) {
        String usernameAndPassword = username + ":" + password;
        String encoded = ByteString.encodeString(usernameAndPassword, charset).base64();
        return "Basic " + encoded;
    }

    public static boolean vpn(Context context) {
        String iface = "";
        Boolean Value = false;
        try {
            PreferenceManager preferenceManager = new PreferenceManager(context);

            if (preferenceManager.getVPNCheck()) {
                Log.e("##", "VPN if");
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (networkInterface.isUp())
                        iface = networkInterface.getName();
                    Log.d("DEBUG", "IFACE NAME: " + iface);
                    if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                        Value = true;
                    }
                }
            } else {
                Log.e("##", "VPN else");
                Value = false;
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
            Log.e("##", "VPN Eroro" + e1.getLocalizedMessage());
        }

        return Value;
    }

    public static boolean hostAvailable(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 2000);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean isPackageInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        if (intent == null) {
            return false;
        }
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public static boolean isCameraPermissionGranted(Context ctx) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("##", "Permission is granted1");
                return true;
            } else {

                Log.v("##", "Permission is revoked1");
                ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.CAMERA}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("##", "Permission is granted1");
            return true;
        }
    }

    public static boolean isReadStoragePermissionGranted(Context ctx) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("##", "Permission is granted1");
                return true;
            } else {

                Log.v("##", "Permission is revoked1");
                ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("##", "Permission is granted1");
            return true;
        }
    }

    public static boolean isWriteStoragePermissionGranted(Context ctx) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("##", "Permission is granted2");
                return true;
            } else {

                Log.v("##", "Permission is revoked2");
                ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("##", "Permission is granted2");
            return true;
        }
    }


    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDate(String strDate) {

        try {
            SimpleDateFormat fmtOut = null;
            SimpleDateFormat fmt = null;
            Date date;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fmt = new SimpleDateFormat("yyyy-MM-dd");
                date = fmt.parse(strDate);

                fmtOut = new SimpleDateFormat("dd-MM-yyyy");
                return fmtOut.format(date);
            } else {
                return strDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDateYMD(String strDate) {

        if (strDate != null) {
            try {
                SimpleDateFormat fmtOut = null;
                SimpleDateFormat fmt = null;
                Date date;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fmt = new SimpleDateFormat("dd-MM-yyyy");
                    date = fmt.parse(strDate);

                    fmtOut = new SimpleDateFormat("yyyy-MM-dd");
                    return fmtOut.format(date);
                } else {
                    return strDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";

    }

    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDateYMD(String strDate, String inputPattern, String outputPattern) {

        if (strDate != null) {
            try {
                SimpleDateFormat fmtOut = null;
                SimpleDateFormat fmt = null;
                Date date;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fmt = new SimpleDateFormat(inputPattern);
                    date = fmt.parse(strDate);

                    fmtOut = new SimpleDateFormat(outputPattern);
                    return fmtOut.format(date);
                } else {
                    return strDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";

    }

    public static String getOnlyStrings(String s) {
        try {
            Pattern pattern = Pattern.compile("[^a-z A-Z]");
            Matcher matcher = pattern.matcher(s);
            String number = matcher.replaceAll("");
            return number;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    public static String getOnlyDigits(String s) {
        if (s != null) {
            Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[+])");
            Matcher matcher = pattern.matcher(s.trim());
            String number = matcher.replaceAll("");
            number = number.replaceAll(" ", "");
            number = number.replaceAll("-", "");
            return number;
        } else {
            return "";
        }
    }

    public static boolean isStringContainsDigits(String str, int n) {
        for (int i = 0; i < n; i++) {
            return str.charAt(i) >= '0'
                    && str.charAt(i) <= '9';
        }
        return false;
    }

    public static boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    public static void openWhatsAppConversationUsingUri(Context context, String numberWithCountryCode, String message) {

        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + message);

        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);

        context.startActivity(sendIntent);
    }

    public static Object getGSonData(Object obj) {
        return new Gson().toJson(obj);
    }

    public static String getFormattedAmount(String amount) {
        return amount;
        /*try {
            NumberFormat myFormat = NumberFormat.getInstance();
            myFormat.setGroupingUsed(true);
            return myFormat.format(amount);
        } catch (Exception e) {
            e.printStackTrace();
            return amount;
        }*/
    }

    public static String getDefaultPath(Context ctx) {
        StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder2.build());

        File file = new File(ctx.getExternalFilesDir(null) + "/Fincasys/Files/");
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + "/";
    }

    public static String getDefaultPathLanguage(Context ctx) {
        StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder2.build());

        File mediaStorageDir = new File(ctx.getExternalFilesDir(null)
                + "/Android/data/"
                + ctx.getApplicationContext().getPackageName()
                + "/Files/language/");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }

        return mediaStorageDir.getAbsolutePath();
    }

    public static File getOutputMediaFile(Context ctx) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder2.build());

        File file = new File(ctx.getExternalFilesDir(null) + "/Fincasys/Files/");
        if (!file.exists()) {
            file.mkdirs();
        }

        File mediaStorageDir = new File(ctx.getExternalFilesDir(null)
                + "/Android/data/"
                + ctx.getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        // Create a media file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new java.text.SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        File mediaFile;
        String mImageName = "IMAGE_" + timeStamp + ".jpg";
        mediaFile = new File(file.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static File getOutputMediaVideoFile(Context ctx) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder2.build());

        File file = new File(ctx.getExternalFilesDir(null) + "/Fincasys/Files/");
        if (!file.exists()) {
            file.mkdirs();
        }


        // Create a media file name
        String timeStamp = new java.text.SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        File mediaFile;
        String mImageName = "VIDEO_" + timeStamp + ".mp4";
        mediaFile = new File(file.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static int calculateInSampleSizeNew(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = Math.min(heightRatio, widthRatio);
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public static String getFilename(Context ctx) {
        File mediaStorageDir = new File(ctx.getExternalFilesDir(null)
                + "/Android/data/"
                + ctx.getApplicationContext().getPackageName()
                + "/Files/Compressed");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

        String mImageName = "IMG_" + System.currentTimeMillis() + ".jpg";
        String uriString = (mediaStorageDir.getAbsolutePath() + "/" + mImageName);
        return uriString;
    }

    @SuppressLint("SimpleDateFormat")
    public static String timeConversion12to24(String twelveHoursTime) {

        try {
            DateFormat df = null;
            DateFormat outputformat = null;
            Date date = null;
            String output = twelveHoursTime;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                df = new SimpleDateFormat("hh:mm aa");
                outputformat = new SimpleDateFormat("HH:mm:ss");

                //Returns Date object
                date = df.parse(twelveHoursTime);

                //old date format to new date format
                output = outputformat.format(date);
            }
            return output;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMimeType(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }

    public static boolean isImageFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("image");
    }

    public static boolean isVideoFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("video");
    }

    public static int RoundFloatValue(float f) {
        int c = (int) ((f) + 0.5f);
        float n = f + 0.5f;
        return (n - c) % 2 == 0 ? (int) f : c;
    }

    public static long getTimeDiff(String time1, String time2) {
        long difference = 0;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat format = new SimpleDateFormat("h:mm a");
                Date date1 = null, date2 = null;
                date1 = format.parse(time1);
                date2 = format.parse(time2);
                difference = date2.getTime() - date1.getTime();
                difference = difference / 1000;

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return difference;
    }

    public static String getUrlExtenssion(String serverPath) {
        if (!serverPath.contains(".")) {
            return null;
        }
        return serverPath.substring(serverPath.lastIndexOf("."));
    }

    public static String getMimeTypeExtenssion(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static String getDuration(File file) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
        String durationStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        return Tools.formateMilliSeccond(Long.parseLong(durationStr));
    }

    public static String formateMilliSeccond(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        //      return  String.format("%02d Min, %02d Sec",
        //                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
        //                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
        //                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));

        // return timer string
        return finalTimerString;
    }


    public static boolean getMicrophoneAvailable(Context context) {
        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(new File(context.getCacheDir(), "MediaUtil#micAvailTestFile").getAbsolutePath());
        boolean available = true;
        try {
            recorder.prepare();
            recorder.start();

        } catch (Exception exception) {
            available = false;
        }
        recorder.release();
        return available;
    }

    public static boolean CompareTime(String one, String two) {

        boolean answer = false;
        String a = timeConversion12to24(one);
        String b = timeConversion12to24(two);

        SimpleDateFormat formatter5 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatter5 = new SimpleDateFormat("HH:mm");
            try {
                Date date1 = formatter5.parse(a);
                Date date2 = formatter5.parse(b);

                if (date1.after(date2)) {
                    answer = true;
                } else {
                    answer = false;
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return answer;
    }

    public static void callDialer(Context context, String number) {
        if (number != null && number.length() > 2) {
            try {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                context.startActivity(dialIntent);
            } catch (Exception e) {
                e.printStackTrace();
                Tools.toast(context, "Calling not supported.", VariableBag.WARNING);
            }

        }
    }


    public static void EmailSender(Context context, String email) {

        if (email != null && email.contains("@")) {
            try {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, " ");
                intent.setData(Uri.parse("mailto:" + email)); // or just "mailto:" for blank
                context.startActivity(Intent.createChooser(intent, "Send Email"));

            } catch (Exception e) {
                e.printStackTrace();
                Tools.toast(context, "Email not supported.", VariableBag.WARNING);
            }

        }
    }

    public static String getCurrentDateTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentDateTimeLong() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentDateTime(String strFormet) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(strFormet, Locale.getDefault());
        return sdf.format(new Date());
    }

    public static boolean isNumeric(String str) {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != localeMinusSign) return false;

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

        for (char c : str.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == localeDecimalSeparator && !isDecimalSeparatorFound) {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static String[] getStringArray(JSONArray jsonArray) {
        String[] stringArray = null;
        if (jsonArray != null) {
            int length = jsonArray.length();
            stringArray = new String[length];
            for (int i = 0; i < length; i++) {
                stringArray[i] = jsonArray.optString(i);
            }
        }
        return stringArray;
    }

    public static String[] getAttendanceMonth(JSONArray jsonArray) {
        String[] stringArray = null;
        if (jsonArray != null) {
            int length = jsonArray.length();
            stringArray = new String[length - 1];
            for (int i = 1; i < length; i++) {
                stringArray[i - 1] = jsonArray.optString(i);
            }
        }
        return stringArray;
    }

    public static void setSpinnerValue(Context context, Spinner spinner, String[] spinnerArray) {
        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    public static void setSpinnerValue(Context context, AppCompatSpinner spinner, String[] spinnerArray) {
        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerArray);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    public static String getCurrentPassword(String societyId, String userId, String userMobile) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = sdf.format(new Date());
        String subMobile = getLastThreeChatFromString(userMobile);
        return userId + "@" + subMobile + "@" + societyId;
    }

    public static String getLastThreeChatFromString(String word) {
        if (word != null && word.length() == 3) {
            return word;
        } else if (word != null && word.length() > 3) {
            return word.substring(word.length() - 3);
        } else {
            // whatever is appropriate in this case
            return "";
        }
    }

    public static String generateRandomHexToken(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16); //hex encoding
    }

    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public void activity_anim(Activity context) {
        //context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @SuppressLint("ResourceType")
    public static void setImageViewTint(Context context, ImageView v, String color) {
        v.setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
    }

    @SuppressLint("ResourceType")
    public static void setButtonBackTint(Context context, Button v, String color) {
        v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
    }

    public String CheckNull(String data) {
        if (data == null || data.trim().equals("")) {
            return preferenceManager.getJSONKeyStringObject("no_data_avilable");
        } else {
            return data;
        }

    }

    public static String pad(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + input;
        }
    }

    public static int toMinutes(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }


    public static int getHours(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hour;
    }

    public static boolean isMockSettingsON(Context context) {
        // returns true if mock location enabled, false if not enabled.
        return !Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
    }

    public static boolean areThereMockPermissionApps(Context context) {
        int count = 0;

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages =
                pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName,
                        PackageManager.GET_PERMISSIONS);

                // Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;

                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        if (requestedPermissions[i]
                                .equals("android.permission.ACCESS_MOCK_LOCATION")
                                && !applicationInfo.packageName.equals(context.getPackageName())) {
                            count++;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("Got exception ", e.getMessage());
            }
        }

        return count > 0;
    }

    @SuppressLint("ObsoleteSdkInt")
    @android.annotation.TargetApi(17)
    public static boolean isDevMode(Context context) {
        if (Integer.valueOf(Build.VERSION.SDK) == 16) {
            return Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
        } else if (Integer.valueOf(Build.VERSION.SDK) >= 17) {
            return Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
        } else return false;
    }

}
