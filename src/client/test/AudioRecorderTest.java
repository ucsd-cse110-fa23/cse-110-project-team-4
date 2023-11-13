package client.test;

import org.junit.jupiter.api.Test;

import client.*;
import models.Recipe;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AudioRecorderTest {
    AudioRecorder sampleAR = new AudioRecorder();
    Thread thread = new Thread(sampleAR);

    @BeforeEach 
    void seedData() {
        thread.start();
    }

    @Test
    void testAudioRecorderFile() {

        assertEquals("RecordAudio.wav", sampleAR.getAudioFile().getName());
        assertEquals("src\\client\\audio\\RecordAudio.wav", sampleAR.getAudioFile().getPath());
        assertEquals("src\\client\\audio\\RecordAudio.wav", sampleAR.getAudioFilePath());
     
    }

    // @Test
    // void testAudioRecorderCall() {
    //     assertTrue(sampleAR.getTargetDataLine().isOpen());
    //     sampleAR.finish();
        
    // }

}
