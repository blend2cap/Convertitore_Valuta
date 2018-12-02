package com.example.blend.convertitore_valuta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private final double cambio_usd_eur = 0.88;
    private final double cambio_cny_eur = 0.13;
    EditText usd_editText;
    EditText eur_editText;
    EditText cny_editText;
    String usd_stringText, eur_stringText, cny_stringText;
    TextWatcher usd_watcher;
    TextWatcher eur_watcher;
    TextWatcher cny_watcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usd_editText = findViewById(R.id.usd_editText);
        eur_editText = findViewById(R.id.eur_editText);

        usd_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                eur_editText.removeTextChangedListener(eur_watcher);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Currency_Conversion(usd_editText, );
            }

            @Override
            public void afterTextChanged(Editable editable) {
                eur_editText.addTextChangedListener(eur_watcher);
            }
        };

        eur_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //disable usd
                usd_editText.removeTextChangedListener(usd_watcher);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (eur_editText.getText().toString().isEmpty()) {eur_stringText="0";}
                else  eur_stringText = eur_editText.getText().toString();
                Double eur_amount = Double.parseDouble(eur_stringText);
                Double usd_amount = eur_amount/ cambio_usd_eur;
                usd_amount = Double.valueOf(new DecimalFormat("#.##").format(usd_amount)); //rounds 2 decimal places
                usd_editText.setText(usd_amount.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                usd_editText.addTextChangedListener(usd_watcher);
            }
        };

        usd_editText.addTextChangedListener(usd_watcher);
        eur_editText.addTextChangedListener(eur_watcher);



        }

    private void Currency_Conversion(EditText this_edit, EditText target_edit, String currencyConversion) {
        String string_amount;
        if (this_edit.getText().toString().isEmpty()) {
            string_amount = "0";
        }
        else {
            string_amount = usd_editText.getText().toString();
        }

        Double this_amount = Double.parseDouble(string_amount);

        switch (currencyConversion) {
            case "eur_usd":
                Double usd_amount = this_amount/ cambio_usd_eur;
                usd_amount = Double.valueOf(new DecimalFormat("#.##").format(usd_amount));
                target_edit.setText(usd_amount.toString());
            case "usd_eur":
                Double eur_amount = this_amount * cambio_usd_eur;
                eur_amount = Double.valueOf(new DecimalFormat("#.##").format(eur_amount));
                target_edit.setText(eur_amount.toString());

        }





    }


}

