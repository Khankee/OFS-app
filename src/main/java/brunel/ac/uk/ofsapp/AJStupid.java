package brunel.ac.uk.ofsapp;

import java.util.Scanner;

public class AJStupid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int heartRate = 98;
        String bloodPressure = "SYS  - 120 mmHg \n DIY  -  80 mmHg \n PULSE - 98 BPM";
        String sleepStage = "Awake 4% - 28 minutes \n REM 22% 1h 35 minutes \n Sleep 74% 5h 18 minutes";
        double bodyTemp = 35.7;
        String ECG = "30 rpm - 98%";

        while(true) {
            System.out.println("What you want stupid AJ? Choose: Heart Rate,Blood Pressure, Sleep Stage, Body Temperature,ESG");
            String next = scan.nextLine();
            if(next.equals("Heart Rate")){
                System.out.println(heartRate);
            } else if (next.equals("Blood Pressure")){
                System.out.println(bloodPressure);
            } else if (next.equals("Sleep Stage")){
                System.out.println(sleepStage);
            } else if (next.equals("Body Temperature")){
                System.out.println(bodyTemp);
            }else if (next.equals("ESG")){
                System.out.println(ECG);
            } else {
                System.out.println("Input incorrect");
            }
        }
    }
}
