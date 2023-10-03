package com.example.finalapp;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private MaterialSearchView searchView;
    private MenuItem mMenuSetting;
    DictionaryFragment dictionaryFragment;
    BookmarkFragment bookmarkFragment;
    HomeFragment homeFragment;

    GameZone gameZone;
    private DBHelper mDbHelper;
    Toolbar toolbar;
    TextView mtimerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //create SQLITE instance here
       mDbHelper= new DBHelper(this);
      //  dbHelper = new DescriptionHelper(this);
        // check if database exists
        File databases = getApplication().getDatabasePath(DBHelper.myDatabaseName);
        if(!databases.exists()){
            mDbHelper.getReadableDatabase();
           // dbHelper.getAllDescription();

            if(copyDatabase(this)){
              //  descriptionList=mDbHelper.getAllDescription();
                Toast.makeText(this, "Database copied",Toast.LENGTH_SHORT).show();
            }
            else{

              //  mDbHelper.openDatabase();
                Toast.makeText(this, "database do not copied",Toast.LENGTH_SHORT).show();
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        homeFragment = new HomeFragment();
        dictionaryFragment = new DictionaryFragment();
        bookmarkFragment = new BookmarkFragment();
        gameZone = new GameZone();
        goToFragment(dictionaryFragment, false);
        //goToFragment(homeFragment, true);
        homeFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });
        dictionaryFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                // Toast.makeText(MainActivity.this,value, Toast.LENGTH_SHORT).show();
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });
        bookmarkFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                // Toast.makeText(MainActivity.this,value, Toast.LENGTH_SHORT).show(); // no more text Toast
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });
        gameZone.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });
//        gameFragment.setOnFragmentListener(new FragmentListener() {
//            @Override
//            public void onItemClick(String value) {
//                goToFragment(DetailFragment.getNewInstance(value), false);
//            }
//        });
        EditText editText = findViewById(R.id.edit_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dictionaryFragment.filterValue(s.toString());

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void onBackPressed(){
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mMenuSetting = menu.findItem(R.id.action_settings);
        String id=Global.getState(this, "dic_type");
        if(id!=null){
            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
        }

        else{
           // dictionaryFragment.resetDataSource(Database.getData(R.id.ke_En));
        }
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        Global.saveState(this, "dic_type", String.valueOf(id));
        if(id==R.id.Ke_Am){
            dictionaryFragment.resetDataSource(Database.getData(id));
            mMenuSetting.setIcon(getDrawable(R.drawable.ke_am));
        }
        else if(id==R.id.ke_En){
            dictionaryFragment.resetDataSource(Database.getData(id));
            mMenuSetting.setIcon(getDrawable(R.drawable.ke_en));

        }
        else if(id==R.id.am_ke){
            dictionaryFragment.resetDataSource(Database.getData(id));
            mMenuSetting.setIcon(getDrawable(R.drawable.am_ke));
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onNavigationItemSelected(MenuItem item){
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_home:
                mMenuSetting.setVisible(false);
                toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
                toolbar.setTitle("Home Fragment");
                String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
                if (!currentFragment.equals(HomeFragment.class.getSimpleName())) {
                    goToFragment(homeFragment, false);
                }
                break;
            case R.id.nav_dictionary:
                mMenuSetting.setVisible(true);
                toolbar.findViewById(R.id.edit_search).setVisibility(View.VISIBLE);
                toolbar.setTitle("Dictionary");
                 currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
                if (!currentFragment.equals(DictionaryFragment.class.getSimpleName())) {
                    goToFragment(dictionaryFragment, false);

                }
                break;
            case R.id.nav_favorites:
                    currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
                if (!currentFragment.equals(BookmarkFragment.class.getSimpleName())) {
                    goToFragment(bookmarkFragment, false);
                }
                mMenuSetting.setVisible(false);
                toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
                toolbar.setTitle("Favorites");
                break;
            case R.id.nav_game:
                mMenuSetting.setVisible(false);
                toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
                toolbar.setTitle("Game Zone");
                currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
                if(!currentFragment.equals(GameZone.class.getSimpleName())){
                    goToFragment(gameZone, false);
                }
               // gameFragment.startTimer(mtimerTextView);


             break;
        }
        return true;
    }
    public void goToFragment(Fragment fragment, boolean isTop){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if(!isTop){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        String currentFragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
//    if(currentFragment.equals(BookmarkFragment.class.getSimpleName())){
//
//    }
//    else if(currentFragment.equals(HomeFragment.class.getSimpleName())){
//
//    }
//    else if(currentFragment.equals(DictionaryFragment.class.getSimpleName())){
//
//    }

//
//
//    }
    return true;
    }
    private boolean copyDatabase(Context context ) {

        try {
            int length=0;
            InputStream stream = context.getAssets().open(DBHelper.myDatabaseName);
            String outputFile=mDbHelper.Database_Location + DBHelper.myDatabaseName;
            OutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[4096];
            while ((length = stream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActivity", "copied");
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }
}