package com.example.munchkincompanion;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private TextView combatPowerView;
    private int combat_power;
    private int mode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.combat_power = 0;
        MainActivity root = (MainActivity) getActivity();
        this.mode =root.mode;
        this.mode = 1;
        this.combatPowerView = (TextView) rootView.findViewById(R.id.CombatPowerView);
        this.combatPowerView.setText(String.valueOf(this.combat_power));
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (this.mode == 1){
            builder.setMessage(R.string.scanCard).setTitle(R.string.nfcAddCard);
        }else if(this.mode == 2){
            builder.setMessage(R.string.picAdd).setTitle(R.string.addCard);
        }else if (this.mode == 3){
            LayoutInflater inflater = getActivity().getLayoutInflater();
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
