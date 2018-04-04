package com.example.munchkincompanion;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView combatPowerView;
    private int combat_power;
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

    }
    public void increaseCombatPower(View view) {
        this.combat_power++;
        combatPowerView.setText(String.valueOf(this.combat_power));
    }
    public void decreaseCombatPower(View view) {
        this.combat_power--;
        combatPowerView.setText(String.valueOf(this.combat_power));
    }
    public void nfcAdd(View view){
        //AddCardDialog dig = new AddCardDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.scanCard).setTitle(R.string.nfcAddCard).setCancelable(true)
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
