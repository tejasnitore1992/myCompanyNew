package com.mycompanynew.utils;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;

import org.json.JSONObject;

import okhttp3.MultipartBody;

public class VariableBag {

    public static String MAIN_KEY = "bmsapikey";
    public static String SUB_URL = "residentApiNew/";
    public static String MAIN_URL = "https://master.my-company.app/mainApi/";
    public static String COMMON_URL = "https://master.my-company.app/commonApi/";
    public static String OFFER_URL = "https://offerscouponsdeals.in/api/merchant/";
    public static String MENU_MYACTIVITY = "my_activity";
    public static String MENU_CHAT = "my_chat";
    public static String MENU_TIMLINE = "my_timleine";
    public static String WORK_REPORT = "work_report";
    public static String ATTENDANCE_TYPE = "attendance_type";
    public static String SUCCESS_CODE = "200";
    public static String FAIL_CODE = "201";
    public static String LOGIN = "LOGIN";
    public static String VPN_CHECK = "VPN_CHECK";
    public static String BACKIMG = "";
    public static String chatVideo = "";
    public static String timeVideo = "";
    public static String settingVideo = "";
    public static String homePageVideo = "";
    public static String NO_DATA_ICON = "no_data_icon";
    public static NotificationManager visitorNotificationNM;
    public static MediaPlayer mediaPlayer;
    public static JSONObject mainObjectJSON;
    public static boolean isBackground = false;
    public static Intent svc;
    public static int VISITOR_NOTIFICATION_ID = 999;
    public static int CLILD_NOTIFICATION_ID = 444;
    public static int SOS_NOTIFICATION_ID = 888;
    public static MultipartBody.Part partsFilePick;
    public static String LABEL_MEMBER_TYPE = "label_member_type";
    public static String LABEL_SETTING_APARTMENT = "label_setting_apartment";
    public static String LABEL_SETTING_RESIDENT = "label_setting_resident";
    public static boolean isAudio = false;
    public static String GUARD = "guard";
    public static String GATEKEEPER = "GateKeeper";
    public static String SERVICE_PROVIDER = "service_provider";
    public static String RESIDENT = "resident";
    public static String GROUP = "group";
    public static String FIRE_CHAT = "FireChat";
    public static String SUBSCRIBE = "subscribe";
    public static String UNSUBSCRIBE = "un_subscribe";
    public static String FIRE_CHAT_GROUP = "FireChatGroup";
    public static String SERVER_KEY = "AAAAwT-3mVg:APA91bE9L6dgsk6XdM4OePC1uCaH_NVY065u0LOG7NgxjydFlI55MBifYICC5yggG1eCgKPngVwHZLG_-P7q2cqliS2haNDcMIVwD1L4sLmxh251h-9S6SXdD5u163aoZQd5dINtIHv1";
    public static final int PAY_REQ_CODE = 938;
    public static boolean IS_LANGUAGE_CHANGE = false;
    public static String FESTIVAL_DATE = "festival_date";
    public static String FESTIVAL_BANNER = "festival_banner";
    public static String PRIMARY_ACCOUNT = "0";
    public static String BUSINESS_CARD_NAME = "businessCardName";
    public static String BUSINESS_CARD_DATA = "businessCardData";


    //community key
    public static String SOCIETY_ID = "society_id";
    public static String SHIFT_TIME_ID = "shift_time_id";
    public static String ATTENDANCE_ID = "attendance_id";
    public static String ATTENDANCE_BREAK_ID = "attendance_break_id";
    public static String BREAK_ID = "break_id";
    public static String CURRENCY = "currency";
    public static String BLOCK_ID = "block_id";
    public static String S_CITY = "city_society";
    public static String EXPENSE_ON = "expense_on";
    public static String MAX_EXPENSE_AMOUNT = "max_expense_amount";
    public static String FLOOR_ID = "floor_id";
    public static String UNIT_ID = "unit_id";
    public static String CITY_ID = "CITY_ID";
    public static String STATE_ID = "STATE_ID";
    public static String COUNTRY_ID = "COUNTRY_ID";
    public static String DEFAULT_COUNTRY_ID = "101";
    public static String FLOOR_NAME = "floor_name";
    public static String BLOCK_NAME = "block_name";
    public static String UNIT_NAME = "unit_name";
    public static String UNIT_STATUS = "unit_status";
    public static String BLOCK_UNIT_NAME = "block_unit_name";
    public static String SOCIETY_NAME = "society_name";
    public static String SOCIETY_LOGO = "society_logo";

    //user key
    public static String USER_ID = "user_id";
    public static String USER_PUBLIC_MOBILE = "user_public_mobile";
    public static String USER__CHAT_ID = "user_chat_id";
    public static String FULL_NAME = "fullName";
    public static String FIRST_NAME = "firstName";
    public static String LAST_NAME = "lastName";
    public static String Country_Code = "Country_Code";
    public static String Country_Code_Alt = "Country_Code_Alt";
    public static String COMPLETE_PROFILE = "complete_profile";
    public static String ALL_LEAVE_ON = "all_leave_on";
    public static String ALL_EXPENSE_ON = "all_expense_on";
    public static String EMPLOYEE_REQUEST_APPROVAL = "employee_request_approval";
    public static String ADD_NEW_EMPLOYEE = "add_new_employee";
    public static String USER_TYPE = "user_type";
    public static String USER_MEMBER_STATUS = "memberSub";
    public static String MEMBER_STATUS = "memberSub";
    public static String USER_Mobile = "mobile";
    public static String USER_Mobile_Privacy = "mobile_privacy";
    public static String GENDER = "gender";
    public static String BLOOD_GROUP = "blood_group";
    public static String USER_ALTER_MOBILE = "altMobile";
    public static String USER_DOB = "dob";
    public static String USER_PROFILE = "userProfile";
    public static String USER_EMAIL = "email";
    public static String ABOUT = "about";
    public static String PRIVACY = "privacy";

    public static int TOAST_ERROR = 1;
    public static int TOAST_INFO  = 2;
    public static int TOAST_SUCCESS = 3;
    public static int TOAST_WARNING = 4;







    //event key
    public static String EVENT_PAID = "1";
    public static String EVENT_FREE = "0";
    public static String EVENT_BOOKING_OPEN = "0";
    public static String EVENT_BOOKING_CLOSE = "1";
    public static String EVENT_PASS_NOT_PURCHASE = "1";
    public static String EVENT_PASS_PURCHASE = "0";
    public static String EVENT_EDIT = "1";
    public static String EVENT_I_AM_INTERESTED = "0";
    public static String EVENT_OPEN = "1";
    public static String EVENT_CLOSE = "0";

    //visitor key
    public static String CUR_VISITOR_ID = "";
    public static String CUR_VISITOR_NAME = "";
    public static String NORMAL_VISITOR = "0";
    public static String EXPECTED_VISITOR = "1";
    public static String DELIVERY_BOY = "2";
    public static String CAB_VISITOR = "3";
    public static String VISITOR_PENDING = "0";
    public static String VISITOR_APPROVED = "1";
    public static String VISITOR_REJECTED = "4";
    public static String VISITOR_DELETED = "5";
    public static String VISITOR_ENTERED = "2";
    public static String VISITOR_EXITED = "3";
    public static String VISITOR_HOLD = "6";

    //settings key
    public static String NOTI_SOUND_SETTING = "notisound";
    public static String NOTI_SYSTEM_LOCK = "systemlock";
    public static String NOTI_SOS_LOCK = "soslock";
    public static String NOTI_VIBR_SETTING = "vibration";
    public static String PROFESSION_ON_OFF = "professional_on_off";
    public static String PARKING_MEMBERS_VIEW = "memberParkings_on_off";
    public static String WALLET_VIEW = "wallet_view";
    public static String VISITOR_APPROVAL_ON_OFF = "visitor_on_off";
    public static String VISITOR_GROUP_APPROVAL_ON_OFF = "visitor_group_on_off";
    public static String GROUP_CHAT_STATUS = "group_chat_status"; // 0 for active(Group Chat On) 1 for deactive (Group Chat Off)
    public static String SCREEN_SORT_TIMELINE = "screen_sort_capture_in_timeline";
    public static String CREATE_GROUP = "create_group";
    public static String TEAM_REGISTRATION_ON_OFF = "team_registration";


    //General Key
    public static final int LIMIT_POLICE_VERFICATION_DOCUMENT = 1;
    public static final String LANGUAGE_ID = "language_id";
    public static String PREF_NAME = "fincasys_association";
    public static String PREF_NAME_LANG = "Fincasys_association_language";
    public static String ADD_MORE_PREF_NAME = "ADD_MORE_BMS";
    public static String COMMON_NAME = "user_c_name";
    public static String COMMON_ID = "user_c_id";
    public static String COMMON_MOBILE = "user_c_mobile";
    public static String COMMON_PROFILE = "user_c_pro";
    public static String COMMON = "user_c";
    public static String COMMON_FLG = "user_f";
    public static String DEFAULT_SETTINGS = "default_settings";
    public static String VERSION_CODE = "version_code";
    public static String VERSION_CODE_LANGUAGE = "version_code_language";
    public static String CUR_LANG = "app_lang";
    public static String CHAT_MEMBER_COUNT = "chatMCount";
    public static String CHAT_MEMBERGROUP_COUNT = "chatMCountG";
    public static String CHAT_GATEKEEPER_COUNT = "chatGCount";
    public static String STAFF_INSIDE = "1";
    public static String STAFF_OUTSIDE = "2";
    public static String LAST_TIMELINE_ID = "tID";
    public static String APARTMENT_CLOSED_GATEKEEPER = "1";
    public static String LAST_NOTIFICATION_ID = "last_noti";


    //Language key
    public static String LANGUAGE = "language_data";


    // Housie Key
    public static String GAME_NOT_STARTED = "204";
    public static String GAME_OVER = "203";
    public static String GAME_WILL_START = "201";
    public static String GAME_IS_STARTED = "200";
    public static String ROOM_ID = "room_id";
    public static int INT_QUESTION_FLAG = 0;
    public static int INT_ANSWER_FLAG = 1;


    //Facility Key
    public static String DAY_WISE = "0";
    public static String HOURLY = "3";
    public static String MONTHLY_WITH_PERSON = "1";
    public static String FREE = "2";

    //Poll Key
    public static String OPEN_POLL = "0";
    public static String CLOSE_POLL = "1";
    public static String POLL_FOR_OWNER = "1";
    public static String POLL_FOR_TENANT = "2";
    public static String POLL_FOR_TENANT_AND_OWNER = "3";

    //Toast Key
    public static int INFO = 0;
    public static int SUCCESS = 2;
    public static int ERROR = 1;
    public static int WARNING = 3;







    //KBG
    public static String KBG_FIFTY_FIFTY = "50-50";
    public static String KBG_AUDIENCE_POLL = "audience_poll";
    public static String KBG_PHONE_FRIEND = "phone_friend";
    public static String KBG_SWAP_QUESTION = "swap_question";



    //Chat Key
    public static String MSG_TYPE_TEXT = "0";
    public static String MSG_TYPE_IMAGE = "1";
    public static String MSG_TYPE_FILE = "2";
    public static String MSG_TYPE_AUDIO = "3";
    public static String MSG_TYPE_LOCATION = "4";
    public static String MSG_TYPE_CONTACT = "5";
    public static String MSG_TYPE_VIDEO = "6";
    public static String MSG_SENDED = "0";
    public static String MSG_READED = "1";
    public static String CHAT_WITH_RESIDENT = "0";
    public static String CHAT_WITH_GUARD = "1";
    public static String CHAT_WITH_GATEKEEPER = "1";
    public static String CHAT_WITH_SERVICE_PROVIDER = "2";




    //Child Key
    public static String CHILD_SECURITY_ACCEPTED = "0";
    public static String CHILD_SECURITY_APPROVED = "1";
    public static String CHILD_SECURITY_ADD_BY_GATEKEEPER = "2";
    public static String CHILD_SECURITY_REJECTED = "5";
    public static String CHILD_SECURITY_EXIT = "3";
    public static String CHILD_SECURITY_RETURNTOHOME = "4";




    //PaymentData Key
    public static String PAYMENTFORTYPE_MAINTENANCE = "0";
    public static String PAYMENTFORTYPE_BILL = "1";
    public static String PAYMENTFORTYPE_FACILITY = "2";
    public static String PAYMENTFORTYPE_EVENT = "3";
    public static String PAYMENTFORTYPE_PENALTY = "4";
    public static final String SALT = "8289e078-be0b-484d-ae60-052f117f8deb";
    public static final int SALT_KEY_INDEX = 1;
    public static final String MERCHANT_ID = "M2306160483220675579140";


    //ringtone custom
    public static String RINGTONE_NOTIFICATION = "ring_notification";
    public static String RINGTONE_SOS = "ring_sos";
    public static String RINGTONE_VISITOR = "ring_visitor";
    public static String RINGTONE_COURIER = "ring_courier";
    public static String RINGTONE_CHILD = "ring_child";
    public static String RINGTONE_NOTIFICATION_NAME = "ring_notification_name";
    public static String RINGTONE_SOS_NAME = "ring_sos_name";
    public static String RINGTONE_VISITOR_NAME = "ring_visitor_name";
    public static String RINGTONE_COURIER_NAME = "ring_courier_name";
    public static String RINGTONE_CHILD_NAME = "ring_child_name";


    // Timeline Key
    public static String FEED_TEXT = "0";
    public static String FEED_IMAGE = "1";
    public static String FEED_VIDEO = "2";




    // Company Key
    public static String Company_Name="company_name";
    public static String Company_Logo="company_logo";
    public static String Company_Logo_Old="company_logo_old";
    public static String Company_Address="company_address";
    public static String Company_Number="company_number";
    public static String Company_Email="company_email";
    public static String Company_Website="company_website";
    public static String Company_lat="company_lat";
    public static String Company_lang="company_lang";
    public static String Company_brochure="company_brochure";
    public static String Company_visitCard="company_visitCard";
    public static String Business_cat="business_cat";
    public static String Business_cat_other="business_cat_other";
    public static String Business_type="business_type";
    public static String Business_type_other="business_type_other";
    public static String BUSINESS_ABOUT="business_about";
    public static String DESIGNATION_NAME = "designation_name";
    public static String Designation="designation_name";
    public static String KEY_WORDS="keywords";

    public static String CUR_EMPLOYEE_TYPE = "CUR_EMPLOYEE_TYPE";
    public static String CUR_PROFESSIONAL_CATEGORY = "CUR_PROFESSIONAL_CATEGORY";
    public static String CUR_PROFESSIONAL_TYPE = "CUR_PROFESSIONAL_TYPE";


    public static boolean IS_SOCIETY_CHANGE = false;
    public static String SOCIETY_CHANGE_ID = "";
    public static String POST_TEXT_STATUS_PATH = null;



    public static String DND_STATUS = "dnd_status";
    public static String DND_CUSTOM = "dnd_custom";
    public static String DND_VISITOR = "dnd_visitor";
    public static String DND_CHILD = "dnd_child";
    public static String DND_END_DATE = "dnd_end_date";

    public static String DND_START_TIME = "dnd_start_time";
    public static String DND_END_TIME = "dnd_end_time";

}
