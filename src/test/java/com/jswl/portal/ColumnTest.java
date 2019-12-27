package com.jswl.portal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.jswl.portal.dto.ColumnTreeNodeDto;
import com.jswl.portal.service.impl.ColumnServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ColumnTest {
	
	@Autowired
	private ColumnServiceImpl column;
	
	@Test
	public void testFindColumnTreeNodeDto() {
		ColumnTreeNodeDto findColumnTreeNodeDto = column.findColumnTreeNodeDto(19, 4);
		System.out.println(findColumnTreeNodeDto);
	}
	
	
	
	
	
	
	
	
	
	
	
}
