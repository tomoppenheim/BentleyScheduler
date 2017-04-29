package com.example.tuffy_josh.termproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class scheduleViewer extends AppCompatActivity {

    TextView B1A, B1B, B2A, B2B, B3A, B3B, B4A, B4B, B5A, B5B, B6A, B6B, B7A, B7B, B8A, B8B, B9A, B9B, B10A, B10B, B11A, B11B, B12A, B12B, B13A, B13B, B14A, B14B, B15A, B15B, B16A, B16B, ME, TE, WE, THE;

    private SQLiteDatabase db;
    private ContentValues values;
    private Cursor cursor;
    private Button Test;
    Schedule scheduleAttempt = new Schedule();
    BroadcastReceiver receiver;
    private NotificationManager mNotificationManager;
    private Notification notifyDetails;

    public static final String DATABASE_NAME = "classes.db";
    public static final String CREATE_TABLE = "CREATE TABLE LISTINGS (\n" +
            "      ClassID VarChar(8) NOT NULL,\n" +
            "      SectionID Varchar(4) NOT NULL,\n" +
            "      BlockPeriod Number(1) NOT NULL,\n" +
            "CONSTRAINT LISTINGS_PK primary key (ClassID, SectionID));\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_viewer);

        db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Button test = (Button) findViewById(R.id.generate);

        test.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Bundle extras = getIntent().getExtras();
                    String class1 = extras.getString("Class1");
                    String class2 = extras.getString("Class2");
                    String class3 = extras.getString("Class3");
                    String class4 = extras.getString("Class4");
                    String class5 = extras.getString("Class5");
                    String class6 = extras.getString("Class6");

                    ArrayList<String> classTest = new ArrayList<String>();
                    if (class1 != null) {
                        classTest.add(class1);
                    }
                    if (class2 != null) {
                        classTest.add(class2);
                    }
                    if (class3 != null) {
                        classTest.add(class3);
                    }
                    if (class4 != null) {
                        classTest.add(class4);
                    }
                    if (class5 != null) {
                        classTest.add(class5);
                    }
                    if (class6 != null) {
                        classTest.add(class6);
                    }

                    doStuff(classTest);
                }catch(Exception e){};
            }
        });


        db.execSQL("DROP TABLE IF EXISTS LISTINGS");
        db.execSQL(CREATE_TABLE);


        //Accounting
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 310', '001', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 310', '002', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 310', '003', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 310', '004', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 310', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', '001', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', '002', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', '003', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', '004', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', 'E01', 18);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', 'H01', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 311', 'H02', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 312', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 312', '002', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 312', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 331', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 332', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 340', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 340', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 340', '003', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 340', '004', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 340', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 350', '001', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 350', '002', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 350', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 381', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 412', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 412', '002', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 412', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 470', '001', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 470', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('AC 475', 'E01', 19);");

        //CIS
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '002', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '004', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '005', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '006', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', '007', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 150', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 180', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 180', '002', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 180', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 180', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 213', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 213', '002', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 213', '003', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 213', '004', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 240', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 280', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 350', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 350', '002', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 350', '003', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 360', '001', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 360', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CS 460', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '002', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '004', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '005', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '006', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '007', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '008', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '009', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '010', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '011', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '012', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '013', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '014', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '015', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '016', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', '017', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'E01', 19);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'H01', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'H02', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'SL1', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'SL2', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IT 101', 'X01', 13);");

        //ECON
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '004', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '005', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '006', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '007', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '008', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '009', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '010', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', '011', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', 'E01', 20);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', 'H01', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', 'H02', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 111', 'H03', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', '002', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', '003', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', '004', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', '005', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 112', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 224', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 224', '002', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 224', 'E01', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 224', 'E02', 19);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 224', 'H01', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 225', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 225', '002', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 225', '003', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 225', 'H01', 2);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 245', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 251', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 270', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 271', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 275', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 311', '001', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 321', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 333', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 361', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 381', '001', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 381', '002', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 391', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 391', '002', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EC 391', 'E01', 19);");


        //General Business
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '002', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '003', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '004', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '005', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '006', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '007', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '008', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '009', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '010', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '011', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '012', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '013', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '014', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '015', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '016', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', '017', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', 'C01', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', 'C02', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', 'E01', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 110', 'H01', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '002', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '003', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '004', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '005', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '006', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '007', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '008', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '009', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '010', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '011', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '012', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '013', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '014', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '015', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '017', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '018', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '019', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '020', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '021', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '022', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '023', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '024', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '026', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '027', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '028', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '030', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '031', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', '032', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', 'C03', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', 'C08', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 112', 'C10', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 212', '001', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 212', '002', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 212', '003', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 212', '004', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '002', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '005', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '007', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '008', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '009', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '010', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '011', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '012', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', '013', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', 'E01', 20);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', 'H01', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 213', 'H02', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '002', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '004', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '005', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '006', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '007', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '008', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '009', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '010', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '011', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '012', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '013', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', '014', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', 'E01', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', 'E02', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', 'H01', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 214', 'H02', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '002', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '003', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '004', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '005', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '006', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '007', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '008', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '009', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '010', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '011', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '012', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '013', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '014', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '015', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', '016', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 215', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '002', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '004', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '005', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '006', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '007', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '008', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '009', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '010', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '011', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '013', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', '014', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', 'EB1', 20);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', 'EB2', 19);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 310', 'R12', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '003', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '004', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '005', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '006', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '007', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '008', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', '009', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', 'EB1', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GB 410', 'EB3', 20);");


        //English
        db.execSQL("INSERT INTO LISTINGS VALUES ('CIN 370', '001', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CIN 370', '002', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('CIN 370', '003', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('CIN 381', '001', 16);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', '002', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', '004', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', '005', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 210', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 320', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('COM 321', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EMS 200', 'H01', 1);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EMS 201', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '002', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '003', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '004', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '005', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '006', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '007', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '008', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '009', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '010', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '011', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '012', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '013', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '014', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '015', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '016', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '017', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '018', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', '019', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 101', 'H01', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 102', '002', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 102', '003', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 102', '004', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 102', 'C05', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '002', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '003', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '004', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '005', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '006', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '007', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '008', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '009', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '010', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '011', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '012', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '013', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '014', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', '015', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', 'E01', 18);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 201', 'H01', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('EXP 202', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 216', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 230', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 232', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 232', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 311', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 313', '001', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 314', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 356', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 363', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 366', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 371', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LIT 391', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 220', '001', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 222', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 224', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 320', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 320', '002', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 341', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MC 342', '001', 12);");


        //Finance
        db.execSQL("INSERT INTO LISTINGS VALUES ('AF 450', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('AF 450', '002', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '001', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '003', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '004', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '005', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 305', '006', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '001', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '002', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '003', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '004', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '005', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', '006', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', 'E01', 20);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 306', 'H01', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', '001', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', '002', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', '004', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', '005', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', 'E01', 18);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 307', 'H01', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 312', '001', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 312', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 315', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 315', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 318', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 325', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 331', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 331', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 335', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 345', '001', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 345', '002', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 347', '001', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 347', '002', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 347', '003', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', '003', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', '004', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', '005', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 351', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 360', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 360', '002', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 362', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 372', '001', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 372', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('FI 399', '001', 11);");


        //GLS
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', '001', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', '004', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', '005', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', '006', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', 'C09', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 100', 'H01', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 101', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 101', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '002', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '004', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '005', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '006', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '007', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '008', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '009', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '010', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '011', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', '012', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 102', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 105', '001', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 105', '002', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 105', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 105', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 110', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 110', '002', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 114', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 114', '002', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 116', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 116', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 116', '003', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 230', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 238', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 243', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 262', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 299', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('GLS 312', '001', 18);");


        //History
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 200', '001', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 200', '002', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 200', '003', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 200', '004', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 200', '005', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 261', '001', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 266', '001', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 279', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 306', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 315', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 356', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 357', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 359', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 370', '001', 2);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 381', '001', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 382', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 385', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 388', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 392', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 395', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 395', '002', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('HI 395', 'H01', 11);");


        //IDCC
        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 230', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 250', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 255', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 320', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 320', '002', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 320', 'E01', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 355', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 360', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 365', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 370', '001', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 370', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 375', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IDCC 390', '002', 8);");


        //IPM
        db.execSQL("INSERT INTO LISTINGS VALUES ('IPM 210', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('IPM 450', '001', 11);");


        //Law
        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 101', '001', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 101', 'H01', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 105', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 106', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 210', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 302', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 308', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 317', '001', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 318', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('LA 402', '001', 12);");


        //Management
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 225', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 228', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 240', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 240', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 240', '003', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 240', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 241', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 242', 'H01', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 250', '001', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 250', '002', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 315', '001', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 316', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 331', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 331', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 332', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 334', 'H01', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 335', '001', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 335', '002', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 335', '003', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 335', 'E02', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 336', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 337', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 340', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 340', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 343', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 345', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 345', '002', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 345', '003', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 345', '004', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 351', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 352', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 360', '001', 16);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 360', 'E01', 20);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 360', 'H01', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MG 365', 'E01', 19);");


        //Marketing
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 321', '001', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 321', '002', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 322', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 322', '002', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 322', '003', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 322', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 340', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 342', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 344', '001', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 361', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 365', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 367', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 369', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 400', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 400', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 400', '003', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 400', '004', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MK 411', 'E01', 17);");


        //Mathematics
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '003', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '004', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '005', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '006', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '007', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '008', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '009', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '010', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '011', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '012', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '013', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '014', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', '015', 3);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', 'E01', 17);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', 'E02', 18);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', 'E03', 19);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 123', 'E04', 20);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 126', '001', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 126', '002', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 126', '003', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 126', '004', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '002', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '003', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '004', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '005', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '006', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '007', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '008', 10);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 131', '009', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 139', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 139', '002', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 139', '003', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 139', '004', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 139', '005', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 233', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 233', '002', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 233', '003', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 239', '001', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 243', '001', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 243', '002', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 243', 'H01', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 252', '001', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 252', '002', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 263', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 263', '002', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 343', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 347', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 375', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MA 402', '001', 9);");


        //Language
        db.execSQL("INSERT INTO LISTINGS VALUES ('MLCH 101', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLCH 201', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLCH 404', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLFR 101', '001', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MLFR 101', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLFR 201', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLFR 402', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLIT 101', '001', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MLIT 101', '002', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLIT 201', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLIT 304', '001', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLJA 101', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLJA 201', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 101', '001', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 101', '002', 5);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 102', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 201', '001', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 201', '002', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 202', '001', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 203', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 301', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('MLSP 406', '001', 10);");


        //Natural Sciences
        db.execSQL("INSERT INTO LISTINGS VALUES ('NASC 111', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 305', '001', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 308', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 313', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 315', '001', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 315', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 317', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 319', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 319', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 328', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 335', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 339', '001', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 342', '001', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 350', '001', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 364', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('NASE 398', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 210', '001', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 210', '002', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 230', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 240', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 252', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 252', '002', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 266', '001', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 266', '002', 14);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 275', '001', 4);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 305', '001', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 311', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 311', '002', 9);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 325', 'H01', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 333', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 380', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 380', '002', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 388', '001', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PS 388', '002', 9);");


        //Philosophy
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '003', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '004', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '005', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '006', 11);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '007', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '008', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '009', 2);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '010', 14);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '011', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '012', 7);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '013', 9);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '014', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '015', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '016', 15);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', '017', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', 'H01', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 101', 'H02', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 133', 'E01', 19);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 134', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 251', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PH 351', '001', 4);");


        //Professional Sales
        db.execSQL("INSERT INTO LISTINGS VALUES ('PRS 339', 'E01', 17);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PRS 343', '001', 12);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('PRS 373', 'E01', 20);");


        //Sociology
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '001', 1);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '002', 4);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '003', 6);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '004', 8);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '005', 12);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '006', 13);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 132', '007', 15);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 241', '001', 8);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 244', '001', 13);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 264', 'H01', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 285', '001', 5);");
        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 285', '002', 7);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 289', 'E01', 18);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 292', '001', 10);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 298', '001', 3);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 299', '001', 11);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 300', '001', 6);");

        db.execSQL("INSERT INTO LISTINGS VALUES ('SO 320', '001', 12);");

    }

    public void setB1(String in) {
        TextView B1A = (TextView) findViewById(R.id.B1A);
        TextView B1B = (TextView) findViewById(R.id.B1B);

        B1A.setText(in);
        B1B.setText(in);
    }

    public void setB2(String in) {
        TextView B2A = (TextView) findViewById(R.id.B2A);
        TextView B2B = (TextView) findViewById(R.id.B2B);

        B2A.setText(in);
        B2B.setText(in);
    }

    public void setB3(String in) {
        TextView B3A = (TextView) findViewById(R.id.B3A);
        TextView B3B = (TextView) findViewById(R.id.B3B);

        B3A.setText(in);
        B3B.setText(in);
    }

    public void setB4(String in) {
        TextView B4A = (TextView) findViewById(R.id.B4A);
        TextView B4B = (TextView) findViewById(R.id.B4B);

        B4A.setText(in);
        B4B.setText(in);
    }

    public void setB5(String in) {
        TextView B5A = (TextView) findViewById(R.id.B5A);
        TextView B5B = (TextView) findViewById(R.id.B5B);

        B5A.setText(in);
        B5B.setText(in);
    }

    public void setB6(String in) {
        TextView B6A = (TextView) findViewById(R.id.B6A);
        TextView B6B = (TextView) findViewById(R.id.B6B);

        B6A.setText(in);
        B6B.setText(in);
    }

    public void setB7(String in) {
        TextView B7A = (TextView) findViewById(R.id.B7A);
        TextView B7B = (TextView) findViewById(R.id.B7B);

        B7A.setText(in);
        B7B.setText(in);
    }

    public void setB8(String in) {
        TextView B8A = (TextView) findViewById(R.id.B8A);
        TextView B8B = (TextView) findViewById(R.id.B8B);

        B8A.setText(in);
        B8B.setText(in);
    }

    public void setB9(String in) {
        TextView B9A = (TextView) findViewById(R.id.B9A);
        TextView B9B = (TextView) findViewById(R.id.B9B);

        B9A.setText(in);
        B9B.setText(in);
    }

    public void setB10(String in) {
        TextView B10A = (TextView) findViewById(R.id.B10A);
        TextView B10B = (TextView) findViewById(R.id.B10B);

        B10A.setText(in);
        B10B.setText(in);
    }

    public void setB11(String in) {
        TextView B11A = (TextView) findViewById(R.id.B11A);
        TextView B11B = (TextView) findViewById(R.id.B11B);

        B11A.setText(in);
        B11B.setText(in);
    }

    public void setB12(String in) {
        TextView B12A = (TextView) findViewById(R.id.B12A);
        TextView B12B = (TextView) findViewById(R.id.B12B);

        B12A.setText(in);
        B12B.setText(in);
    }

    public void setB13(String in) {
        TextView B13A = (TextView) findViewById(R.id.B13A);
        TextView B13B = (TextView) findViewById(R.id.B13B);

        B13A.setText(in);
        B13B.setText(in);
    }

    public void setB14(String in) {
        TextView B14A = (TextView) findViewById(R.id.B14A);
        TextView B14B = (TextView) findViewById(R.id.B14B);

        B14A.setText(in);
        B14B.setText(in);
    }

    public void setB15(String in) {
        TextView B15A = (TextView) findViewById(R.id.B15A);
        TextView B15B = (TextView) findViewById(R.id.B15B);

        B15A.setText(in);
        B15B.setText(in);
    }

    public void setB16(String in) {
        TextView B16A = (TextView) findViewById(R.id.B16A);
        TextView B16B = (TextView) findViewById(R.id.B16B);

        B16A.setText(in);
        B16B.setText(in);
    }

    public void setME(String in) {
        TextView ME = (TextView) findViewById(R.id.ME);

        ME.setText(in);
    }

    public void setTE(String in) {
        TextView TE = (TextView) findViewById(R.id.TE);

        TE.setText(in);
    }

    public void setWE(String in) {
        TextView WE = (TextView) findViewById(R.id.WE);

        WE.setText(in);
    }

    public void setTHE(String in) {
        TextView THE = (TextView) findViewById(R.id.THE);

        THE.setText(in);
    }


    public boolean testBuild(ArrayList<String> inClasses) {

        scheduleAttempt.CB1 = null;
        scheduleAttempt.CB2 = null;
        scheduleAttempt.CB3 = null;
        scheduleAttempt.CB4 = null;
        scheduleAttempt.CB5 = null;
        scheduleAttempt.CB6 = null;
        scheduleAttempt.CB7 = null;
        scheduleAttempt.CB8 = null;
        scheduleAttempt.CB9 = null;
        scheduleAttempt.CB10 = null;
        scheduleAttempt.CB11 = null;
        scheduleAttempt.CB12 = null;
        scheduleAttempt.CB13 = null;
        scheduleAttempt.CB14 = null;
        scheduleAttempt.CB15 = null;
        scheduleAttempt.CB16 = null;
        scheduleAttempt.CB17 = null;
        scheduleAttempt.CB18 = null;
        scheduleAttempt.CB19 = null;
        scheduleAttempt.CB20 = null;

        boolean complete = true;

        outerloop: for (String q: inClasses) {

            boolean classAssigned = false;

            String[] classes = {q};
            Log.i("Searching Class",q);
            cursor = db.query("LISTINGS", new String[]{
                            "ClassID", "SectionID", "BlockPeriod"}, "ClassID=?", classes, null, null,
                    "ClassID");

            while (cursor.moveToNext() && classAssigned==false) {
                String classID = cursor.getString(cursor.getColumnIndex("ClassID"));
                String sectionID = cursor.getString(cursor.getColumnIndex("SectionID"));
                int blockPeriod = cursor.getInt(cursor.getColumnIndex("BlockPeriod"));

                Schedule.ClassBlock b1 = scheduleAttempt. new ClassBlock(classID, sectionID, blockPeriod);
                Log.i("Section Found",b1.toString() + " " + blockPeriod);



                switch (blockPeriod) {
                    case 1: if (scheduleAttempt.CB1 == null) {
                        scheduleAttempt.CB1 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 2: if (scheduleAttempt.CB2 == null) {
                        scheduleAttempt.CB2 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 3: if (scheduleAttempt.CB3 == null) {
                        scheduleAttempt.CB3 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 4:
                        if (scheduleAttempt.CB4 == null) {
                            scheduleAttempt.CB4 = b1;
                            classAssigned = true;
                        }
                        break;
                    case 5: if (scheduleAttempt.CB5 == null) {
                        scheduleAttempt.CB5 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 6: if (scheduleAttempt.CB6 == null) {
                        scheduleAttempt.CB6 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 7: if (scheduleAttempt.CB7 == null) {
                        scheduleAttempt.CB7 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 8: if (scheduleAttempt.CB8 == null) {
                        scheduleAttempt.CB8 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 9: if (scheduleAttempt.CB9 == null) {
                        scheduleAttempt.CB9 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 10: if (scheduleAttempt.CB10 == null) {
                        scheduleAttempt.CB10 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 11: if (scheduleAttempt.CB11 == null) {
                        scheduleAttempt.CB11 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 12: if (scheduleAttempt.CB12 == null) {
                        scheduleAttempt.CB12 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 13: if (scheduleAttempt.CB13 == null) {
                        scheduleAttempt.CB13 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 14: if (scheduleAttempt.CB14 == null) {
                        scheduleAttempt.CB14 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 15: if (scheduleAttempt.CB15 == null) {
                        scheduleAttempt.CB15 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 16: if (scheduleAttempt.CB16 == null) {
                        scheduleAttempt.CB16 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 17: if (scheduleAttempt.CB17 == null) {
                        scheduleAttempt.CB17 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 18: if (scheduleAttempt.CB18 == null) {
                        scheduleAttempt.CB18 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 19: if (scheduleAttempt.CB19 == null) {
                        scheduleAttempt.CB19 = b1;
                        classAssigned = true;
                    }
                        break;
                    case 20: if (scheduleAttempt.CB20 == null) {
                        scheduleAttempt.CB20 = b1;
                        classAssigned = true;
                    }
                        break;
                }




            }
            ;

            if (classAssigned == false) {

                complete = false;
            }


        }




        return complete;
    }

    public void SetView() {

        //Log.i("test22",scheduleAttempt.CB1.toString());

        if (scheduleAttempt.CB1 != null) setB1(scheduleAttempt.CB1.toString());
        else setB1("");
        if (scheduleAttempt.CB2 != null) setB2(scheduleAttempt.CB2.toString());
        else setB2("");
        if (scheduleAttempt.CB3 != null) setB3(scheduleAttempt.CB3.toString());
        else setB3("");
        if (scheduleAttempt.CB4 != null) setB4(scheduleAttempt.CB4.toString());
        else setB4("");
        if (scheduleAttempt.CB5 != null) setB5(scheduleAttempt.CB5.toString());
        else setB5("");
        if (scheduleAttempt.CB6 != null) setB6(scheduleAttempt.CB6.toString());
        else setB6("");
        if (scheduleAttempt.CB7 != null) setB7(scheduleAttempt.CB7.toString());
        else setB7("");
        if (scheduleAttempt.CB8 != null) setB8(scheduleAttempt.CB8.toString());
        else setB8("");
        if (scheduleAttempt.CB9 != null) setB9(scheduleAttempt.CB9.toString());
        else setB9("");
        if (scheduleAttempt.CB10 != null) setB10(scheduleAttempt.CB10.toString());
        else setB10("");
        if (scheduleAttempt.CB11 != null) setB11(scheduleAttempt.CB11.toString());
        else setB11("");
        if (scheduleAttempt.CB12 != null) setB12(scheduleAttempt.CB12.toString());
        else setB12("");
        if (scheduleAttempt.CB13 != null) setB13(scheduleAttempt.CB13.toString());
        else setB13("");
        if (scheduleAttempt.CB14 != null) setB14(scheduleAttempt.CB14.toString());
        else setB14("");
        if (scheduleAttempt.CB15 != null) setB15(scheduleAttempt.CB15.toString());
        else setB15("");
        if (scheduleAttempt.CB16 != null) setB16(scheduleAttempt.CB16.toString());
        else setB16("");
        if (scheduleAttempt.CB17 != null) setME(scheduleAttempt.CB17.toString());
        else setME("");
        if (scheduleAttempt.CB18 != null) setTE(scheduleAttempt.CB18.toString());
        else setTE("");
        if (scheduleAttempt.CB19 != null) setWE(scheduleAttempt.CB19.toString());
        else setWE("");
        if (scheduleAttempt.CB20 != null) setTHE(scheduleAttempt.CB20.toString());
        else setTHE("");

    }


    public void buildNotification(String buildData) {

        //Built notification here so this method can be called when the notification needs to be built. Couldn't build in receiver class. Couldn't have it pre-built either.

        notifyDetails = new Notification.Builder(getApplicationContext())

                .setContentTitle(buildData)
                .setContentText("Thanks for playing")
                .setVibrate(new long[] {1000, 1000, 1000, 1000})
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon)
                .build();
    }


    public void doStuff(ArrayList<String> input) {


        if (testBuild(input)) {
            buildNotification("Build Successful");
            SetView();
        }
        else {
            SetView();
            buildNotification("Build Unsuccessful");
            Toast.makeText(scheduleViewer.this, "Unable to generate schedule", Toast.LENGTH_SHORT).show();
        }

        mNotificationManager.notify(0, notifyDetails);
    }


}

