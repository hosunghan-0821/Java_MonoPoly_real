
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.lang.reflect.Executable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicThread implements Runnable {


    public File a;
    public AudioInputStream b;
    public Clip c;
    public SharedObject sharedObject;

    public MusicThread(File a, AudioInputStream b, Clip c, SharedObject sharedObject) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {

        while (true) {
            try {
                c.open(b);
                c.start();
                for (int i = 0; i < c.getMicrosecondLength() / 1000000; i++) {
                    try {
                        while (sharedObject.turnOn) {
                            synchronized (sharedObject) {
                                c.stop();
                                sharedObject.wait();
                            }
                        }
                        c.start();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        //System.out.println("1번 인터럽트");
                        c.stop();
                        c.close();
                        break;
                    }

                }
                this.b=AudioSystem.getAudioInputStream(this.a);
                this.c=AudioSystem.getClip();
                if(sharedObject.TURNOFF) {
                    break;
                }
            } catch (Exception e) {

            }

        }
    }
}
