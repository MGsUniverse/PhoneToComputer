package com.MGSUniverse.PhoneToComputer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ThirdActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Button sendBTN = findViewById(R.id.sendBTN);
        Button SSD = findViewById(R.id.ServerShutDown);
        Button commands = findViewById(R.id.commands);

        sendBTN.setOnClickListener(view -> {
            Worker w = new Worker();
            new Thread(w).start();
        });

        SSD.setOnClickListener(view -> {
            SD s = new SD();
            new Thread(s).start();
        });

        commands.setOnClickListener(view -> {
            EditText ip = findViewById(R.id.ip);
            Intent intent = new Intent(ThirdActivity.this, commands.class);
            intent.putExtra("ip", ip.getText().toString());

            startActivity(intent);
        });
    }
    class Worker implements Runnable{

        @Override
        public void run() {

            EditText message = findViewById(R.id.message);
            EditText ip = findViewById(R.id.ip);

            Socket s;
            try {
                s = new Socket(InetAddress.getByName(ip.getText().toString()), 16734);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

            String str = message.getText().toString();

            PrintWriter out;
            try {
                out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

            out.println(str);
            out.flush();
        }
    }
    class SD implements Runnable{

        @Override
        public void run() {

            Socket s;
            EditText ip = findViewById(R.id.ip);
            try {
                s = new Socket(InetAddress.getByName(ip.getText().toString()), 16734);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

            String str = "bnhfhdhfdjgujddnkvhdkhodld,flb.n[[ph5574sbHSqw yqg geqhavdsgfvgdhgu    ftfyfdfvfchgvchghcgvhcgvhcgvhcghdvf dfbdvchchgvghcghjfgutgfcgbdbdfjfhrhrvdhgg";

            PrintWriter out;
            try {
                out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

            out.println(str);
            out.flush();
        }
    }
}