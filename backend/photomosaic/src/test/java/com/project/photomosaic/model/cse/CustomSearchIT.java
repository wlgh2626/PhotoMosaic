package com.project.photomosaic.model.cse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.model.cse.CustomSearch;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CSTestConfig.class })
public class CustomSearchIT {

	@Autowired
	@Qualifier("testSearch")
	private CustomSearch custom;

	@Autowired
	@Qualifier("onionLinks")
	private ArrayList<String> onionLinks;

	@Test
	public void searchOnion() {
		ArrayList<String> customLinks = custom.search("onion");

		for (String link : onionLinks) {
			System.out.println(link);
		}
		System.out.println("-----------");
		for (String link : customLinks) {
			System.out.println(link);
		}
		;

		assertThat(customLinks).containsAnyElementsOf(onionLinks);
	}

}
