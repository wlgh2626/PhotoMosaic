package com.project.photomosaic.model;

import java.io.IOException;

import org.junit.Test;

import com.project.photomosaic.image.model.S3Connector;

public class S3ConnectorTest {
	@Test
	public void simpleConnectorTest() throws IOException {
		S3Connector s3 = new S3Connector("kurmd3fx");
	}
}
