package br.com.powermock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Foo.class)
public class FooTest {
    
	@Spy
	Foo foo = new Foo();
    
    @Mock
    private Bar mockBar;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.whenNew(Bar.class).withNoArguments().thenReturn(mockBar);
    }

    @Test
    public void testGetValue_mockConstrutor() throws Exception {
        when(mockBar.getTexto()).thenReturn("Success");
        assertEquals("Success",foo.getBarValue());
    }
    
    @Test
    public void testMockStatic() {
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 06);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.YEAR, 1995);
		PowerMockito.mockStatic(Calendar.class);
		PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
		
		System.out.println(Calendar.getInstance().getTime());
		
		PowerMockito.verifyStatic(Mockito.times(1));
		Calendar.getInstance();
    }
    
    @Test
    public void testMockPrivateMethod() throws Exception {
    	
    	PowerMockito.doReturn(10).when(foo, "setThisValueToTen");
    	foo.methodCallingPrivate();
    	PowerMockito.verifyPrivate(foo).invoke("setThisValueToTen");
		Assert.assertThat(foo.getValue(), is(10));
    }
    

    @Test
    public void testCallPrivateMethod() throws Exception {
    	int valor = Whitebox.invokeMethod(foo, "setThisValueToTen");
    	Assert.assertThat(valor, is(10));
    }
}