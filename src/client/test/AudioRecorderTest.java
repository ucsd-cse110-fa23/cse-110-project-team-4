package client.test;

import org.junit.jupiter.api.Test;

import client.*;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AudioRecorderTest {
    AudioRecorder sampleAR = new AudioRecorder();
    Thread thread = new Thread(sampleAR);

    @Test
    void testAudioRecorderFile() {

        assertEquals("RecordAudio.wav", sampleAR.getAudioFile().getName());
        assertEquals("src\\client\\audio\\RecordAudio.wav", sampleAR.getAudioFile().getPath());
        assertEquals("src\\client\\audio\\RecordAudio.wav", sampleAR.getAudioFilePath());
     
    }

}
