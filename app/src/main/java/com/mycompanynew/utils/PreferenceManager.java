package com.mycompanynew.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreferenceManager {

    public static PreferenceManager preferenceManager;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public PreferenceManager(Context context) {
        preferenceManager = this;
        mSharedPreferences = context.getSharedPreferences(VariableBag.PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    public static void setPreferenceManager(PreferenceManager preferenceManager) {
        PreferenceManager.preferenceManager = preferenceManager;
    }

    public static PreferenceManager getInstance() {
        return preferenceManager;
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
                    // Convert to title case and switch out of whitespace mode.
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

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }


    public SharedPreferences.Editor getmEditor() {
        return mEditor;
    }

    public void setmEditor(SharedPreferences.Editor mEditor) {
        this.mEditor = mEditor;
    }

    public void clearPreferences() {
        mEditor.clear();
        mEditor.commit();
        //deleteLoginSession();
    }

    /*set preference for registration*/

    public void removePref(Context context, String keyToRemove) {
        mSharedPreferences = context.getSharedPreferences(VariableBag.PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.remove(keyToRemove);
        mEditor.apply();
    }

    public boolean isDefaultSettingOpened() {
        return mSharedPreferences.getBoolean(VariableBag.DEFAULT_SETTINGS, false);
    }

    public void setDefaultSettingOpened(boolean value) {
        mEditor.putBoolean(VariableBag.DEFAULT_SETTINGS, value).commit();
    }


    public String getSocietyName() {
        return mSharedPreferences.getString(VariableBag.SOCIETY_NAME, "0");
    }

    public void setSocietyName(String id) {
        mEditor.putString(VariableBag.SOCIETY_NAME, id).commit();
    }

    public String getBgSplashUrl() {
        return mSharedPreferences.getString("bgUrl", "");
        //return "http://192.168.0.111/SW/fincasys/" + VariableBag.SUB_URL;
    }

    public void setBgSplashUrl(String wiFiSession) {
        mEditor.putString("bgUrl", wiFiSession).commit();
    }

    public String getBgSplashColorCode() {
        return mSharedPreferences.getString("bgColorCode", "");
        //return "http://192.168.0.111/SW/fincasys/" + VariableBag.SUB_URL;
    }

    public void setBgSplashColorCode(String wiFiSession) {
        mEditor.putString("bgColorCode", wiFiSession).commit();
    }


    public String getNoDataIcon() {
        return mSharedPreferences.getString(VariableBag.NO_DATA_ICON, "");
    }

    public void setNoDataIcon(String icon) {
        mEditor.putString(VariableBag.NO_DATA_ICON, icon).commit();
    }

    public String getSocietyLogo() {
        return mSharedPreferences.getString(VariableBag.SOCIETY_LOGO, "0");
    }

    public void setSocietyLogo(String id) {
        mEditor.putString(VariableBag.SOCIETY_LOGO, id).commit();
    }

    public String getSocietyId() {
        return mSharedPreferences.getString(VariableBag.SOCIETY_ID, "1");
    }

    public void setSocietyId(String id) {
        mEditor.putString(VariableBag.SOCIETY_ID, id).commit();
    }

    public String getShiftTimeId() {
        return mSharedPreferences.getString(VariableBag.SHIFT_TIME_ID, "0");
    }

    public void setShiftTimeId(String id) {
        mEditor.putString(VariableBag.SHIFT_TIME_ID, id).commit();
    }

    public String getAttendanceId() {
        return mSharedPreferences.getString(VariableBag.ATTENDANCE_ID, "0");
    }

    public void setAttendanceId(String id) {
        mEditor.putString(VariableBag.ATTENDANCE_ID, id).commit();
    }

    public String getBreakId() {
        return mSharedPreferences.getString(VariableBag.BREAK_ID, "");
    }

    public void setBreakId(String id) {
        mEditor.putString(VariableBag.BREAK_ID, id).commit();
    }

    public String getBlockId() {
        return mSharedPreferences.getString(VariableBag.BLOCK_ID, "0");
    }

    public void setBlockId(String id) {
        mEditor.putString(VariableBag.BLOCK_ID, id).commit();
    }

    public String getFloorId() {
        return mSharedPreferences.getString(VariableBag.FLOOR_ID, "0");
    }

    public void setFloorId(String id) {
        mEditor.putString(VariableBag.FLOOR_ID, id).commit();
    }

    public String getBlockUnitName() {
        return mSharedPreferences.getString(VariableBag.BLOCK_UNIT_NAME, " ");
    }

    public void setBlockUnitName(String value) {
        mEditor.putString(VariableBag.BLOCK_UNIT_NAME, value).commit();
    }


    public String getDesignationName() {
        return mSharedPreferences.getString(VariableBag.DESIGNATION_NAME, " ");
    }

    public void setDesignationName(String value) {
        mEditor.putString(VariableBag.DESIGNATION_NAME, value).commit();
    }

    public String getCompanyName() {
        return mSharedPreferences.getString(VariableBag.Company_Name, " ");
    }

    public void setCompanyName(String value) {
        mEditor.putString(VariableBag.Company_Name, value).commit();
    }


    public String getAboutSelf() {
        return mSharedPreferences.getString(VariableBag.BUSINESS_ABOUT, "");
    }

    public void setAboutSelf(String value) {
        mEditor.putString(VariableBag.BUSINESS_ABOUT, value).commit();
    }


    public String getUnitId() {
        return mSharedPreferences.getString(VariableBag.UNIT_ID, "0");
    }

    public void setUnitId(String id) {
        mEditor.putString(VariableBag.UNIT_ID, id).commit();
    }

    public String getRegisteredUserId() {
        return mSharedPreferences.getString(VariableBag.USER_ID, "1");
    }

    public void setRegisteredUserId(String strUserId) {
        mEditor.putString(VariableBag.USER_ID, strUserId).commit();
    }

    public String getChatUserId() {
        return mSharedPreferences.getString(VariableBag.USER__CHAT_ID, "0");
    }

    public void setChatUserId(String strUserId) {
        mEditor.putString(VariableBag.USER__CHAT_ID, strUserId).commit();
    }

    public String getBusinessCardName() {
        return mSharedPreferences.getString(VariableBag.BUSINESS_CARD_NAME, "");
    }

    public void setBusinessCardName(String strUserId) {
        mEditor.putString(VariableBag.BUSINESS_CARD_NAME, strUserId).commit();
    }

    public void deleteLoginSession() {
        mEditor.putBoolean(VariableBag.LOGIN, false);
        mEditor.commit();
    }


    public boolean getVPNCheck() {
        return mSharedPreferences.getBoolean(VariableBag.VPN_CHECK, true);
    }

    public boolean isSociety() {
        return false;
    }

    public void setVPNCheck(Boolean vpnCheck) {
        mEditor.putBoolean(VariableBag.VPN_CHECK, vpnCheck);
        mEditor.commit();
    }

    public void setLoginSession() {
        mEditor.putBoolean(VariableBag.LOGIN, true);
        mEditor.commit();
    }

    public boolean getLoginSession() {
        return mSharedPreferences.getBoolean(VariableBag.LOGIN, false);
    }


    public void setCurrentSociety(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getCurrentSociety(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public boolean getFirstSession() {
        return mSharedPreferences.getBoolean("first_start", false);
    }

    public void setFirstSession(boolean wiFiSession) {
        mEditor.putBoolean("first_start", wiFiSession).commit();
    }

    public void setApikey(String wiFiSession) {
        mEditor.putString("key", wiFiSession).commit();
    }

    public String getApiKey() {
        return mSharedPreferences.getString("key", "bmsapikey");
    }

    public String getBaseUrl() {
        return mSharedPreferences.getString("baseurl", "https://dev.my-company.app/") + VariableBag.SUB_URL;
        //return "http://192.168.0.111/SW/fincasys/" + VariableBag.SUB_URL;
    }

    public void setBaseUrl(String wiFiSession) {
        mEditor.putString("baseurl", wiFiSession).commit();
    }

    public void setKeyValueString(String key, String value) {
        mEditor.putString(key, value).commit();
    }

    public int getVersionCode() {
        return mSharedPreferences.getInt(VariableBag.VERSION_CODE, 0);
    }

    public void setVersionCode(int value) {
        mEditor.putInt(VariableBag.VERSION_CODE, value).commit();
    }

    public void setKeyValueInt(String key, int value) {
        mEditor.putInt(key, value).commit();
    }

    public void setKeyValueBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value).commit();
    }

    public boolean getFirstTime() {
        return mSharedPreferences.getBoolean("firstTime", false);
    }

    public void setFirstTime(boolean key) {
        mEditor.putBoolean("firstTime", key).commit();
    }

    public String getBackBanner() {
        return mSharedPreferences.getString("bannerBack", VariableBag.BACKIMG);
    }

    public void setBackBanner(String key) {
        mEditor.putString("bannerBack", key).commit();
    }

    public String getKeyValueString(String key) {
        return mSharedPreferences.getString(key, "9099360078");
    }

    public String getKeyValueStringWithZero(String key) {
        return mSharedPreferences.getString(key, "0");
    }

    public int getKeyValueInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
    public int getKeyValue(String key) {
        return mSharedPreferences.getInt(key, -1);
    }
    public void setKeyValue(String key, int value) {
        mEditor.putInt(key, value).commit();
    }
    public boolean getKeyValueBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void setUserFullName(String fullName, String userFullName) {
        mEditor.putString(fullName, userFullName).commit();
    }

    public String getUserName() {
        return mSharedPreferences.getString(VariableBag.FULL_NAME, "Parth Jadav");
    }

    public void setJSONPref(String key, String json) {
        mEditor.putString(key, json).commit();
    }

    public String getJSONPref(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public JSONObject getJSONObject(String key) {
        try {
            return new JSONObject(mSharedPreferences.getString(key, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getJSONArray(String key) {
        try {
            return new JSONArray(mSharedPreferences.getString(key, ""));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getJSONKeyString(String objKey, String stringKey) {
        JSONObject obj = getJSONObject(objKey);
        if (objKey != null) {
            try {
                return obj.getString(stringKey);
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }


    public String getCommonUserFullName() {
        return mSharedPreferences.getString(VariableBag.COMMON_NAME, "");
    }

    public void setCommonUserFullName(String userFullName) {
        mEditor.putString(VariableBag.COMMON_NAME, userFullName).commit();
    }

    public String getCommonUserMobile() {
        return mSharedPreferences.getString(VariableBag.COMMON_MOBILE, "");
    }

    public void setCommonUserMobile(String userFullName) {
        mEditor.putString(VariableBag.COMMON_MOBILE, userFullName).commit();
    }

    public String getCommonUserID() {
        return mSharedPreferences.getString(VariableBag.COMMON_ID, "");
    }

    public void setCommonUserID(String userFullName) {
        mEditor.putString(VariableBag.COMMON_ID, userFullName).commit();
    }

    public String getCommonUserPro() {
        return mSharedPreferences.getString(VariableBag.COMMON_PROFILE, "");
    }

    public void setCommonUserPro(String userFullName) {
        mEditor.putString(VariableBag.COMMON_PROFILE, userFullName).commit();
    }

    public void deleteCommonUser() {
        mEditor.putBoolean(VariableBag.COMMON, false);
        mEditor.commit();
    }

    public void setCommonUser() {
        mEditor.putBoolean(VariableBag.COMMON, true);
        mEditor.commit();
    }

    public boolean getCommonUser() {
        return mSharedPreferences.getBoolean(VariableBag.COMMON, false);
    }

    public void setCommonUserFag() {
        mEditor.putBoolean(VariableBag.COMMON_FLG, true);
        mEditor.commit();
    }

    public boolean getCommonUserFag() {
        return mSharedPreferences.getBoolean(VariableBag.COMMON_FLG, false);
    }

    public void deleteCommon() {
        mEditor.putBoolean(VariableBag.COMMON, false).commit();
        mEditor.putString(VariableBag.COMMON_PROFILE, "").commit();
        mEditor.putString(VariableBag.COMMON_ID, "").commit();
        mEditor.putString(VariableBag.COMMON_MOBILE, "").commit();
        mEditor.putString(VariableBag.COMMON_NAME, "").commit();
    }

    public void deleteCommonUserFag() {
        mEditor.putBoolean(VariableBag.COMMON_FLG, false);
        mEditor.commit();
    }

    public String getSocietyCity() {
        return mSharedPreferences.getString(VariableBag.S_CITY, "");
    }

    public void setSocietyCity(String id) {
        mEditor.putString(VariableBag.S_CITY, id).commit();
    }


    public boolean isExpenseOn() {
        return mSharedPreferences.getBoolean(VariableBag.EXPENSE_ON, false);
    }

    public void setExpenseOn(boolean value) {
        mEditor.putBoolean(VariableBag.EXPENSE_ON, value).commit();
    }


    public String getMaxExpenseAmount() {
        return mSharedPreferences.getString(VariableBag.MAX_EXPENSE_AMOUNT,
                "0.00");
    }

    public void setMaxExpenseAmount(String amount) {
        mEditor.putString(VariableBag.MAX_EXPENSE_AMOUNT, amount).commit();
    }

    public int getCurrentLanguage() {
        return mSharedPreferences.getInt(VariableBag.CUR_LANG, 1);
    }

    public void setCurrentLanguage(int lan) {
        mEditor.putInt(VariableBag.CUR_LANG, lan);
        mEditor.commit();
    }


    public String getCUR_PROFESSIONAL_CATEGORY() {
        return mSharedPreferences.getString(VariableBag.CUR_PROFESSIONAL_CATEGORY, "");
    }

    public void setCUR_PROFESSIONAL_CATEGORY(String id) {
        mEditor.putString(VariableBag.CUR_PROFESSIONAL_CATEGORY, id).commit();
    }

    public String getCUR_PROFESSIONAL_TYPE() {
        return mSharedPreferences.getString(VariableBag.CUR_PROFESSIONAL_TYPE, "");
    }

    public void setCUR_PROFESSIONAL_TYPE(String id) {
        mEditor.putString(VariableBag.CUR_PROFESSIONAL_TYPE, id).commit();
    }

    public int getChatMemberCurrentCount() {
        return mSharedPreferences.getInt(VariableBag.CHAT_MEMBER_COUNT, 0);
    }

    public void setChatMemberCurrentCount(int counter) {
        mEditor.putInt(VariableBag.CHAT_MEMBER_COUNT, counter).commit();
    }

    public void setChatGroupCurrentCount(int counter) {
        mEditor.putInt(VariableBag.CHAT_MEMBERGROUP_COUNT, counter).commit();
    }

    public int getChatGROUPCurrentCount() {
        return mSharedPreferences.getInt(VariableBag.CHAT_MEMBERGROUP_COUNT, 0);
    }

    public int getChatGateCurrentCount() {
        return mSharedPreferences.getInt(VariableBag.CHAT_GATEKEEPER_COUNT, 0);

    }

    public void setChatGateCurrentCount(int counter) {
        mEditor.putInt(VariableBag.CHAT_GATEKEEPER_COUNT, counter).commit();
    }

    public boolean getNotificationSoundSetting() {
        return mSharedPreferences.getBoolean(VariableBag.NOTI_SOUND_SETTING, true);
    }

    public void setNotificationSoundSetting(boolean v) {
        mEditor.putBoolean(VariableBag.NOTI_SOUND_SETTING, v);
        mEditor.commit();
    }

    public boolean getNotificationVibrationSetting() {
        return mSharedPreferences.getBoolean(VariableBag.NOTI_VIBR_SETTING, true);
    }

    public void setNotificationVibrationSetting(boolean v) {
        mEditor.putBoolean(VariableBag.NOTI_VIBR_SETTING, v);
        mEditor.commit();
    }

    public boolean getSystemLockSetting() {
        return mSharedPreferences.getBoolean(VariableBag.NOTI_SYSTEM_LOCK, false);
    }

    public void setSystemLockSetting(boolean v) {
        mEditor.putBoolean(VariableBag.NOTI_SYSTEM_LOCK, v);
        mEditor.commit();
    }

    public boolean getSosPinLock() {
        return mSharedPreferences.getBoolean(VariableBag.NOTI_SOS_LOCK, false);
    }

    public void setSosPinLock(boolean v) {
        mEditor.putBoolean(VariableBag.NOTI_SOS_LOCK, v);
        mEditor.commit();
    }

    public String getUserGender() {
        return mSharedPreferences.getString(VariableBag.GENDER, "");
    }

    public void setUserGender(String s) {
        mEditor.putString(VariableBag.GENDER, s).commit();
    }


    public String getDefaultLanguage() {
        return mSharedPreferences.getString("langId", "");
    }

    public void setDefaultLanguage(String s) {
        mEditor.putString("langId", s).commit();
    }

    public String getLastTimelineId() {
        return mSharedPreferences.getString(VariableBag.LAST_TIMELINE_ID, "0");
    }

    public void setLastTimeLineId(String s) {
        mEditor.putString(VariableBag.LAST_TIMELINE_ID, s).commit();
    }

    public boolean getMenuMyActivity() {
        return mSharedPreferences.getBoolean(VariableBag.MENU_MYACTIVITY, false);
    }

    public void setMenuMyActivity(boolean v) {
        mEditor.putBoolean(VariableBag.MENU_MYACTIVITY, v);
        mEditor.commit();
    }

    public boolean getMenuChat() {
        return mSharedPreferences.getBoolean(VariableBag.MENU_CHAT, false);
    }

    public void setMenuChat(boolean v) {
        mEditor.putBoolean(VariableBag.MENU_CHAT, v);
        mEditor.commit();
    }

    public boolean getMenuTimeline() {
        return mSharedPreferences.getBoolean(VariableBag.MENU_TIMLINE, false);
    }

    public void setMenuTimeline(boolean v) {
        mEditor.putBoolean(VariableBag.MENU_TIMLINE, v);
        mEditor.commit();
    }

    public boolean getWorkReportStatus() {
        return mSharedPreferences.getBoolean(VariableBag.WORK_REPORT, false);
    }

    public void setWorkReportStatus(boolean v) {
        mEditor.putBoolean(VariableBag.WORK_REPORT, v);
        mEditor.commit();
    }

    public String getAttendanceType() {
        return mSharedPreferences.getString(VariableBag.ATTENDANCE_TYPE, "");
    }

    public void setAttendanceType(String attendanceType) {
        mEditor.putString(VariableBag.ATTENDANCE_TYPE, attendanceType);
        mEditor.commit();
    }

    public String getLanguageId() {
        return "1";
    }


    public int getVersionLanguageCode() {
        return mSharedPreferences.getInt(VariableBag.VERSION_CODE_LANGUAGE, 0);
    }

    public void setVersionLanguageCode(int value) {
        mEditor.putInt(VariableBag.VERSION_CODE_LANGUAGE, value).commit();
    }


    public String getCountryID() {
        return mSharedPreferences.getString(VariableBag.COUNTRY_ID, "0");
    }

    public void setCountryID(String id) {
        mEditor.putString(VariableBag.COUNTRY_ID, id).commit();
    }


    public String getStateID() {
        return mSharedPreferences.getString(VariableBag.STATE_ID, "0");
    }

    public void setStateID(String id) {
        mEditor.putString(VariableBag.STATE_ID, id).commit();
    }


    public String getCityID() {
        return mSharedPreferences.getString(VariableBag.CITY_ID, "0");
    }

    public void setCityID(String id) {
        mEditor.putString(VariableBag.CITY_ID, id).commit();
    }


    public Uri getRingtoneNotification() {
        return Uri.parse(mSharedPreferences.getString(VariableBag.RINGTONE_NOTIFICATION, ""));
    }

    public void setRingtoneNotification(Uri v) {
        mEditor.putString(VariableBag.RINGTONE_NOTIFICATION, String.valueOf(v));
        mEditor.commit();
    }

    public Uri getRingtoneSOS() {
        return Uri.parse(mSharedPreferences.getString(VariableBag.RINGTONE_SOS, ""));
    }

    public void setRingtoneSOS(Uri v) {
        mEditor.putString(VariableBag.RINGTONE_SOS, String.valueOf(v));
        mEditor.commit();
    }

    public Uri getRingtoneVisitor() {
        return Uri.parse(mSharedPreferences.getString(VariableBag.RINGTONE_VISITOR, ""));

    }

    public void setRingtoneVisitor(Uri v) {
        mEditor.putString(VariableBag.RINGTONE_VISITOR, String.valueOf(v));
        mEditor.commit();
    }


    public Uri getRingtoneCourier() {
        return Uri.parse(mSharedPreferences.getString(VariableBag.RINGTONE_COURIER, ""));

    }

    public void setRingtoneCourier(Uri v) {
        mEditor.putString(VariableBag.RINGTONE_COURIER, String.valueOf(v));
        mEditor.commit();
    }


    public Uri getRingtoneChild() {
        return Uri.parse(mSharedPreferences.getString(VariableBag.RINGTONE_CHILD, ""));

    }

    public void setRingtoneChild(Uri v) {
        mEditor.putString(VariableBag.RINGTONE_CHILD, String.valueOf(v));
        mEditor.commit();
    }


    public String getRingtoneNotificationName() {
        return mSharedPreferences.getString(VariableBag.RINGTONE_NOTIFICATION_NAME, "");
    }

    public void setRingtoneNotificationName(String v) {
        mEditor.putString(VariableBag.RINGTONE_NOTIFICATION_NAME, v);
        mEditor.commit();
    }

    public String getRingtoneSOSName() {
        return mSharedPreferences.getString(VariableBag.RINGTONE_SOS_NAME, "");
    }

    public void setRingtoneSOSName(String v) {
        mEditor.putString(VariableBag.RINGTONE_SOS_NAME, v);
        mEditor.commit();
    }

    public String getRingtoneVisitorName() {
        return mSharedPreferences.getString(VariableBag.RINGTONE_VISITOR_NAME, "");

    }

    public void setRingtoneVisitorName(String v) {
        mEditor.putString(VariableBag.RINGTONE_VISITOR_NAME, v);
        mEditor.commit();
    }


    public String getRingtoneCourierName() {
        return mSharedPreferences.getString(VariableBag.RINGTONE_COURIER_NAME, "");

    }

    public void setRingtoneCourierName(String v) {
        mEditor.putString(VariableBag.RINGTONE_COURIER_NAME, v);
        mEditor.commit();
    }


    public String getRingtoneChildName() {
        return mSharedPreferences.getString(VariableBag.RINGTONE_CHILD_NAME, "");

    }

    public void setRingtoneChildName(String v) {
        mEditor.putString(VariableBag.RINGTONE_CHILD_NAME, v);
        mEditor.commit();
    }


    public boolean getCompleteProfile() {
        return mSharedPreferences.getBoolean(VariableBag.COMPLETE_PROFILE, false);
    }

    public void setCompleteProfile(boolean v) {
        mEditor.putBoolean(VariableBag.COMPLETE_PROFILE, v);
        mEditor.commit();
    }



    public boolean isAllLeaveON() {
        return mSharedPreferences.getBoolean(VariableBag.ALL_LEAVE_ON, false);
    }

    public void setAllLeaveON(boolean v) {
        mEditor.putBoolean(VariableBag.ALL_LEAVE_ON, v);
        mEditor.commit();
    }


    public boolean isAllExpenseON() {
        return mSharedPreferences.getBoolean(VariableBag.ALL_EXPENSE_ON, false);
    }

    public void setAllExpenseON(boolean v) {
        mEditor.putBoolean(VariableBag.ALL_EXPENSE_ON, v);
        mEditor.commit();
    }


    public boolean isEmployeeRequestON() {
        return mSharedPreferences.getBoolean(VariableBag.EMPLOYEE_REQUEST_APPROVAL, false);
    }

    public void setEmployeeRequestON(boolean v) {
        mEditor.putBoolean(VariableBag.EMPLOYEE_REQUEST_APPROVAL, v);
        mEditor.commit();
    }



    public boolean isEmployeeAddON() {
        return mSharedPreferences.getBoolean(VariableBag.ADD_NEW_EMPLOYEE, false);
    }

    public void setEmployeeAddON(boolean v) {
        mEditor.putBoolean(VariableBag.ADD_NEW_EMPLOYEE, v);
        mEditor.commit();
    }


    public boolean getDNDStatus() {
        return mSharedPreferences.getBoolean(VariableBag.DND_STATUS, false);
    }

    public void setDNDStatus(boolean v) {
        mEditor.putBoolean(VariableBag.DND_STATUS, v);
        mEditor.commit();
    }


    public boolean getFireBaseChat() {
        return mSharedPreferences.getBoolean(VariableBag.FIRE_CHAT, false);
    }

    public void setFireBaseChat(boolean v) {
        mEditor.putBoolean(VariableBag.FIRE_CHAT, v);
        mEditor.commit();
    }


    public boolean getDNDIsCustom() {
        return mSharedPreferences.getBoolean(VariableBag.DND_CUSTOM, false);
    }

    public void setDNDIsCustom(boolean v) {
        mEditor.putBoolean(VariableBag.DND_CUSTOM, v);
        mEditor.commit();
    }

    public boolean isVisitorDNDInclude() {
        return mSharedPreferences.getBoolean(VariableBag.DND_VISITOR, true);
    }

    public void setVisitorDND(boolean v) {
        mEditor.putBoolean(VariableBag.DND_VISITOR, v);
        mEditor.commit();
    }

    public void setDNDEndDate(String v) {
        mEditor.putString(VariableBag.DND_END_DATE, v);
        mEditor.commit();
    }

    public String getDNDEndDate() {
        return mSharedPreferences.getString(VariableBag.DND_END_DATE, "");

    }

    public void setDNDStartTime(String v) {
        mEditor.putString(VariableBag.DND_START_TIME, v);
        mEditor.commit();
    }

    public String getDNDStartTime() {
        return mSharedPreferences.getString(VariableBag.DND_START_TIME, "");
    }

    public void setDNDEndTime(String v) {
        mEditor.putString(VariableBag.DND_END_TIME, v);
        mEditor.commit();
    }

    public String getDNDEndTime() {
        return mSharedPreferences.getString(VariableBag.DND_END_TIME, "");
    }


    //json

    public void setObject(String key, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        mEditor.putString(key, json).commit();
    }

    public <GenericClass> GenericClass getObject(String key, Class<GenericClass> object) {
        try {
            Gson gson = new Gson();
            String json = mSharedPreferences.getString(key, "");
            return gson.fromJson(json, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}


