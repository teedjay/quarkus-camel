package com.teedjay.quarkus.camel;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class AwsS3Route extends RouteBuilder {

    @ConfigProperty(name = "minio.s3.host")
    String s3_server;

    @ConfigProperty(name = "minio.s3.accesskey")
    String accessKey;

    @ConfigProperty(name = "minio.s3.secretkey")
    String secretKey;

    @Override
    public void configure() {
        from("aws-s3://teedjay-bucket")
            .to("bean:debugBean?method=printFirstLine")
        ;
    }

    @Produces
    @Named("AmazonS3Client")
    public AmazonS3 amazonS3Client() {
        // hack to use local Minio server, adapted from https://docs.min.io/docs/how-to-use-aws-sdk-for-java-with-minio-server.html
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(s3_server, Regions.US_EAST_1.name());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(endpointConfiguration)
            .withPathStyleAccessEnabled(true)
            .withClientConfiguration(clientConfiguration)
            .withCredentials(credentialsProvider)
            .build();
    }

}
