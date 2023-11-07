package client;

import javax.sound.sampled.*;

import javafx.concurrent.Task;

import java.io.*;

public class AudioRecorder extends Task<Void> {

    // path of the wav file
    private File wavFile = new File("src/client/audio/RecordAudio.wav");

    // format of audio file
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which audio data is captured
    private TargetDataLine line;

    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = false;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Captures the sound and record into a WAV file
     */
    @Override
    public Void call() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start(); // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }


    /**
     * Closes the target data line to finish capturing and recording
     */
    public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }

    public File getAudioFile() {
        return this.wavFile;
    }

    public String getAudioFilePath() {
        return this.wavFile.getPath();
    }

    public boolean getTargetDataLineOpenStatus() {
        return this.line.isOpen();
    }
}
