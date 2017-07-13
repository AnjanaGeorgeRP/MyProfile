package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    CheckBox ckbLike;


    @Override
    protected void onPause() {
        super.onPause();
        String strName = etName.getText().toString();
        Float gpa = Float.parseFloat(etGPA.getText().toString());
        boolean check = ckbLike.isChecked();
        int radioCheck = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit(); //for update later
        prefEdit.putString("name",strName); //Add key-vallue pair
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putBoolean("check", check);
        prefEdit.putInt("checkRadio",radioCheck);
        prefEdit.commit(); //save changes into shared preferences
    }

    //Test a commit 
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strName = prefs.getString("name","John");
        Float gpa = prefs.getFloat("gpa",0);
        String strGpa = String.valueOf(gpa);
        boolean check = prefs.getBoolean("check",false);
        int radioCheck = prefs.getInt("checkRadio",-1);
        etName.setText(strName);
        etGPA.setText(strGpa);
        ckbLike.setChecked(check);
        rgGender.check(radioCheck);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.editTextName);
        etGPA = (EditText)findViewById(R.id.editTextGpa);
        rgGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        ckbLike = (CheckBox) findViewById(R.id.checkBoxLikeProgramming);

    }
}
