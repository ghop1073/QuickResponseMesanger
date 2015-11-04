package ctec.quickresponsemesanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.telephony.SmsManager;
import java.util.ArrayList;


public class MessagerApp extends AppCompatActivity {

    private EditText smsMessageField;
    private EditText smsNumberField;
    private Button sendSMSButton;
    private ArrayList<String> quickSpinner;
    private Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager_app);

        smsMessageField = (EditText) findViewById(R.id.smsContentEditText);
        smsNumberField = (EditText) findViewById(R.id.smsNumberEditText);
        sendSMSButton = (Button) findViewById(R.id.sendSMSButton);
        mySpinner = (Spinner) findViewById(R.id.quickSpinner);

        quickSpinner = new ArrayList<String>();
        buildList();

        setupListeners();
    }

    private void buildList()
    {
        quickSpinner.add("Hello. How is your day going.");
        quickSpinner.add("What are you doing?");
        quickSpinner.add("Want to hang out?");
        quickSpinner.add("I will call you in a minute.");

        ArrayAdapter<String> myListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, quickSpinner);
        mySpinner.setAdapter(myListAdapter);


    }

    private void setupListeners(){

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> unused, View view, int position, long id) {
                smsMessageField.setText(mySpinner.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> unused) {

            }

        });
        sendSMSButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View currentView) {

                try {

                    String contact = smsNumberField.getText().toString();
                    String message = smsMessageField.getText().toString();
                    sendSMS(contact, message);

                    Toast.makeText(currentView.getContext(), "message was sent", Toast.LENGTH_SHORT).show();
                } catch (Exception currentException) {

                    Toast.makeText(currentView.getContext(), "message was not sent", Toast.LENGTH_LONG).show();
                    Toast.makeText(currentView.getContext(), currentException.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendSMS(String messageAddress, String messageContent){

        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(messageAddress, null, messageContent, null, null);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messager_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
