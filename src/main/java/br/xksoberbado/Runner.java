package br.xksoberbado;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class Runner {

    private static final String CONTAINER_NAME = "my-first-container";
    private static final String LOCAL_CONNECTION_STRING = "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;QueueEndpoint=http://127.0.0.1:10001/devstoreaccount1;TableEndpoint=http://127.0.0.1:10002/devstoreaccount1;";

    public static void main(final String[] args) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
            .connectionString(LOCAL_CONNECTION_STRING)
            .buildClient();

        uploadFileToBlobStorage(blobServiceClient);

//        createBlobContainer(blobServiceClient);
    }

    private static void uploadFileToBlobStorage(final BlobServiceClient blobServiceClient) {
        final String localPath = "./data/";
        new File(localPath).mkdirs();
        final String fileName = "quickstart-" + UUID.randomUUID() + ".txt";

        final BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);
        final BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        FileWriter writer = null;
        try {
            writer = new FileWriter(localPath + fileName, true);
            writer.write("Upado!");
            writer.close();
        } catch (final IOException ex) {
            log.error(ex.getMessage());
        }

        log.info("\nUploading to Blob storage as blob: \n {}", blobClient.getBlobUrl());

        blobClient.uploadFromFile(localPath + fileName);
    }

    private static void createBlobContainer(final BlobServiceClient blobServiceClient) {
        blobServiceClient.createBlobContainer(CONTAINER_NAME);
    }
}
