package com.skilldistillery.jets.entities;

import com.ventura.util.ConsoleEffect;

public class CargoPlane extends Jet implements IntrfCargo, ConsoleEffect {

	private static final String type = "Cargo";

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(type + " flying");
		
	}

	@Override
	public void loadCarrier(int counter) {
		if (counter % 2 == 0) {
			System.out.println(
					green + blackBg + "Loading up cargo: " + reset + black + bblackBg + this.toString() + reset);

		} else {
			System.out.println(
					green + blackBg + "Loading up cargo: " + reset + bblack + blackBg + this.toString() + reset);

		}

	}

	@Override
	public String toString() {
		return "[Type: " + type + "\t\t" + "Model: " + getModel() + "\t" + "Speed: " + getSpeed() + "\t" + "Range: "
				+ getRange() + "\t" + "Price: " + getPrice() + "]";
	}

}
