package com.MGSUniverse.GUI;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final JPanel panel = new JPanel();
    private static final JTextArea pane = new JTextArea();
    private static final JFrame frame = new JFrame();
    private static final JScrollPane scroll = new JScrollPane(pane);

    public static void main(String[] args) throws IOException {
        while(true) {

            frame.add(panel);

            pane.setEditable(false);

            scroll.setBounds(0, 0, 685, 465);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(scroll);

            panel.setLayout(null);

            pane.setSize(700, 500);

            frame.setSize(700, 550);
            frame.setResizable(false);
            frame.setTitle("Phone to Computer");
            frame.setDefaultCloseOperation(3);

            frame.show(true);

            ServerSocket ss = new ServerSocket(16734, 100);

            Socket s = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String data = br.readLine();

            if (data.equals("bnhfhdhfdjgujddnkvhdkhodld,flb.n[[ph5574sbHSqw yqg geqhavdsgfvgdhgu    ftfyfdfvfchgvchghcgvhcgvhcgvhcghdvf dfbdvchchgvghcghjfgutgfcgbdbdfjfhrhrvdhgg")){
                shutdown();
            }

            if(data.startsWith("cmd:")){
                String command = data.substring(4);

                runCommand(command);
                data = "";
            }

            pane.append("\n " + data);
            ss.close();
        }

    }
    public static void shutdown() throws RuntimeException, IOException {
        String shutdownCommand = null;
        String operatingSystem = System.getProperty("os.name");

        if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
            shutdownCommand = "shutdown -h now";
        }
        else if ("Windows 10".equals(operatingSystem) || "Windows 7".equals(operatingSystem) || "Windows 8".equals(operatingSystem) || "Windows 8.1".equals(operatingSystem) || "Windows Vista".equals(operatingSystem) || "Windows XP".equals(operatingSystem) || "Windows ME".equals(operatingSystem) || "Windows 98".equals(operatingSystem) || "Windows 95".equals(operatingSystem) || "Windows".equals(operatingSystem)) {
            shutdownCommand = "shutdown.exe -s -t 0";
        }
        else {
            pane.append("LOG: Unsupported Operating System");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }
    public static void runCommand(String command){
        ProcessBuilder processBuilder = new ProcessBuilder();


        processBuilder.command("cmd.exe", "/c", command);

        pane.append("\ncommand output: \"\n");

        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                pane.append("\n" + output);
                pane.append("\n\"");
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
