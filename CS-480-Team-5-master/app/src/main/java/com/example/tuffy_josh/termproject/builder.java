package com.example.tuffy_josh.termproject;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import  android.widget.ArrayAdapter;
import  android.widget.ListView;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import  android.widget.RadioGroup;
import  android.widget.RadioButton;
import  android.widget.RadioGroup.OnCheckedChangeListener;


public class builder extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private EditText dataEntry;
    String[] items = {};
    private TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);

        speaker = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.US);
                }
            }
        });

        dataEntry = (EditText)findViewById(R.id.editText);
        ListView listview = (ListView)findViewById(R.id.list);
        arrayList = new ArrayList<>(Arrays.asList(items));
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item, R.id.txtitem, arrayList);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = arrayList.get(position);
                dataEntry.setText(text);
            }
        });
    }

    public void speak(String output){
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "id 0");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1, 1, 1, "Enter");
        menu.add(1,2,2,"Delete");
        menu.add(1,3,3,"Clear");

        return true;
    }

    public void goClicked(View v){
        Intent goIntent = new Intent(this, scheduleViewer.class);

        for (int i = 0; i < arrayList.size(); i++){
            String course = arrayList.get(i);
            int n = i + 1;
            String classNumber = "Class" + n;
            goIntent.putExtra(classNumber,course);
            Log.i("intent",course);
        }
        startActivity(goIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                if (arrayList.size() < 6) {
                    String newEntry = dataEntry.getText().toString();
                    if (newEntry.contains(" ")){
                        dataEntry.setText("Please don't include a space");
                    }
                    else {
                        newEntry = newEntry.toUpperCase();
                        String numberEntry = newEntry.substring(newEntry.length() - 3, newEntry.length());
                        String classCodeEntry = newEntry.substring(0, newEntry.length() - 3);
                        String finalClass = classCodeEntry + " " + numberEntry;
                        arrayList.add(finalClass);
                        arrayAdapter.notifyDataSetChanged();
                        dataEntry.setText("");
                        String addSpeak = newEntry + " was added";
                        speak(addSpeak);
                    }
                }
            return true;

            case 2:
                String deleteEntry = dataEntry.getText().toString();
                deleteEntry = deleteEntry.toUpperCase();
                arrayList.remove(deleteEntry);
                arrayAdapter.notifyDataSetChanged();
                dataEntry.setText("");
                String deleteSpeak = deleteEntry + " was deleted";
                speak(deleteSpeak);
            return true;

            case 3:
                arrayList.clear();
                arrayAdapter.notifyDataSetChanged();
                String clearSpeak = "Schedule cleared";
                speak(clearSpeak);

            default: super.onOptionsItemSelected(item);
        }
        return false;
    }

}
