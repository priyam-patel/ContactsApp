package com.priyampatel.cs478hw1.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private static Button addButton;
    public static EditText inputTxt;
    private static TextView readOnly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputTxt = (EditText) findViewById(R.id.editText);
        readOnly = (TextView) findViewById(R.id.textView);

        addButton = (Button) findViewById(R.id.contactbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonAction(v);
            }
        });

    }

    //add flag that indicated exit on save when edit contact information is being saved, look at slides,
    //code snippet where edit contact data is being saved in that manner
    public void buttonAction(View v) {
        final String fullName = inputTxt.getText().toString();
        readOnly = (TextView) findViewById(R.id.textView);
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, fullName);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        if(checkInput(fullName) == true) {
        startActivityForResult(intent, 1); }
        else { readOnly.setText("Invalid Input: Enter full name"); }
        }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
       readOnly = (TextView) findViewById(R.id.textView);
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                readOnly.setText("Success! Contact Saved!");
            }
        }
    }

    public boolean checkInput(String fullName) {

        String[] names = fullName.split(" ");

        int numNames = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                numNames++;
            }
        }

        if (numNames == 2) return true;
        else return false;

    }

}