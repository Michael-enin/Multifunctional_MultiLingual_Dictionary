package com.example.finalapp;

import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import static android.widget.Toast.LENGTH_SHORT;

public class GameActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    GameStore gameStore;
    int counter=20;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    View layoutView;
    private SQLiteDatabase db;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private FragmentListener listener;
    Toolbar toolbar;
    private TextView textViewDescription;
    private TextView textViewDescriptionCount;
    private TextView textViewScore;
    private TextView textViewTimeCounter;
    private Button myBtn1, myBtn2, myBtn3, myBtn4, mybtnConfirm;
    private List<Description> descriptionList;
    private DBHelper  dbHelper;
    private DescriptionHelper dbh;
    private int questionCounter;
    private int questionCountTotal;
    private Description currentDescription;
    int toRand=0;
    private int score;
    boolean answered;
    private ColorStateList btnDefaultColor;
    List<Button> buttonList;
    Vector<String>vec;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Random random = new Random();
        toRand=random.nextInt();
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescriptionCount = findViewById(R.id.description_count);
        textViewScore = findViewById(R.id.tvScore);
        myBtn1 = findViewById(R.id.bt1);
        myBtn2 = findViewById(R.id.bt2);
        myBtn3 = findViewById(R.id.bt3);
        myBtn4 = findViewById(R.id.bt4);
     //   mybtnConfirm = findViewById(R.id.btnConfirm);
        btnDefaultColor = myBtn1.getTextColors();
           dbh = new DescriptionHelper(this);
           db=dbh.getWritableDatabase();
            descriptionList = dbh.getAllDescription();
        questionCountTotal =descriptionList.size();
        for (int i =0;i<descriptionList.size();i++){
            System.out.println("The Descs are: " +descriptionList.get(i));
        }
        Collections.shuffle(descriptionList);

         showNextDesc();
                  // all buttons clicked
     buttonList = new ArrayList<>();
     buttonList.add(myBtn1);
     buttonList.add(myBtn2);
     buttonList.add(myBtn3);
     buttonList.add(myBtn4);
     Collections.shuffle(buttonList);
     for(final Button bt:buttonList){
         if(bt.getText()==currentDescription.getOption3()) {
             bt.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View v) {
                   if(!answered) {
                      checkAnswer();
                   }
                   else{
                       showNextDesc();
                   }
                 }

             });
         }
     }
       // System.out.println("the anse: " + currentDescription.getOption1());
    }
    public void showNextDesc(){
        myBtn1.setTextColor(btnDefaultColor);
        myBtn2.setTextColor(btnDefaultColor);
        myBtn3.setTextColor(btnDefaultColor);
        myBtn4.setTextColor(btnDefaultColor);
        if(questionCounter < questionCountTotal){
            currentDescription = descriptionList.get(questionCounter);
            textViewDescription.setText(currentDescription.getDescription());
            vec = new Vector<>();
            vec.add(currentDescription.getOption1());
            vec.add(currentDescription.getOption2());
            vec.add(currentDescription.getOption3());
            vec.add(currentDescription.getOption4());
            Collections.shuffle(vec);
            Random random = new Random();
            myBtn1.setText( vec.get(0));
            myBtn2.setText(vec.get(1));
            myBtn3.setText(vec.get(2));
            myBtn4.setText(vec.get(3));
            questionCounter++;
            textViewDescriptionCount.setText(questionCounter + "/" + questionCountTotal);
            answered = false;

            System.out.println("T1 "+ myBtn1.getText());
            System.out.println("T2 "+ myBtn2.getText());
            System.out.println("T3 "+ myBtn3.getText());
            System.out.println("T4 "+ myBtn4.getText());


            //mybtnConfirm.setText("Confirm");
        }
        else {
            finishIt();
        }
    }
    public void checkAnswer(){
        answered = true;

        for(final Button bt:buttonList){
            for(int i=0;i<vec.size();i++){
                if(bt.getText()==vec.get(i)){
                    score++;
                   // questionCounter++;
                    textViewScore.setText( ""+ score);
                    Toast.makeText(this, "you Scored", LENGTH_SHORT).show();
                }
                else{
                   // showSolution();
                    Toast.makeText(this, "Did not get", LENGTH_SHORT).show();
                }
            }
        }
    }
    private void showSolution(){
//        myBtn1.setBackgroundColor(Integer.parseInt("#900C3F"));
//        myBtn2.setBackgroundColor(Integer.parseInt("#900C3F"));
//        myBtn3.setBackgroundColor(Integer.parseInt("#900C3F"));
//        myBtn4.setBackgroundColor(Integer.parseInt("#900C3F"));
    }
    public void finishIt(){
        finish();
    }
    public void startTimer(final TextView mTextView) {
         this.mTextViewCountDown=mTextView;
         if(counter>0) {
             mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                 @Override
                 public void onTick(long millisUntilFinished) {
                     mTimeLeftInMillis = millisUntilFinished;
                     counter--;
                     if(counter>0) {
                         mTextViewCountDown.setText( ": "+String.valueOf(counter));
                     }
                     if(counter==0){

                         mTextViewCountDown.setText("Finished");
                         mTextViewCountDown.setBackgroundColor(Color.parseColor("#C70039 "));

                     }
                 }
                 @Override
                 public void onFinish() {
                     mTextViewCountDown.setText("Finished!");
                 }
             }.start();
         }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void toFadeIn(View view) {
//        Button bt = (Button)findViewById(R.id.bt1);
//       Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade);
//       bt.startAnimation(animation);
    }
}
