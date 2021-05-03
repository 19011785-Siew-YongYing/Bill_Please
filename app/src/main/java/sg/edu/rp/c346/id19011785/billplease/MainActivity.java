package sg.edu.rp.c346.id19011785.billplease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText etAmt;
    EditText etPax;
    ToggleButton tgSVS;
    ToggleButton tgGST;
    EditText etDiscount;
    RadioGroup grpPayment;
    Button btnSplit1;
    Button btnReset1;
    TextView viewTotal;
    TextView viewIdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.editAmount);
        etPax = findViewById(R.id.editNumOfPax);
        tgSVS = findViewById(R.id.toggleButtonSVS);
        tgGST = findViewById(R.id.toggleButtonGST);
        etDiscount = findViewById(R.id.editDiscount);
        grpPayment = findViewById(R.id.radioButtonPayments);
        btnSplit1 = findViewById(R.id.btnSplit);
        btnReset1 = findViewById(R.id.btnReset);
        viewTotal = findViewById(R.id.totalB);
        viewIdv = findViewById(R.id.idvPays);

        btnSplit1.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Double totalB = 0.00;
                int pple = Integer.parseInt(etPax.getText().toString());

                if (etAmt.getText().toString().length() > 0 && etPax.getText().toString().length() > 0){ // validation
                    if (tgSVS.isChecked() == false && tgGST.isChecked() == false){ // check if both SVS and GST is NOT checked
                        totalB = Double.parseDouble(etAmt.getText().toString());
                    }
                    else if (tgSVS.isChecked() == true && tgGST.isChecked() == false){ // check if SVS is checked but GST is not checked
                        totalB = Double.parseDouble(etAmt.getText().toString()) * 1.1;
                    }
                    else if (tgSVS.isChecked() == false && tgGST.isChecked() == true) { // check if SVS is NOT checked but GST is checked
                        totalB = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                    }
                    else if (tgSVS.isChecked() == true && tgGST.isChecked() == true) { // check if SVS and GST is checked
                        totalB = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                    }
                }
                else if (etAmt.getText().toString().length() == 0 && etPax.getText().toString().length() > 0) { // validation part 2
                    Toast.makeText(MainActivity.this, "Please enter amount", Toast.LENGTH_LONG).show();
                }

                else if (etAmt.getText().toString().length() > 0 && etPax.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Please enter pax", Toast.LENGTH_LONG).show();
                }

                else if (etAmt.getText().toString().length() == 0 && etPax.getText().toString().length() == 0){
                        Toast.makeText(MainActivity.this, "Please enter Amount and Pax number", Toast.LENGTH_LONG).show();
                }
                
                if (etDiscount.getText().toString().length() > 0) {
                    totalB = totalB * (1 - Double.parseDouble(etDiscount.getText().toString()) / 100);
                }

                int checkRadioId = grpPayment.getCheckedRadioButtonId();
                if (checkRadioId == R.id.radioButtonCash) {
                    viewTotal.setText("Total Bill: $" + String.format("%.2f", totalB));
                    viewIdv.setText("Each Pays: $ " + String.format("%.2f", totalB/pple) + " in cash");
                }

                if (checkRadioId == R.id.radioButtonPayNow) {
                    viewTotal.setText("Total Bill: $" + String.format("%.2f", totalB));
                    viewIdv.setText("Each Pays: $" + String.format("%.2f", totalB/pple) + " via PayNow to 91234567");
                }
            }

        });

        btnReset1.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v2){
                etAmt.setText("");
                etPax.setText("");
                tgSVS.setChecked(false);
                tgGST.setChecked(false);
                etDiscount.setText("");
                grpPayment.clearCheck();
                viewTotal.setText("");
                viewIdv.setText("");
            }

        });

    }
}