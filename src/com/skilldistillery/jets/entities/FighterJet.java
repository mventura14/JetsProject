package com.skilldistillery.jets.entities;

import com.ventura.util.ConsoleEffect;

public class FighterJet extends Jet implements IntfFighter, ConsoleEffect {

	private static final String type = "Fighter";

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.print(blackBg + green + type + " Flying Out:   " + reset);
	}

	@Override
	public String toString() {
		return "Model: " + getModel() + "\t" + "Speed: " + getSpeed() + "\t" + "Range: "
				+ getRange() + "\t" + "Price: " + getPrice() + "]";
	}

}
