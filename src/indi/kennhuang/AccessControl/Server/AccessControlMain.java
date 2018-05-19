package indi.kennhuang.AccessControl.Server;

import indi.kennhuang.AccessControl.Server.db.SQLite;
import indi.kennhuang.AccessControl.Server.Device.DeviceServer;
import indi.kennhuang.AccessControl.Server.web.WebServer;

import java.sql.SQLException;

public class AccessControlMain {
    static DeviceServer deviceserver = new DeviceServer();
    static WebServer webServer = new WebServer();
    public static void main(String[] Args) throws SQLException, InterruptedException {
        new Thread(deviceserver).start();
        SQLite.openDatabase("jdbc:sqlite:foo.db");
        new Thread(webServer).start();
        /*
        Scanner input = new Scanner(System.in);
        while(true){
            int cmdInt = input.nextInt();

            switch (cmdInt){
                case 1:

                    break;
                default:
                    System.err.println("Wrong number");
                    break;
            }
        }
        */
    }
}
