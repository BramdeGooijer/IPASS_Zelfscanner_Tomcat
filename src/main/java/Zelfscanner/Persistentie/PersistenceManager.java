package Zelfscanner.Persistentie;

import Zelfscanner.Domeinmodel.Winkel;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;

public class PersistenceManager {
    private final static String ENDPOINT = "https://blobstorageipassbram.blob.core.windows.net/;QueueEndpoint=https://blobstorageipassbram.queue.core.windows.net/;FileEndpoint=https://blobstorageipassbram.file.core.windows.net/;TableEndpoint=https://blobstorageipassbram.table.core.windows.net/;SharedAccessSignature=sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2022-08-27T19:06:58Z&st=2022-06-27T11:06:58Z&spr=https&sig=JUN2YJ8iqGdweQ6%2FVCs50P2mPnsqqkOna9P6pXHj4dU%3D";
    private final static String SASTOKEN = "?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2022-08-27T19:06:58Z&st=2022-06-27T11:06:58Z&spr=https&sig=JUN2YJ8iqGdweQ6%2FVCs50P2mPnsqqkOna9P6pXHj4dU%3D";
    private final static String CONTAINER = "ipasswinkeldata";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();


    public static void loadWinkelFromAzure() throws IOException, ClassNotFoundException {
        BlobClient blobClient = blobContainer.getBlobClient("Winkel.json");

        if (blobContainer.exists()) {
            if (blobClient.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blobClient.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                ObjectInputStream ois = new ObjectInputStream(bais);

                Winkel loadedWinkel = (Winkel) ois.readObject();
                Winkel.setWinkel(loadedWinkel);
            }
        }
    }

    public static void saveWinkelToAzure() throws IOException {
        if (!blobContainer.exists()) {
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("Winkel.json");
        Winkel winkelToSave = Winkel.getWinkel();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(winkelToSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
    }

    public static void main(String[] args) throws IOException {
        saveWinkelToAzure();
    }
}
