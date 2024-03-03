package com.skilldistillery.jets.entities;

import com.ventura.util.ConsoleEffect;

public class FighterJet extends Jet implements IntfFighter, ConsoleEffect {



	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.print(reset + green + "Flying Out: " + reset);
	}

	@Override
	public String toString() {
		return "Model: " + getModel() + " | " + "Speed: " + getSpeed() + " | " + "Range: " + getRange() + " | "
				+ "Price: " + getPrice() + "]";
	}

	@Override
	public void dogFight() {
		System.out.println(" " + this.toString() + reset);

	}

}
