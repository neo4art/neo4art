package other;

import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class NumberFormatTest {

		@Test
		public void shoudFormatWithUndercores() throws MalformedURLException{
		  
		  Assert.assertEquals("100.000.000", NumberFormat.getInstance(Locale.ITALY).format(100000000));
		  Assert.assertEquals("123.456.789", NumberFormat.getInstance(Locale.ITALY).format(123456789));
		}
}
