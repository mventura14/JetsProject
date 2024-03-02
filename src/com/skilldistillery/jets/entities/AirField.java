package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ventura.util.ConsoleEffect;
import com.ventura.util.VerifyScanner;

public class AirField implements ConsoleEffect {

	private ArrayList<Jet> hanger = new ArrayList<>();

	public AirField() {
	};

	public AirField(String file) {
		hanger = this.getJetsFromFile(file);
	};

	public ArrayList<Jet> getJetsFromFile(String file) {
		int count = 1;
		try (BufferedReader bufIn = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = bufIn.readLine()) != null) {
				try {
					String[] jetInfo = line.split(",");

				String type = jetInfo[0];
				String model = jetInfo[1];
				double speed = Double.parseDouble(jetInfo[2]);
				int range = Integer.parseInt(jetInfo[3]);
				long price = Long.parseLong(String.join("", jetInfo[4].split("_")));

				switch (type.toLowerCase()) {
				case "passenger":
					hanger.add(new PassengerPlane(model, speed, range, price));
					break;
				case "fighter":
					hanger.add(new FighterJet(model, speed, range, price));
					break;
				case "cargo":
					hanger.add(new CargoPlane(model, speed, range, price));
					break;
				}

			} catch (Exception e) {
				System.out.println(yellow + "File error on line: " + count + reset);
			} finally {
				count++;
			}

			}

		} catch (IOException e) {
			System.err.println(e);
		}

		return hanger;
	}

	public ArrayList<Jet> getHanger() {
		return hanger;
	}

	public void setHanger(ArrayList<Jet> hanger) {
		this.hanger = hanger;
	}

	public void showHanger() {
		int counter = 0;
		for (Jet jet : hanger) {
			if (counter % 2 == 0) {
				System.out.print(bblackBg + black);
			} else {
				System.out.print(blackBg + bblack);
			}
			System.out.println(jet);
			counter++;
		}
		System.out.print(reset);
	}

	public void flyAllJets() {
		ArrayList<Jet> jets = this.getHanger();
		for (int i = 0; i < jets.size(); i++) {
			System.out.println(jets.get(i));
			jets.get(i).fly();
		}
	};

	public void showFastest() {
		ArrayList<Jet> jets = getHanger();
		Jet fastest = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getSpeed() > fastest.getSpeed()) {
				fastest = jet;
			}
		}
		System.out.println("Our Fastest jet is: " + fastest);

	};

	public void showLongestRange() {
		ArrayList<Jet> jets = getHanger();
		Jet best = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getRange() > best.getRange()) {
				best = jet;
			}
		}
		System.out.println("Our jet With the longest range is: " + best);
	}

	public void loadAllCargo() {
		ArrayList<Jet> jets = getHanger();
		int counter = 0;
		for (Jet jet : jets) {
			if (jet instanceof CargoPlane) {
				CargoPlane cargo = (CargoPlane) jet;
				cargo.loadCarrier(counter);
				counter++;
			}
		}

	};

	public void addJet(Scanner sc) {
		int type;
		String model;
		double speed;
		int range;
		long price;
		String message;

		System.out.printf("ADDING NEW JET%n");
		System.out.printf("%1s  Type selection: %11s%n", "", "");
		message = "1) PASSENGER | 2) FIGHTER | 3) CARGO \nSelection:  ";
		type = VerifyScanner.inputValidation(sc, "int", message);

		System.out.printf("Enter Model: %n");
		sc.nextLine();
		model = sc.nextLine();

		message = "Enter Speed: ";
		speed = VerifyScanner.inputValidation(sc, "double", message);

		System.out.printf("Enter Range: ");
		range = (int) sc.nextDouble();

		System.out.printf("Enter price [Numbers only]: ");
		price = VerifyScanner.inputValidation(sc, "long", message);

		switch (type) {
		case 1:
			hanger.add(new PassengerPlane(model, speed, range, price));
			break;
		case 2:
			hanger.add(new FighterJet(model, speed, range, price));
			break;
		case 3:
			hanger.add(new CargoPlane(model, speed, range, price));
			break;
		}

	};

	public void removeJet(Scanner sc) {
		hanger.remove(0);
	};
}
