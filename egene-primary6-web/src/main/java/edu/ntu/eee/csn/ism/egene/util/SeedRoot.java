package edu.ntu.eee.csn.ism.egene.util;

public class SeedRoot {

	private String value = null;
	private int valueType = 0;

	public SeedRoot(String value, int valueType) {
		this.value = value;
		this.valueType = valueType;
	}

	public String getValue() {
		return this.value;
	}

	public int getValueType() {
		return this.valueType;
	}

	@Override
	public String toString() {
		return "[" + this.value + "," + this.valueType + "]";
	}

}
