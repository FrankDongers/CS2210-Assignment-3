import java.io.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundPlayer {

  public SoundPlayer() {}


  public  void play(String fileName) throws MultimediaException{    
   /* Play a file of type .wav or .mid. If the file cannot be played a 
      MultimediaException is thrown.                                        */
    try {
      File file = new File(fileName);
      if (!file.isFile()) throw new MultimediaException("Invalid file");
      URL url = file.toURI().toURL();
      AudioClip ac = Applet.newAudioClip(url);
      ac.play();      

      System.out.print("Press RET to continue.");
      BufferedReader keyboard =  new BufferedReader
		                   (new InputStreamReader(System.in));
      String c = keyboard.readLine();
      ac.stop();
    } catch (Exception e) {
       throw new MultimediaException("Error playing sound file "+fileName);

    }
  }
}
