package client;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Transcriber {
    public String generateTranscription() throws IOException, URISyntaxException;
}
