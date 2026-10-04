package com.TyreseKeyter.smartpantrymanager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
public class SettingsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        SharedPreferences preferences = getPreferences("settings", MODE_PRIVATE);
        Switch expiryAlertSwitch = findViewById(R.switchExpiryAlerts);
        boolean alertsEnabled = preferences.getBoolean("expiry_alerts", true);
        expiryAlertSwitch.setChecked(alertsEnabled);
        expiryAlertSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("expiry_alerts", isChecked);
            editor.apply();
        });
    }
}
