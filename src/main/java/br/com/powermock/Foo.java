package br.com.powermock;

public class Foo {
	
	private int value;
	
    public String getBarValue() {
        Bar bar = new Bar();
        return bar.getTexto();
    }
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void methodCallingPrivate() {
		this.setValue(setThisValueToTen());
	}
	
	private int setThisValueToTen() {
		return 10;
	}
}