package presentstudent;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.fazecast.jSerialComm.*;
import static com.fazecast.jSerialComm.SerialPort.*;


public class ComPortSendReceive {

    public SerialPort serialPort;
    public String tijdstip = "";
    public static String tagUID;

    public void startSerial() {

        // detect usable port name
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
            listen();
    }

    public void listen() {

        StringBuilder bericht = new StringBuilder();

        //InsertIntoSQL database = new InsertIntoSQL();   //Deze regel uitcommenten als SQL nog niet werkt.

        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            public void serialEvent(SerialPortEvent event) {

                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }

                String vorigTijdstip = null;
                byte buffer[] = new byte[serialPort.bytesAvailable()];
                int numRead = serialPort.readBytes(buffer, buffer.length);

                for (byte b : buffer) {
                    if ((b == '\r' || b == '\n') && bericht.length() > 0) { // regeleinde gedetecteerd ('\r' of '\n')

                        // StringBuilder naar String converteren
                        String berichtData = bericht.toString();

                        berichtData = berichtData.replace("\n", "").replace("\r", "");

                        System.out.println(berichtData);
                        tagUID = berichtData;

                        bericht.setLength(0);
                    } else {
                        bericht.append((char) b);
                    }
                }
            }
        });
    }
}
