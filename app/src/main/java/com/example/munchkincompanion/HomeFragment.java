package com.example.munchkincompanion;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
public class HomeFragment extends Fragment{
    public static final String PREFS_NAME = "CoreSkillsPrefsFile";
    private TextView combatPowerView;
    private int combat_power;
    private int mode;
    private View root;
    private Button button;
    static final int TAKE_PHOTO_PERMISSION = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        this.root = rootView;
        //if(sharedPreferences.contains("combat_power"))
        this.combat_power = sharedPreferences.getInt("combat_power", 0);
        this.combatPowerView = rootView.findViewById(R.id.CombatPowerView);
        this.combatPowerView.setText(String.valueOf(combat_power));
        this.mode = 1;
        this.button = (Button) rootView.findViewById(R.id.button);
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && mode == 2) {
            button.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[] { android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE }, TAKE_PHOTO_PERMISSION);
        }
        return rootView;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // This is called when permissions are either granted or not for the app
        // You do not need to edit this code.

        if (requestCode == TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                button.setEnabled(true);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK)  {
            Log.d("Added", "Card 'added'");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.picAdd).setTitle(R.string.addCard);
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor sharedPrefs = getActivity().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE).edit();
        sharedPrefs.putInt("combat_power", combat_power);
        sharedPrefs.apply();
    }
    public void setButtonText(int value){
        this.button.setText(value);
    }
    public void increaseCombatPower() {
        this.combat_power++;
        combatPowerView.setText(String.valueOf(this.combat_power));
    }
    public void decreaseCombatPower() {
        this.combat_power--;
        combatPowerView.setText(String.valueOf(this.combat_power));
    }
    public void add(){
        MainActivity root = (MainActivity) getActivity();
        this.mode = root.mode;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (this.mode == 1){
            builder.setMessage(R.string.nfcAddCard).setTitle(R.string.scanCard);
        }else if(this.mode == 2){
            builder.setMessage(R.string.picAdd).setTitle(R.string.addCard);
            Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            //We want the dialogue box to appear when the picture is taken but I don't want to edit Tyler's code a lot
            return;
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
