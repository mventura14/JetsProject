package com.skilldistillery.jets.entities;

public class FighterJet extends Jet implements IntfFighter {

	private static final String type = "Fighter";

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(type + " flying");
	}

	@Override
	public String toString() {
		return "[Type: " + type + "\t\t" + "Model: " + getModel() + "\t" + "Speed: " + getSpeed() + "\t" + "Range: "
				+ getRange() + "\t" + "Price: " + getPrice() + "]";
	}

}
