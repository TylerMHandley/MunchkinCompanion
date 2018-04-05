package com.example.munchkincompanion;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
public class HomeFragment extends Fragment{
    private TextView combatPowerView;
    private int combat_power;
    private int mode;
    private View root;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.root = rootView;
        this.combat_power = 0;
        this.combatPowerView = rootView.findViewById(R.id.CombatPowerView);
        this.combatPowerView.setText(String.valueOf(combat_power));
        this.mode = 1;
        this.button = (Button) rootView.findViewById(R.id.button);
        return rootView;
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
