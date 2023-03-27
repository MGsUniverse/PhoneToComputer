package com.MGSUniverse.PhoneToComputer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class commands extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);

        Button run = findViewById(R.id.run);

        run.setOnClickListener(view -> {
            commandSender cs = new commandSender();
            new Thread(cs).start();
        });


    }
    class commandSender implements Runnable{

        @Override
        public void run() {

            EditText cmd = findViewById(R.id.cmd);

            Socket s;
            Bundle extras = getIntent().getExtras();
            assert extras != null;
            String value = extras.getString("ip");
            try {
                s = new Socket(value, 16734);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }

            String str = "cmd:" + cmd.getText().toString();

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