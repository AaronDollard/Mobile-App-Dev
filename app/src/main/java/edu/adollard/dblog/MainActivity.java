package edu.adollard.dblog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);

        //db.emptyContacts();
        //Empty table if required

        // Inserting Contacts
        Log.i("Insert: ", "Inserting...");
        //db.addContact(new Contact("Joe", "0873456789"));
        //db.addContact(new Contact("Adam", "0863111122"));
        //db.addContact(new Contact("Mary", "0859999888"));
        //db.addContact(new Contact("Kelly", "083334444"));
        //db.addContact(new Contact("Tony", "0831112222"));
        //db.addContact(new Contact("Joe", "0835554444"));
        // Reading all contacts
        Log.i("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.i("Name: ", log);
        }

        Log.i("divider", "====================");
        Contact singleUser = db.getContact(5);
        Log.i("contact 5 is ", singleUser.getName());
        Log.i("divider", "====================");
        // Calling SQL statement
        int userCount = db.getContactsCount();
        Log.i("User count: ", String.valueOf(userCount));

        Log.i("divider", "====================");
        Log.i("Reading: ", "Reading Joes in Database");
        List<Contact> ListOfJoes = db.getJoes("Joe");
        for (Contact Joe : ListOfJoes) {
            String Jogger = "Id: " + Joe.getID()
                    + " ,Name: " + Joe.getName()
                    + " ,Phone: " + Joe.getPhoneNumber();
            // Writing Contacts to log
            Log.i("Name: ", Jogger);
        }
    }
}
