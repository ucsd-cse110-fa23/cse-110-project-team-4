package client.openAIAPI;

import java.io.IOException;
import java.net.URISyntaxException;

public class DallEImageGeneratorMock implements ImageGenerator {
    
    private String prompt;

    public DallEImageGeneratorMock(String prompt) {
        this.prompt = prompt;
        System.out.print(this.prompt);
    }

    @Override
    public byte[] generateImage() throws IOException, InterruptedException, URISyntaxException {
        // TODO Auto-generated method stub
        byte[] toReturn = { (byte) 0xe0, 0x4f, (byte) 0xd0,
                0x20, (byte) 0xea, 0x3a, 0x69, 0x10 };
        return toReturn;

    }

}
