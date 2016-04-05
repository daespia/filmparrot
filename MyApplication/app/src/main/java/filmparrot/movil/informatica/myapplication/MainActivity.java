package filmparrot.movil.informatica.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button editButton, applyButton;
    private EditText iName, ipAddress, netAddress, gateAddress, dns1addr, dns2addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeUIComponents();
    }

    private void InitializeUIComponents(){

        iName = (EditText) findViewById(R.id.nameText);
        ipAddress = (EditText) findViewById(R.id.ipText);
        netAddress = (EditText) findViewById(R.id.netmaskText);
        gateAddress = (EditText) findViewById(R.id.gatewayText);
        dns1addr = (EditText) findViewById(R.id.dns1Text);
        dns2addr = (EditText) findViewById(R.id.dns2Text);

        SetCurrentConfiguration();
        EnableComponents(false);

        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editButton.getText().equals("EDIT")){
                    EnableComponents(true);
                    applyButton.setEnabled(true);
                    editButton.setText("CANCEL");
                    editButton.setBackgroundColor(Color.RED);

                } else {
                    SetCurrentConfiguration();
                    applyButton.setEnabled(false);
                    EnableComponents(false);
                    editButton.setText("EDIT");
                    editButton.setBackgroundColor(Color.GREEN);
                }
            }
        });

        applyButton = (Button) findViewById(R.id.apply_button);
        applyButton.setEnabled(false);
    }

    private void EnableComponents(boolean enabled){
        iName.setEnabled(enabled);
        ipAddress.setEnabled(enabled);
        netAddress.setEnabled(enabled);
        gateAddress.setEnabled(enabled);
        dns1addr.setEnabled(enabled);
        dns2addr.setEnabled(enabled);
    }

    private void SetCurrentConfiguration(){
        iName.setText("eth10");
        ipAddress.setText("192.10.20.30");
        netAddress.setText("192.10.0.0");
        gateAddress.setText("192.10.0.0");
        dns1addr.setText("192.10.0.0");
        dns2addr.setText("192.10.0.0");
    }

}
