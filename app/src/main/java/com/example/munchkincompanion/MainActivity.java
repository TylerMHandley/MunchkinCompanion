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
  public int mode;
  private Fragment fragment;
  private HomeFragment frag;
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          frag = new HomeFragment();
          final FragmentTransaction home_transaction = getFragmentManager().beginTransaction();
          home_transaction.replace(R.id.main_container, frag).commit();
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_cards);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText("");
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
    navigation.setSelectedItemId(R.id.navigation_home);
    this.mode = 1;
    /*this.combat_power = 0;
    this.combatPowerView = (TextView) findViewById(R.id.CombatPowerView);
    this.combatPowerView.setText(String.valueOf(this.combat_power));
    //SharedPreferences loadprefs = getSharedPreferences("ModeFile", MODE_PRIVATE);
    this.mode = 1;
    */
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

    if (id == R.id.mode1) {
      this.mode = 1;
      frag.setButtonText(R.string.scanCard);
    }if (id == R.id.mode2) {
      this.mode = 2;
      frag.setButtonText(R.string.addCard);
    }if (id == R.id.mode3) {
      this.mode = 3;
      frag.setButtonText(R.string.addCard);
    }
    //sharedPrefs.apply();
    return super.onOptionsItemSelected(item);
  }

  public void increaseCombatPower(View view) {
    frag.increaseCombatPower();
  }
  public void decreaseCombatPower(View view) {
    frag.decreaseCombatPower();
  }
  public void add(View view){
    frag.add();
  }


}