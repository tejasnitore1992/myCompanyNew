package com.mycompanynew.life_at_my_company.activities;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Toast;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ActivityApplyJobBinding;
import com.mycompanynew.interfaces.DateSelectListener;
import com.mycompanynew.life_at_my_company.response.ApplyOpeningResponse;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningItem;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningResponse;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.utils.Functions;
import com.mycompanynew.utils.IntentData;
import com.mycompanynew.utils.PRRequestBody;
import com.mycompanynew.utils.PathUtil;
import com.mycompanynew.utils.PathUtil1;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ApplyJobActivity extends AppCompatActivity {

    private Context context;
    private ActivityApplyJobBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    private List<CurrentOpeningItem> currentOpeningItemList = new ArrayList<>();
    private ArrayAdapter<CurrentOpeningItem> spinnerPositionArrayAdapter;
    private SimpleDateFormat sdfDmy = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyy-MM-dd");

    private ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplyJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = ApplyJobActivity.this;

        tools = new Tools(ApplyJobActivity.this);
        preferenceManager = new PreferenceManager(ApplyJobActivity.this);

        restCall = RestClient.createService(RestCall.class, preferenceManager.getBaseUrl(),
                preferenceManager.getApiKey(),
                preferenceManager.getUserName(),
                Tools.getCurrentPassword(preferenceManager.getSocietyId(),
                        preferenceManager.getRegisteredUserId(),
                        preferenceManager.getKeyValueString(VariableBag.USER_Mobile)));


        init();
    }

    private void init(){

        spinnerPositionArrayAdapter = new ArrayAdapter<CurrentOpeningItem>(this, android.R.layout.simple_spinner_item,currentOpeningItemList);
        spinnerPositionArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        binding.acsPosition.setAdapter(spinnerPositionArrayAdapter);

        binding.tietDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String selectedDateStr = binding.tietDateOfBirth.getText().toString();
                    Date selectedDate = null;
                    if(!TextUtils.isEmpty(selectedDateStr))
                        selectedDate = sdfDmy.parse(selectedDateStr);

                    Calendar calendarMaxDate = Calendar.getInstance();
                    calendarMaxDate.add(Calendar.YEAR,-8);

                    Functions.showDatePicker(context, selectedDate, null, calendarMaxDate.getTime(), new DateSelectListener() {
                        @Override
                        public void dateSelect(Calendar calendar) {
                            binding.tietDateOfBirth.setText(sdfDmy.format(calendar.getTime()));
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        binding.mbtnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()){
                    selectFile();
                }else{
                    requestPermission();
                }


            }
        });

        binding.mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldValidation();
            }
        });
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Uri uri = result.getData().getData();
                            DocumentFile file = DocumentFile.fromSingleUri(context,uri);
                            file.getUri().getPath();
                            Log.i("My Company URI",uri.getPath());
                            try {
                                Log.i("My Company PATH", PathUtil.getPath(context,uri));
//                                Log.i("My Company PATH", uri.getEncodedPath());
//                                File file = new File(uri);
//                                Log.i("My Company PATH", file.getAbsolutePath());
                                Log.i("My Company PATH", file.getUri().getPath());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.i("My Company Error", e.getMessage());
                            }
                        }
                    }
                });

        handleBundle();
    }

    private void handleBundle() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(IntentData.INTENT_CURRENT_OPENING_ITEM)){
            CurrentOpeningItem openingItem = (CurrentOpeningItem) bundle.getSerializable(IntentData.INTENT_CURRENT_OPENING_ITEM);
            currentOpeningItemList.clear();
            currentOpeningItemList.add(openingItem);
            if(spinnerPositionArrayAdapter != null)
                spinnerPositionArrayAdapter.notifyDataSetChanged();
        }else{
            getData();
        }
    }

    public void getData() {

        restCall.getCurrentOpeningData(
                "getCurrentOpening",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<CurrentOpeningResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(context,"Something went wrong!!!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onNext(CurrentOpeningResponse response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.getStatus().equalsIgnoreCase(VariableBag.SUCCESS_CODE)) {
                                    // code here


                                    setData(response);
                                } else {
                                    // No data view
                                   Toast.makeText(context,"Something went wrong!!!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
    }

    private void setData(CurrentOpeningResponse response) {
        currentOpeningItemList.clear();
        CurrentOpeningItem item = new CurrentOpeningItem();
        item.setCompanyCurrentOpeningId("-1");
        item.setCompanyCurrentOpeningTitle("-- Select Position --");
        item.setCompanyCurrentOpeningPosition("");
        item.setCompanyCurrentOpeningAddress("");
        item.setCompanyCurrentOpeningTiming("");
        currentOpeningItemList.add(item);
        currentOpeningItemList.addAll(response.getOpeningItemList());
        if(spinnerPositionArrayAdapter != null)
            spinnerPositionArrayAdapter.notifyDataSetChanged();
    }

    private void fieldValidation() {

        CurrentOpeningItem selectedOpening = (CurrentOpeningItem) binding.acsPosition.getSelectedItem();
        String name = binding.tietName.getText().toString();
        String dateOfBirth = binding.tietDateOfBirth.getText().toString();
        String gender = null;
        if(binding.rbMale.isChecked()){
            gender = binding.rbMale.getText().toString();
        }else if(binding.rbFemale.isChecked()){
            gender = binding.rbFemale.getText().toString();
        }else if(binding.rbGenderOther.isChecked()){
            gender = binding.rbGenderOther.getText().toString();
        }
        String state = binding.tietState.getText().toString();
        String city = binding.tietCity.getText().toString();
        String contactNo = binding.tietContactNo.getText().toString();
        String emailId = binding.tietEmailId.getText().toString();
        String education = null;
        if(binding.rbGraduate.isChecked()){
            education = binding.rbGraduate.getText().toString();
        }else if(binding.rbPostGraduate.isChecked()){
            education = binding.rbPostGraduate.getText().toString();
        }else if(binding.rbEducationOther.isChecked()){
            education = binding.rbEducationOther.getText().toString();
        }

        String totalYearOfExperience = (String) binding.acsTotalYearOfExperience.getSelectedItem();

        if(selectedOpening.getCompanyCurrentOpeningId() == null || Integer.parseInt(selectedOpening.getCompanyCurrentOpeningId()) < 1){
            Toast.makeText(context,"Please select position!!!",Toast.LENGTH_LONG).show();
            binding.acsPosition.requestFocus();
            binding.scrollView.fullScroll(ScrollView.FOCUS_UP);
        }else if(TextUtils.isEmpty(name)){

            Toast.makeText(context,"Please entre name!!!",Toast.LENGTH_LONG).show();
            binding.tietName.requestFocus();
        }else if(TextUtils.isEmpty(dateOfBirth)){

            Toast.makeText(context,"Please select date of birth!!!",Toast.LENGTH_LONG).show();
            binding.tietDateOfBirth.requestFocus();
        }else if(TextUtils.isEmpty(gender)){
            Toast.makeText(context,"Please select gender!!!",Toast.LENGTH_LONG).show();
            binding.rgGender.requestFocus();
        }else if(TextUtils.isEmpty(state)){
            Toast.makeText(context,"Please entre state!!!",Toast.LENGTH_LONG).show();
            binding.tietState.requestFocus();
        }else if(TextUtils.isEmpty(city)){
            Toast.makeText(context,"Please entre city!!!",Toast.LENGTH_LONG).show();
            binding.tietCity.requestFocus();
        }else if(TextUtils.isEmpty(contactNo)){
            Toast.makeText(context,"Please entre contact number!!!",Toast.LENGTH_LONG).show();
            binding.tietContactNo.requestFocus();
        }else if(TextUtils.isEmpty(emailId)){
            Toast.makeText(context,"Please entre email address!!!",Toast.LENGTH_LONG).show();
            binding.tietEmailId.requestFocus();
        }else if(TextUtils.isEmpty(education)){
            Toast.makeText(context,"Please select eduction!!!",Toast.LENGTH_LONG).show();
            binding.rgEducationInfo.requestFocus();
        }else if(TextUtils.isEmpty(totalYearOfExperience)){
            Toast.makeText(context,"Please select total year of experience!!!",Toast.LENGTH_LONG).show();
            binding.acsTotalYearOfExperience.requestFocus();
        }else{
            try {
                applyJob(selectedOpening.getCompanyCurrentOpeningId(), name, sdfYmd.format(
                        sdfDmy.parse(dateOfBirth)
                ), gender, state, city, contactNo, emailId, education, totalYearOfExperience);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void applyJob(String openingId,String name, String dateOfBirth, String gender, String state, String city,
                          String contactNo, String emailId, String education, String totalYearOfExperience) {

      /*  File resume = null;
        PRRequestBody requestFile = new PRRequestBody(resume, MediaType.parse(Uri.fromFile(resume).toString()));
        requestFile.setFileUploaderCallback(new PRRequestBody.FileUploaderCallback() {
            @Override
            public void onProgressUpdate(int currentpercent, int totalpercent) {

            }
        });
        MultipartBody.Part bodyResume =
                MultipartBody.Part.createFormData("company_current_opening_resume", resume.getName(), requestFile);
*/
        RequestBody requestBodyApplyOpening = RequestBody.create("applyOpening",MediaType.parse("multipart/form-data"));
        RequestBody requestBodySocietyId = RequestBody.create(preferenceManager.getSocietyId(),MediaType.parse("multipart/form-data"));
        RequestBody requestBodyLanguageId = RequestBody.create(preferenceManager.getLanguageId(),MediaType.parse("multipart/form-data"));
        RequestBody requestBodyOpeningId = RequestBody.create(openingId,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyName = RequestBody.create(name,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyDateOfBirth = RequestBody.create(dateOfBirth,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyGender = RequestBody.create(gender,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyState = RequestBody.create(state,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyCity = RequestBody.create(city,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyContactNo = RequestBody.create(contactNo,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyEmailId = RequestBody.create(emailId,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyEduction = RequestBody.create(education,MediaType.parse("multipart/form-data"));
        RequestBody requestBodyTotalYearOfExperience = RequestBody.create(totalYearOfExperience,MediaType.parse("multipart/form-data"));

        restCall.applyOpening(
                requestBodyApplyOpening,
                requestBodySocietyId,
                requestBodyLanguageId,
                requestBodyOpeningId,
                requestBodyName,
                requestBodyDateOfBirth,
                requestBodyGender,
                requestBodyState,
                requestBodyCity,
                requestBodyContactNo,
                requestBodyEmailId,
                requestBodyEduction,
                requestBodyTotalYearOfExperience,
                null)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ApplyOpeningResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onNext(ApplyOpeningResponse response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.getStatus().equalsIgnoreCase(VariableBag.SUCCESS_CODE)) {
                                    // code here
                                    Toast.makeText(context,response.getMessage(),Toast.LENGTH_LONG).show();
                                } else {
                                    // No data view
                                    Toast.makeText(context,response.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
    }

    private void selectFile() {

        Intent intent = null;

            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
        someActivityResultLauncher.launch(intent);
    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(ApplyJobActivity.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ApplyJobActivity.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            /*try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);

            }*/
            ActivityCompat.requestPermissions(ApplyJobActivity.this, new String[]{MANAGE_EXTERNAL_STORAGE}, 1000);
        } else {
            //below android 11
            ActivityCompat.requestPermissions(ApplyJobActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (SDK_INT >= Build.VERSION_CODES.R) {

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectFile();
            }

        }else{
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    selectFile();
                }
            }
        }


    }
}