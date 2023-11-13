package client;

import javax.sound.sampled.*;

import javafx.concurrent.Task;

import java.io.*;

/*
 * Code from this file mostly borrowed from https://www.codejava.net/coding/capture-and-record-sound-into-wav-file-with-java-sound-api,
 * but very similar to the code from the AudioRecorder in Lab 5.
 */
public class AudioRecorder extends Task<Void> {

    // path of the wav file
    private File wavFile;

    // format of audio file
    private AudioFileFormat.Type fileType;
    // the line from which audio data is captured
    private TargetDataLine line;

    public AudioRecorder() {
        wavFile = new File("src\\client\\audio\\RecordAudio.wav");
        fileType = AudioFileFormat.Type.WAVE;
        try {
            DataLine.Info dataLineInfo = new DataLine.Info(
                    TargetDataLine.class,
                    getAudioFormat());
            this.line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

    public TargetDataLine getTargetDataLine() {
        try {
            this.line.open(getAudioFormat());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.line;
    }
}
