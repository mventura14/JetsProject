package com.skilldistillery.jets.entities;

import com.ventura.util.ConsoleEffect;

public class PassengerPlane extends Jet implements ConsoleEffect {

	private static final String type = "Passenger";

	public PassengerPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);

	}

	@Override
	public void fly() {
		System.out.print(blackBg + green + type + " Flying Out: " + reset);
	}

	@Override
	public String toString() {
		return "Model: " + getModel() + "\t" + "Speed: " + getSpeed() + "\t" + "Range: "
				+ getRange() + "\t" + "Price: " + getPrice() + "]";
	}

}
