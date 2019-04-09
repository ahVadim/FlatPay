package com.example.vadim.flatpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowMessageAc extends AppCompatActivity {

    Button sendMessageBtn;
    EditText messageText;
    SaveData mSaveData;
    SingleData lastData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        sendMessageBtn = (Button) findViewById(R.id.sendMessageBtn);
        messageText = (EditText) findViewById(R.id.messageText);
        mSaveData = new SaveData(this);
        lastData = mSaveData.getLastData();
        final String str1 = makeText();
        messageText.setText(str1);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendTextIntent = new Intent();
                sendTextIntent.setAction(Intent.ACTION_SEND);
                sendTextIntent.putExtra(Intent.EXTRA_TEXT, str1);
                sendTextIntent.setType("text/plain");
                startActivity(sendTextIntent);
            }
        });
    }

    public String makeText(){
        String text = "нет данных";
        if (lastData!=null) {
            text =  "Показания в этом месяце:\n" +
                    "эл-во: " + lastData.getElectricity() + "; " +
                    "х.в.: " + lastData.getColdWater() + "; " +
                    "г.в.: " + lastData.getHotWater() + ".";
            Float sum = mSaveData.calcSum();
            if (sum !=0) {
                text = text +  "\nСумма за прошлый месяц: " + sum + "руб.";
            }
        }
        return text;
    }

}
