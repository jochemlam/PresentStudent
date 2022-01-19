package nl.dehaagsehogeschool.thechallenge;

import java.text.SimpleDateFormat;
import java.util.*; // Scanner om invoer te lezen

import com.fazecast.jSerialComm.*;
import static com.fazecast.jSerialComm.SerialPort.*;


public class ComPortSendReceive {

    public static SerialPort serialPort;

    public static void main(String[] args) {

        String portName;
        SerialPort[] portNames = SerialPort.getCommPorts();
            portName = portNames[0].getSystemPortName();
        serialPort = SerialPort.getCommPort(portName);

        try {
            // seriële poort openen en instellen
            serialPort.openPort();
            serialPort.setComPortParameters(9600, 8, ONE_STOP_BIT, NO_PARITY);
            serialPort.setFlowControl(FLOW_CONTROL_DISABLED);

        } catch (Exception ex) {
            System.out.println("Fout bij schrijven naar seriële poort: " + ex);
        }

        try {
            Thread.sleep(5000); // 5 seconden pauzeren
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder bericht = new StringBuilder();

        //InsertIntoSQL database = new InsertIntoSQL();   //Deze regel uitcommenten als SQL nog niet werkt.

        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

            public void serialEvent(SerialPortEvent event) {

                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) { return; }

                String vorigTijdstip = null;
                byte buffer[] = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(buffer, buffer.length);


                for (byte b : buffer) {
                    if ((b == '\r' || b == '\n') && bericht.length() > 0) { // regeleinde gedetecteerd ('\r' of '\n')

                        // StringBuilder naar String converteren
                        String berichtData = bericht.toString();

                        // tijdstip = nu
                        String tijdstip = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                        // regeleindes verwijderen uit data en tijdstip
                        berichtData = berichtData.replace("\n", "").replace("\r", "");
                        tijdstip = tijdstip.replace("\n", "").replace("\r", "");


                        if (tijdstip.equals(vorigTijdstip)) {
                            System.out.println("Regel uit buffer genegeerd:");
                        }
                        else {
                           // database.insert(tijdstip, UID);  //Deze regel uitcommenten als SQL nog niet werkt.
                        }

                        System.out.print(tijdstip);
                        System.out.print("  ");
                        System.out.println(berichtData);
                        vorigTijdstip = tijdstip;

                        bericht.setLength(0);
                    } else {
                        bericht.append((char) b);
                    }
                }
            }
        });
    }
}
