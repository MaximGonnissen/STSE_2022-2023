package FuzzyRGB;


import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.ev3.EV3ColorSensor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.io.FileWriter;
import java.io.IOException;


//Jason Imports Start

//Jason Imports End


public class FuzzyRGB_f {

    static EV3LargeRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.C);

    static EV3LargeRegulatedMotor motor2 =  new EV3LargeRegulatedMotor(MotorPort.D);

    static EV3ColorSensor color =  new EV3ColorSensor(SensorPort.S4);

    static float[] sample ;
    public static void main(String[] args) {



        System.out.println("Switching to RGB Mode");
       // color.setFloodlight(true);
        SampleProvider sp = color.getRGBMode();

        int sampleSize = sp.sampleSize();

        sample  = new float[sampleSize];

        int idcolor = 0;

        Delay.msDelay(2000);

        motor.setSpeed(150);
        motor.forward();

        // Takes some samples and prints them

        System.out.println("*****START HERE*******");

        float red =0;
        float green =0;
        float blue =0;

            double sample_size = 1.0;


        for (int i = 1; i < 5000; i++) {

          sp.fetchSample(sample, 0);
            //    idcolor = color.getColorID();
            	System.out.println("N=" + i + " Sample={}" + (sample[0]));
            	System.out.println("N=" + i + " Sample={}" + (sample[1]));
            	System.out.println("N=" + i + " Sample={}" + (sample[2]));

            if (sample[0]> 17.0 || sample[1]> 17.0 || sample[2] > 17.0){
                motor.stop();
            }



                System.out.println("COLOR:"+idcolor);

                red +=sample[0];
                green +=sample[1];
                blue +=sample[2];

            if (i % (int)sample_size ==0)
            {
                System.out.println("countta");
                red /=sample_size;
                green /=sample_size;
                blue /=sample_size;


                fuzzyColorSensor(red,green,blue);

                red = 0;
                green  = 0;
                blue = 0;
            }



            //Color cl = new Color((int)(sample[0] * 255), (int)(sample[1] * 255), (int)(sample[2] * 255));
            //	System.out.println(cl.getRed() +" " +cl.getGreen() + " "+ cl.getBlue() + " " + cl.getColor());
               Delay.msDelay(100);
        }

        sp = color.getAmbientMode();
        sampleSize = sp.sampleSize();
        sample = new float[sampleSize];
        sp.fetchSample(sample, 0);
        System.out.println("Ambient" + (sample[0]));



    }

    public static void fuzzyColorSensor(float r, float g, float b) {

        double r1 = 0.0;
        double r2 = 0.0;
        double r3 = 0.0;
        double r4 = 0.0;
        double r5 = 0.0;


        double g1 = 0.0;
        double g2 = 0.0;
        double g3 = 0.0;
        double g4 = 0.0;
        double g5 = 0.0;


        double b1 = 0.0;
        double b2 = 0.0;
        double b3 = 0.0;
        double b4 = 0.0;
        double b5 = 0.0;

        int red =  0;
        int green = 0;
        int blue = 0;



         red = (int) r;
         green =(int) g;
         blue =(int) b;




        String s_red = "";
        String s_green = "";
        String s_blue = "";





        if (red < 15)    // low red
        {

            r1 = 1;
            r2 = 0;
            r3 = 0;
            System.out.println("low red" + " " + r1 + " " + r2 + " " + r3);

            s_red = "low red";

        } else if (red >= 15 && red < 35) {   // middle red

            r1 = (35.0 - red) / 20.0;
            r2 = (red - 15.0) / 20.0;
            r3 = 0;
            System.out.println("middle red" + " " + r1 + " " + r2 + " " + r3);

            s_red = "middle red";

        } else if (red >= 35 && red < 55) { // high red

            r1 = 0;
            r2 = (55.0 - red) / 20.0;
            r3 = (red - 35.0) / 20.0;
            System.out.println("high red" + " " + r1 + " " + r2 + " " + r3);


            s_red = "high red";

        } else if (red >= 55 && red < 101) { // very high red

            r1 = 0;
            r2 = 0;
            r3 = 0;
            r4 = (red-55.0)/45.0;
            r5 = (100.0-red)/45.0;

            System.out.println("very high red" + " " + r4 + " " + r5 ); //+ " " + r3


            s_red = "very high red";

        }

        else if (red >= 101 && red < 155) { // very high green

            r1 = 0;
            r2 = 0;
            r3 = 0;
            r4 = (155.0-red)/55.0;
            r5 = (red-101.0)/55.0;

            System.out.println("ultra red" + " " + r4 + " " + r5 + " " ); //+ b3


            s_red = "ultra red";

        }

        else if (red >= 155) { // very high green

            //  b1 = 0;
            //  b2 = 0;
            //  b3 = 0;
            //  b4 = (155.0-blue)/55.0;
            r5 = 1;

            System.out.println("ultra high red" + " " + r5); //+ b3


            s_red = "ultra high red";

        }


// ---------------------------------- GREEN GREEN GREEN ---------------------------------------------

        if (green < 15)    // low green
        {

            g1 = 1;
            g2 = 0;
            g3 = 0;
            System.out.println("low green" + " " + g1 + " " + g2 + " " + g3);


            s_green = "low green";

        } else if (green >= 15 && green < 35) {   // middle green

            g1 = (35.0 - green) / 20.0;
            g2 = (green - 15.0) / 20.0;
            g3 = 0;
            System.out.println("middle green" + " " + g1 + " " + g2 + " " + g3);


            s_green = "middle green";

        } else if (green >= 35 && green < 55) { // high green

            g1 = 0;
            g2 = (55.0 - green) / 20.0;
            g3 = (green - 35.0) / 20.0;
            System.out.println("high green" + " " + g1 + " " + g2 + " " + g3);


            s_green = "high green";

        } else if (green >= 55 && green < 101) { // very high green

            g1 = 0;
            g2 = 0;
            g3 = 0;
            g4 = (green-55.0)/45.0;
            g5 = (100.0-green)/45.0;

            System.out.println("very high green" + " " + g4 + " " + g5 + " " ); //+ b3


            s_green = "very high green";

        }

        else if (green >= 101 && green < 155) { // very high green

            g1 = 0;
            g2 = 0;
            g3 = 0;
            g4 = (155.0-green)/55.0;
            g5 = (green-101.0)/55.0;

            System.out.println("ultra green" + " " + g4 + " " + g5 + " " ); //+ b3


            s_green = "ultra green";

        }

        else if (green >= 155) { // very high green

            //  b1 = 0;
            //  b2 = 0;
            //  b3 = 0;
            //  b4 = (155.0-blue)/55.0;
            g5 = 1;

            System.out.println("ultra high green" + " " + g5); //+ b3


            s_green = "ultra high green";

        }


//--------------------------------   BLUE BLUE BLUE ---------------------------------------------


        if (blue < 15)    // low blue
        {

            b1 = 1;
            b2 = 0;
            b3 = 0;
            System.out.println("low blue" + " " + b1 + " " + b2 + " " + b3);


            s_blue = "low blue";

        } else if (blue >= 15 && blue < 35) {   // middle blue

            b1 = (35.0 - blue) / 20.0;
            b2 = (blue - 15.0) / 20.0;
            b3 = 0;
            System.out.println("middle blue" + " " + b1 + " " + b2 + " " + b3);

            s_blue = "middle blue";

        } else if (blue >= 35 && blue < 55) { // high blue

            b1 = 0;
            b2 = (55.0 - blue) / 20.0;
            b3 = (blue - 35.0) / 20.0;
            System.out.println("high blue" + " " + b1 + " " + b2 + " " + b3);


            s_blue = "high blue";

        } else if (blue >= 55 && blue < 101) { // very high green

            b1 = 0;
            b2 = 0;
            b3 = 0;
            b4 = (blue-55.0)/45.0;
            b5 = (100.0-blue)/45.0;

            System.out.println("very high blue" + " " + b4 + " " + b5 + " " ); //+ b3


            s_blue = "very high blue";

        }

        else if (blue >= 101 && blue < 155) { // very high green

            b1 = 0;
            b2 = 0;
            b3 = 0;
            b4 = (155.0-blue)/55.0;
            b5 = (blue-101.0)/55.0;

            System.out.println("ultra blue" + " " + b4 + " " + b5 + " " ); //+ b3


            s_blue = "ultra blue";

        }

        else if (blue >= 155) { // very high green

          //  b1 = 0;
          //  b2 = 0;
          //  b3 = 0;
          //  b4 = (155.0-blue)/55.0;
            b5 = 1;

            System.out.println("ultra high blue" + " " + b5); //+ b3


            s_blue = "ultra high blue";

        }






        if (s_red.equals("very high red") && s_green.equals("middle green") && s_blue.equals("low blue")) {


            System.out.println("Red 1");



            if ( ( (r4 <= 0.2 && g2 <= 0.8 && b3 == 0.0)) ){
                System.out.println("Spoiled Red possible");
                GoSpoiledRedPosition();
            }else{
                //    GoRedPosition(); push();
            }


        }

        else if (s_red.equals("high red") && s_green.equals("middle green") && s_blue.equals("low blue")) {


            System.out.println("Red 2");

        //    GoRedPosition(); push();

            if ( ( ( r3 <0.55 && g3 == 0.0 && b3 == 0.0 )) ){
                System.out.println("Spoiled Red possible");
             //   GoSpoiledRedPosition();
            }
            else{
      //          GoRedPosition(); push();
            }
        }

        else if (s_red.equals("middle red") && s_green.equals("low green") && s_blue.equals("low blue")) {


            System.out.println("Red 3");

            if ( ( (r3 < 0.45 && r3 > 0.0)) ){
                System.out.println("Spoiled Red possible");
            //    GoSpoiledRedPosition();
            }
            else if( ( (r3 == 0.0 && g3 == 0.0 && b3 == 0.0)) ){
                System.out.println("Brown  possible");
            }

          //  GoRedPosition(); push();


        }

        else if (s_red.equals("ultra red") && s_green.equals("middle green") && s_blue.equals("low blue")) {


            System.out.println("Red 4");

         //   GoRedPosition(); push();

            if (!((r5>0.0 && r5 < 0.15) && g2>=0.85) )
                System.out.println("Spoiled Red possible Test");




        }

        else if (s_red.equals("high red") && s_green.equals("low green") && s_blue.equals("low blue")) {


            System.out.println("Red 5");

         //   GoRedPosition(); push();


        }

        else if (s_red.equals("ultra red") && s_green.equals("high green") && s_blue.equals("low blue")) {


            System.out.println("Red 6");


            if (!((r5>0.0 && r5 < 0.31) && g2>=0.55) )
                System.out.println("Spoiled Red possible Test");


        }

        else if (s_red.equals("ultra red") && s_green.equals("high green") && s_blue.equals("middle blue")) {


            System.out.println("Spoiled Red possible yakinca");


        }

        else if (s_red.equals("very high red") && s_green.equals("low green") && s_blue.equals("low blue")) {

            System.out.println("Curuk Kirmizi 1");

          //  GoSpoiledRedPosition();
        }

        else if (s_red.equals("ultra red") && s_green.equals("middle green") && s_blue.equals("middle blue")) {


            System.out.println("Red 7 - Spoiled Red possible");

         //   GoSpoiledRedPosition();


        }


        //--------------------------------------------------------------


        else if (s_red.equals("low red") && s_green.equals("high green") && s_blue.equals("low blue")) {


            System.out.println("Normal Green 1");

            if (r3>=0.4)
            {
                System.out.println("Dark Green possible");
            }


        }

        else if (s_red.equals("middle red") && s_green.equals("very high green") && s_blue.equals("low blue")) {


            System.out.println("Normal Green 22");

            if (r2<=0.75 && g5<=0.34)
            {
                System.out.println("Fistik Yesili possible");
            }




        }

        else if (s_red.equals("low red") && s_green.equals("middle green") && s_blue.equals("low blue")) {


            System.out.println("Normal Green 2");

            if (r2==1.0 && g5<=0.58)
            {
                System.out.println("Fistik Yesili possible");
            }

            if (g2>=0.85)
            {
                System.out.println("Dark Green possible");
            }


        }

        else if (s_red.equals("middle red") && s_green.equals("high green") && s_blue.equals("low blue")) {


            System.out.println("Dark Green 1");

            if (r2>=0.45 && g3>=0.3){
                System.out.println("Mor possible");
            }


        }

        else if (s_red.equals("middle red") && s_green.equals("middle green") && s_blue.equals("low blue")) {


            System.out.println("Dark Green 2");


        }

        else if (s_red.equals("middle red") && s_green.equals("low green") && s_blue.equals("middle blue")) {


            System.out.println("BROWN");


        }




        else if (s_red.equals("middle red") && s_green.equals("low green") && s_blue.equals("middle blue")) {


            System.out.println("BROWN");


        }



        else if (s_red.equals("middle red") && s_green.equals("middle green") && s_blue.equals("very high blue")) {


            System.out.println("Koyu Mor 1");


        }

        else if (s_red.equals("middle red") && s_green.equals("middle green") && s_blue.equals("high blue")) {


            System.out.println("Mor");

            if (r1>=7.5 || g1>=0.05 || b2>=6.0) // olmazsa && ile dene  g1 0.05 kotu
            System.out.println("Koyu yesil possible");


        }

        else if (s_red.equals("middle red") && s_green.equals("middle green") && s_blue.equals("high blue")) {


            System.out.println("Koyu yesil");


        }

        else if (s_red.equals("middle red") && s_green.equals("high green") && s_blue.equals("high blue")) {


            System.out.println("Koyu yesil 2");


        }

        else if (s_red.equals("middle red") && s_green.equals("middle green") && s_blue.equals("middle blue")) {


            System.out.println("Koyu Mor 2");

            if (r1<=4.0 || g1<=8.0 || b1<=0.6){

                System.out.println("KAHVE possible");
            }


        }

        else if (s_red.equals("very high red") && s_green.equals("high green") && s_blue.equals("low blue")) {


            System.out.println("*Spoiled Red possible mi Testi - Red 10");

             //   GoSpoiledRedPosition();
            if (((r5>0.0 && r5 < 0.16) && g3>=0.45) )
                System.out.println("Spoiled Red possible Test");




        }



        else if (s_red.equals("very high red") && s_green.equals("high green") && s_blue.equals("middle blue")) {


            System.out.println("Acik Sari 1");

            if (r5<=0.8 && g3<=0.5 && b3 == 0.0){
                System.out.println("Pink");
            }


        } else if (s_red.equals("very high red") && s_green.equals("very high green") && s_blue.equals("middle blue")) {


            System.out.println("Acik Sari 2");  // Condition 2 for acik sari


        } else if (s_red.equals("very high red") && s_green.equals("very high green") && s_blue.equals("high blue")) {


            System.out.println("Acik Sari 3");  // Condition 3 for acik sari


        }

     else if (s_red.equals("ultra red") && s_green.equals("ultra green") && s_blue.equals("middle blue")) {


        System.out.println("Acik Sari 1-1");  // Condition 3 for acik sari


    }

        else if (s_red.equals("ultra red") && s_green.equals("ultra green") && s_blue.equals("low blue")) {


            System.out.println("Acik Sari 1-2");  // Condition 3 for acik sari


        }

        else if (s_red.equals("ultra high red") && s_green.equals("ultra green") && s_blue.equals("low blue")) {


            System.out.println("Acik Sari 1-3");  // Condition 3 for acik sari


        }



        else if (s_red.equals("very high red") && s_green.equals("high green") && s_blue.equals("high blue")) {


            System.out.println("Koyu Sari(Oralet) 1");


        } else if (s_red.equals("middle red") && s_green.equals("high green") && s_blue.equals("very high blue") ||
                (((r1 == 0) && (r2 >= 0.55 && r2 <= 1) && (g1 == 0) && (g2 >= 0.45 && g2 <= 0.9) && (g3 >= 0.1 && g3 <= 0.55)))) {


            System.out.println("Koyu Sari(Oralet) 2");


        }

        else if (s_red.equals("high red") && s_green.equals("middle green") && s_blue.equals("middle blue") ||
                (((r1 == 0) && (r2 >= 0.55 && r2 <= 1) && (g1 == 0) && (g2 >= 0.45 && g2 <= 0.9) && (g3 >= 0.1 && g3 <= 0.55)))) {


                System.out.println("Koyu Sari(Oralet) 3");

                if (r2<=7.0 || g2<=4.0  || b3<=5.0){
                    System.out.println("BROWN possible 2");
                }


        }

        else if (s_red.equals("ultra red") && s_green.equals("very high green") && s_blue.equals("very high blue")) {


            System.out.println("Koyu Sari(Oralet) 4");


        }

        else if (s_red.equals("ultra red") && s_green.equals("very high green") && s_blue.equals("high blue")) {


            System.out.println("Pembe 1-1");


        }

        else if (s_red.equals("ultra high red") && s_green.equals("ultra green") && s_blue.equals("very high blue")) {


            System.out.println("Pembe 1-2");


        }

        else if (s_red.equals("ultra high red") && s_green.equals("ultra green") && s_blue.equals("high blue")) {


            System.out.println("Pembe 1-3");


        }



        else if (s_red.equals("low red") && s_green.equals("middle green") && s_blue.equals("middle blue")) {



            System.out.println("Koyu Yesil*"); // Koyu yesilde possible


        }

        else if (s_red.equals("very high red") && s_green.equals("ultra green") && s_blue.equals("low blue")) {


            System.out.println("Fistik Yesili 1-1");

            motor.forward();


        }

        else if (s_red.equals("middle red") && s_green.equals("high green") && s_blue.equals("middle blue")) {


            System.out.println("Fistik Yesili");


        }

        else if (s_red.equals("high red") && s_green.equals("very high green") && s_blue.equals("middle blue")) {


            System.out.println("Fistik Yesili 2");


        }

        else if (s_red.equals("high red") && s_green.equals("very high green") && s_blue.equals("high blue")) {


            System.out.println("Fistik Yesili 3");


        }

        else if (s_red.equals("high red") && s_green.equals("very high green") && s_blue.equals("low blue")) {


            System.out.println("Fistik Yesili 4");


        }

        else if (s_red.equals("very high red") && s_green.equals("ultra green") && s_blue.equals("high blue")) {


            System.out.println("Fistik Yesili 5");


        }


        else if (s_red.equals("low red") && s_green.equals("middle green") && s_blue.equals("very high blue")) {


            System.out.println("Koyu Mavi");


        }

            else if (s_red.equals("low red") && s_green.equals("middle green") && s_blue.equals("high blue")) {


                System.out.println("Acik Mavi");


            }
            else if (s_red.equals("low red") && s_green.equals("middle green") && s_blue.equals("very high blue")) {


                System.out.println("Acik Mavi 2");

            }

             else if (s_red.equals("middle red") && s_green.equals("very high green") && s_blue.equals("very high blue")) {


                System.out.println("Acik Mavi 3");

        }



            else if (s_red.equals("low red") && s_green.equals("high green") && s_blue.equals("very high blue")) {


            System.out.println("TURKUAZ");


        }

        else if (s_red.equals("low red") && s_green.equals("low green") && s_blue.equals("middle blue")) {


            System.out.println("Dark Green low");


        } else if ((s_red.equals("high red") && s_green.equals("high green") && s_blue.equals("very high blue"))
                && (!((r1 == 0) && (r2 >= 0.55 && r2 <= 1) && (g1 == 0) && (g2 >= 0.45 && g2 <= 0.9) && (g3 >= 0.1 && g3 <= 0.55)))) {


            System.out.println("Lila");


        } else if (s_red.equals("very high red") && s_green.equals("very high green") && s_blue.equals("very high blue")) {


            System.out.println("Beyaz");


        } else if (s_red.equals("very high red") && s_green.equals("middle green") && s_blue.equals("high blue")) {


            System.out.println("Magenta");


        }

        else if (s_red.equals("high red") && s_green.equals("middle green") && s_blue.equals("high blue")) {


            System.out.println("Kahverengi 4 - Eski Pembe");


        }

        else if (s_red.equals("high red") && s_green.equals("very high green") && s_blue.equals("ultra blue")) {


            System.out.println("Pembe 2");


        }

        else if (s_red.equals("ultra red") && s_green.equals("very high green") && s_blue.equals("ultra blue")) {


            System.out.println("Pembe 3");


        }





        else if (s_red.equals("very high red") && s_green.equals("low green") && s_blue.equals("middle blue")) {


            System.out.println("Red");

            if (r5 < 0.4)
                System.out.println("Spoiled Red possible");

        } else if (s_red.equals("high red") && s_green.equals("middle green") && s_blue.equals("middle blue")) {


            System.out.println("Red");


        }else if (s_red.equals("very high red") && s_green.equals("middle green") && s_blue.equals("middle blue")) {


                System.out.println("Red");

                if (r5 <0.34)
                    System.out.println("Spoiled Red possible Test");


        }
        else if (s_red.equals("ultra red") && s_green.equals("very high green") && s_blue.equals("middle blue")) {


            System.out.println("Acik Sari 4");


        }

        else if (s_red.equals("ultra red") && s_green.equals("ultra green") && s_blue.equals("high blue")) {


            System.out.println("Acik Sari 5");

            if (r5 <=0.93 && g5<=0.26 && b3 ==0.9)
            System.out.println("Pembe possible");


        }



        else {

            System.out.println("null");

            FileWriter fw = null;
            try {
                fw = new FileWriter("/home/robot/productionLineFuzzy/src/java/fuzzy.txt", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // BufferedWriter bw = new BufferedWriter(fw);
            //   bw.write(motor1.getSpeed());
            //   bw.newLine();
            //  bw.close();


            try {
                fw.write(sample[0]+","+sample[1]+","+sample[2]);
                fw.write(System.getProperty( "line.separator" ));

                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }

        System.out.println("----------------------------------------------------------");

    }

    public static void GoRedPosition() {
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(725);
        motor2.stop();
        motor2.getPosition();

        motor.setSpeed(150);
        motor.forward();
    }



    public static void GoSpoiledRedPosition() {
        motor2.setSpeed(800);
        Delay.msDelay(1);
        motor2.backward();
        Delay.msDelay(2500);
        motor2.stop();
        motor2.getPosition();

        motor.setSpeed(150);
        motor.forward();
    }







}
