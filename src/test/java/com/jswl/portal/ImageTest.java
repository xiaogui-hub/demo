package com.jswl.portal;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jswl.portal.entity.Image;
import com.jswl.portal.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageTest {
	
	@Autowired
	private ImageService imageService;
	
	@Test
	public void findAllImageTest() {
		List<Image> findAllImages = imageService.findAllImages();
		for (Image image : findAllImages) {
			System.err.println(image);
		}
	}
	
	
	
	
	
	
	
	
	
	
}
