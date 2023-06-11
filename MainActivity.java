package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText unitsEditText;
    private EditText rebateEditText;
    private Button calculateButton;
    private TextView totalChargesTextView;
    private TextView finalCostTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsEditText = findViewById(R.id.unitsEditText);
        rebateEditText = findViewById(R.id.rebateEditText);
        calculateButton = findViewById(R.id.calculateButton);
        totalChargesTextView = findViewById(R.id.totalChargesTextView);
        finalCostTextView = findViewById(R.id.finalCostTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });
    }

    private void calculateBill() {
        // Retrieve the input values from the user interface
        String unitsString = unitsEditText.getText().toString().trim();
        String rebateString = rebateEditText.getText().toString().trim();

        if (TextUtils.isEmpty(unitsString) || TextUtils.isEmpty(rebateString)) {
            // Display an error message if any of the fields are empty
            Toast.makeText(MainActivity.this, "Please enter values for all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int unitsUsed = Integer.parseInt(unitsString);
        float rebatePercentage = Float.parseFloat(rebateString);

        // Calculate the electricity bill
        float totalCharges = 0.0f;

        if (unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218f;
        } else if (unitsUsed <= 300) {
            totalCharges = 200 * 0.218f + (unitsUsed - 200) * 0.334f;
        } else if (unitsUsed <= 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + (unitsUsed - 300) * 0.516f;
        } else if (unitsUsed > 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + 300 * 0.516f + (unitsUsed - 600) * 0.546f;
        }

        // Apply the rebate percentage
        float finalCost = totalCharges - (totalCharges * (rebatePercentage / 100.0f));

        // Display the calculated values in the output fields
        totalChargesTextView.setText(String.format("RM %.2f", totalCharges));
        finalCostTextView.setText(String.format("RM %.2f", finalCost));
    }
}
