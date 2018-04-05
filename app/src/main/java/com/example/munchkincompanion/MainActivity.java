package com.example.munchkincompanion;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  private TextView combatPowerView;
  private int combat_power;
  private int mode;
  private Fragment fragment;
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);

          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_cards);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          fragment = new NotificationFragment();
            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    this.combat_power = 0;
    this.combatPowerView = (TextView) findViewById(R.id.CombatPowerView);
    this.combatPowerView.setText(String.valueOf(this.combat_power));
    //SharedPreferences loadprefs = getSharedPreferences("ModeFile", MODE_PRIVATE);
    this.mode = 1;
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_modes, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    //SharedPreferences.Editor sharedPrefs = getSharedPreferences("ModeFile",MODE_PRIVATE).edit();
    Button addButton = findViewById(R.id.button);
    if (id == R.id.mode1) {
      //sharedPrefs.putInt("mode",1);
      this.mode = 1;
      addButton.setText(R.string.scanCard);
    }if (id == R.id.mode2) {
      //sharedPrefs.putInt("mode",2);
      this.mode = 2;
      addButton.setText(R.string.addCard);
    }if (id == R.id.mode3) {
      //sharedPrefs.putInt("mode",3);
      this.mode = 3;
      addButton.setText(R.string.addCard);
    }
    //sharedPrefs.apply();
    return super.onOptionsItemSelected(item);
  }

  public void increaseCombatPower(View view) {
    this.combat_power++;
    combatPowerView.setText(String.valueOf(this.combat_power));
  }
  public void decreaseCombatPower(View view) {
    this.combat_power--;
    combatPowerView.setText(String.valueOf(this.combat_power));
  }
  public void add(View view){
    //SharedPreferences loadprefs = getSharedPreferences("ModeFile", MODE_PRIVATE);
    //this.mode = loadprefs.getInt("mode", 1);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
        /*
        switch (this.mode){
            case 1:
                builder.setMessage(R.string.scanCard).setTitle(R.string.nfcAddCard);
            case 2:
                builder.setMessage(R.string.numAdd).setTitle(R.string.addCard);
            case 3:
                builder.setMessage(R.string.picAdd).setTitle(R.string.addCard);
        }
        */
    if (this.mode == 1){
      builder.setMessage(R.string.scanCard).setTitle(R.string.nfcAddCard);
    }else if(this.mode == 2){
      builder.setMessage(R.string.picAdd).setTitle(R.string.addCard);
    }else if (this.mode == 3){
      LayoutInflater inflater = this.getLayoutInflater();
      builder.setTitle(R.string.addCard);
      builder.setView(inflater.inflate(R.layout.dialog_3, null));
    }

    builder.setCancelable(true).setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
      }
    });
    builder.setCancelable(true).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }
}