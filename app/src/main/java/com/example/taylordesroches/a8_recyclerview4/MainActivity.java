package com.example.taylordesroches.a8_recyclerview4;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String [] people = {
            "Lianne",
            "Garth",
            "Evan",
            "Bill",
            "Tony",
            "Brian",
            "Jim",
            "Janice"};

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    TextView textViewDisplay;
    ArrayList<Person> personArrayList;
    // define in class
    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById( R.id.recyclerView );
        textViewDisplay = findViewById( R.id.textViewDisplay);
        recyclerViewManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( recyclerViewManager );
        recyclerView.setHasFixedSize( true );
        personArrayList = new ArrayList<Person>();

        // database
        db = new DBAdapter(this);
        // get the existing database file or from the assets folder if doesn't exist
        try
        {
            String destPath = "data/data/"+getPackageName()+"/databases";
            File f = new File(destPath);
            if(!f.exists()){
                f.mkdirs();
                //copy db from assets folder
                CopyDB(getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath + "/MyDB"));
            }
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        db.open();

        //커서 : 데이터베이스를 iterate 할수 있게해준다, moveToFirst(), moveToNext()등등
        Cursor c;
        //커서에 데이터베이스 바인드
        c = db.getAllContacts();
        if(c.moveToFirst())
        {
            do{
                // put database item into an ArrayList for the RecyclerView
                personArrayList.add(new Person(c.getInt(0),c.getString(1),
                        c.getString(2),c.getString(3)));
            } while(c.moveToNext());
        }

        // ************************************************************
        //new code added to load in initial contacts                  *
        // ************************************************************
        else
        {
            int count = 0;

            Person Lianne = new Person(count, "Lianne", "lwong@fan.ca", "28");
            db.insertContact(Lianne.getsName_(), Lianne.getsEmail_(), parseInt(Lianne.getsAge_()));
            personArrayList.add(Lianne);

            Person Taylor = new Person(count, "Taylor", "tdes@fan.ca", "59");
            db.insertContact(Taylor.getsName_(), Taylor.getsEmail_(), parseInt(Taylor.getsAge_()));
            personArrayList.add(Taylor);

            Person Bill = new Person(count, "Bill", "pulling@fan.ca", "21");
            db.insertContact(Bill.getsName_(), Bill.getsEmail_(), parseInt(Bill.getsAge_()));
            personArrayList.add(Bill);
        }

        db.close();

        recyclerViewAdapter = new MyAdapter( getApplicationContext(),personArrayList );
        recyclerView.setAdapter( recyclerViewAdapter );

    }

    // copy database from assets to phone
    // items in the assets folder preserve filename
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //Copy one byte at a time
        byte [] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }

    // get Person from Database and display in TextView
    public String DisplayContact(Cursor e)
    {
        String msg = "id:" + e.getString(0) + "\n" +
                "Name: " + e.getString(1) + "\n" +
                "Email: " + e.getString(2) + "\n" +
                "Age: " + e.getString(3) + "\n";
        // show contact in toast message and textView
        TextView textView = findViewById(R.id.textViewDisplay);
        textView.setText(msg);
        return msg;
    }

    public void onButtonClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonTotal:
                textViewDisplay.setText( getString(R.string.thetotal) + String.valueOf(recyclerView.getAdapter().getItemCount()));
                break;
            case R.id.buttonFirst:
//                textViewDisplay.setText( getString(R.string.thefirst) + people[0]);
                textViewDisplay.setText(getString(R.string.thefirst) + personArrayList.get(0).toString());
                break;
            case R.id.buttonSecond:
//                textViewDisplay.setText( getString(R.string.thesecond) + people[1]);
                textViewDisplay.setText(getString(R.string.thesecond) + personArrayList.get(1).toString());
                break;
        }
    }
}