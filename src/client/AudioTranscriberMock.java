package client;

import java.io.IOException;
import java.net.URISyntaxException;

public class AudioTranscriberMock implements Transcriber{
    private String FILE_PATH;
    
    public AudioTranscriberMock(String audioFile) {
        this.FILE_PATH = audioFile;
    }

    @Override
    public String generateTranscription() throws IOException, URISyntaxException {
        // TODO Auto-generated method stub
        return "I want a dinner recipe for peas, carrots, and rice";
    }

}
