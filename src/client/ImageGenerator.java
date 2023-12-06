package client;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ImageGenerator {
    byte[] generateImage() throws IOException, InterruptedException, URISyntaxException;
}
