package pk.EA.Scenario.app.Etiquette.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.fragments.IntroductionFragment;
import pk.EA.Scenario.app.Etiquette.fragments.LoginFragment;


public class MainActivity extends ActionBarActivity implements
        View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    public DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();

                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.discover:
                        Toast.makeText(getApplicationContext(), "Discover Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.addScenario:
                        Toast.makeText(getApplicationContext(), "Add Scenario Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.setting:
                        Toast.makeText(getApplicationContext(), "Setting Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


        IntroductionFragment newFrag = new IntroductionFragment();
        //TravelQuestionFragment newFrag = new TravelQuestionFragment();

        android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        trans.add(R.id.fragment_container, newFrag, "IntroFragment").commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LoginFragment frag = (LoginFragment)getSupportFragmentManager().findFragmentByTag("loginFragment");
        if(frag != null && frag.isVisible())
        {
            frag.onActivityResult(requestCode, resultCode, data);
        }

    }


    @Override
    public void onClick(View v) {

    }

    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
            return;
        }

        Fragment popularFragment = (Fragment) getSupportFragmentManager().findFragmentByTag("PopularFragment");
        Fragment profileFragment = (Fragment) getSupportFragmentManager().findFragmentByTag("ProfileFragment");
        Fragment loginFragment = (Fragment) getSupportFragmentManager().findFragmentByTag("loginFragment");
        Fragment signupFragment = (Fragment) getSupportFragmentManager().findFragmentByTag("signupFragment");
        Fragment latestFragment = getSupportFragmentManager().findFragmentByTag("LatestFragment");
        Fragment categoriesFragment = getSupportFragmentManager().findFragmentByTag("CategoriesFragment");
        Fragment introFragment = getSupportFragmentManager().findFragmentByTag("IntroFragment");


        if ((loginFragment != null && loginFragment.isVisible())
                || (signupFragment != null && signupFragment.isVisible())) {
            IntroductionFragment newFrag = new IntroductionFragment();

            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container, newFrag, "IntroFragment");

            trans.commit();

        }
        else if ((popularFragment != null && popularFragment.isVisible())
                || (profileFragment != null && profileFragment.isVisible())
                || (latestFragment != null && latestFragment.isVisible())
                || (categoriesFragment != null && categoriesFragment.isVisible())
                || (introFragment != null && introFragment.isVisible())) {
            if (doubleBackToExitPressedOnce) {
                try {
                    LoginManager.getInstance().logOut();
                }
                catch (Exception e){}

                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
