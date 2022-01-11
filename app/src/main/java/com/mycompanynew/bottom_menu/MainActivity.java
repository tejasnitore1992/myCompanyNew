package com.mycompanynew.bottom_menu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mycompanynew.R;
import com.mycompanynew.about_us.AboutUsFragment;
import com.mycompanynew.databinding.ActivityMainBinding;
import com.mycompanynew.get_in_touch.GetInTouchFragment;
import com.mycompanynew.home.HomeFragment;
import com.mycompanynew.interfaces.HomeButtonClickListener;
import com.mycompanynew.life_at_my_company.LifeAtMyCompanyFragment;
import com.mycompanynew.our_services.OurServicesFragment;

public class MainActivity extends AppCompatActivity implements HomeButtonClickListener {
    //implements NavigationBarView.OnItemSelectedListener
    private ActivityMainBinding binding;

    //Fragments
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private int selectedBottomId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.navView.setOnItemSelectedListener(this);


        replaceHome();
        handleBottomButtons();

    }


    private void replaceHome() {
        unSelectMenu();
        binding.llcBottomMenu.homeUnselect.setVisibility(View.GONE);
        binding.llcBottomMenu.homeSelect.setVisibility(View.VISIBLE);
        selectedBottomId = binding.llcBottomMenu.rlHome.getId();
        Fragment fragment = new HomeFragment();
        replaceFragment(fragment, "");
    }

    private void replaceFragment(Fragment fragment, final String mTag) {
        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment);
            fragmentTransaction.commit();
//            getSupportActionBar().setTitle(getFragmentTitle(fragment.getClass().getSimpleName()));
//            getSupportActionBar().setTitle(getFragmentTitle(mTag));
        }
    }

    private void addFragment(Fragment fragment, final String mTag) {
        //if (fragment != null && !getSupportActionBar().getTitle().equals(getFragmentTitle(fragment.getClass().getSimpleName()))) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment_activity_main, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(mTag);
        fragmentTransaction.commit();
//            getSupportActionBar().setTitle(getFragmentTitle(fragment.getClass().getSimpleName()));
//            getSupportActionBar().setTitle(getFragmentTitle(mTag));
        // }
    }

    private String getFragmentTitle(final String TAG) {
        String title = "";
        Fragment fragment = null;
        String mTag = "";

        return title;
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        String mTag = "";
        switch (id){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                mTag = "";
            break;

            case R.id.navigation_about_us:
                fragment = new AboutUsFragment();
                mTag = "";
                break;

            default:
                fragment = new HomeFragment();
                mTag = "";
                break;
        }
        addFragment(fragment, mTag);
        return true;
    }*/


    private void handleBottomButtons() {

        View.OnClickListener bottomClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedBottomId != view.getId()) {
                    selectedBottomId = view.getId();
                    handleBottomMenu(view.getId());
                }
            }
        };

        binding.llcBottomMenu.rlHome.setOnClickListener(bottomClickListener);
        binding.llcBottomMenu.rlPhoneCall.setOnClickListener(bottomClickListener);
        binding.llcBottomMenu.rlService.setOnClickListener(bottomClickListener);
        binding.llcBottomMenu.rlClient.setOnClickListener(bottomClickListener);
        binding.llcBottomMenu.rlNoVacancy.setOnClickListener(bottomClickListener);
    }

    private void handleBottomMenu(int id) {
        unSelectMenu();
        Fragment fragment = null;
        String mTag = "";
        switch (id) {
            case R.id.rl_home:
                binding.llcBottomMenu.homeUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.homeSelect.setVisibility(View.VISIBLE);
                fragment = new HomeFragment();
                mTag = "";

                break;
            case R.id.rl_phone_call:
                binding.llcBottomMenu.phoneCallUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.phoneCallSelect.setVisibility(View.VISIBLE);
                fragment = new GetInTouchFragment();
                mTag = "";
                break;
            case R.id.rl_service:
                binding.llcBottomMenu.serviceUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.serviceSelect.setVisibility(View.VISIBLE);
                fragment = new OurServicesFragment();
                mTag = "";
                break;
            case R.id.rl_client:
                binding.llcBottomMenu.clientUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.clientSelect.setVisibility(View.VISIBLE);
                fragment = new AboutUsFragment();
                mTag = "";
                break;
            case R.id.rl_no_vacancy:
                binding.llcBottomMenu.noVacancyUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.noVacancySelect.setVisibility(View.VISIBLE);
                fragment = new LifeAtMyCompanyFragment();
                mTag = "";
                break;
            default:
                binding.llcBottomMenu.homeUnselect.setVisibility(View.GONE);
                binding.llcBottomMenu.homeSelect.setVisibility(View.VISIBLE);
                fragment = new HomeFragment();
                mTag = "";
                break;
        }
        if (fragment != null)
            addFragment(fragment, mTag);
    }

    private void unSelectMenu() {
        binding.llcBottomMenu.homeSelect.setVisibility(View.GONE);
        binding.llcBottomMenu.phoneCallSelect.setVisibility(View.GONE);
        binding.llcBottomMenu.serviceSelect.setVisibility(View.GONE);
        binding.llcBottomMenu.clientSelect.setVisibility(View.GONE);
        binding.llcBottomMenu.noVacancySelect.setVisibility(View.GONE);

        binding.llcBottomMenu.homeUnselect.setVisibility(View.VISIBLE);
        binding.llcBottomMenu.phoneCallUnselect.setVisibility(View.VISIBLE);
        binding.llcBottomMenu.serviceUnselect.setVisibility(View.VISIBLE);
        binding.llcBottomMenu.clientUnselect.setVisibility(View.VISIBLE);
        binding.llcBottomMenu.noVacancyUnselect.setVisibility(View.VISIBLE);
    }

    @Override
    public void clickOnViewAllOurService(View view) {
        unSelectMenu();
        selectedBottomId = binding.llcBottomMenu.rlService.getId();
        binding.llcBottomMenu.serviceUnselect.setVisibility(View.GONE);
        binding.llcBottomMenu.serviceSelect.setVisibility(View.VISIBLE);
        Fragment fragment = new OurServicesFragment();
        addFragment(fragment, "");
    }

    @Override
    public void clickOnViewAllCurrentOpening(View view) {
        unSelectMenu();
        selectedBottomId = binding.llcBottomMenu.rlNoVacancy.getId();
        binding.llcBottomMenu.noVacancyUnselect.setVisibility(View.GONE);
        binding.llcBottomMenu.noVacancySelect.setVisibility(View.VISIBLE);
        Fragment fragment = new LifeAtMyCompanyFragment();
        addFragment(fragment, "");

    }
}