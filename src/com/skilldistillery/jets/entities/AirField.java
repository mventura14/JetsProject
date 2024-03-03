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
					System.out.println(reset + byellow + "File error on line: " + count + reset);
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
		System.out.println();
		for (Jet jet : hanger) {
			if (counter % 2 == 0) {
				System.out.print(bblackBg + black);
			} else {
				System.out.print(blackBg + bblack);
			}
			System.out.println((counter + 1) + ") " + jet);
			counter++;
		}
		System.out.println(reset);
	}

	public void flyAllJets() {
		ArrayList<Jet> jets = this.getHanger();
		System.out.println();
		for (int i = 0; i < jets.size(); i++) {
			jets.get(i).fly();

			if (i % 2 == 0) {
				System.out.print(bblackBg + black);
				System.out.println("  " + jets.get(i));

			} else {
				System.out.print(blackBg + bblack);
				System.out.println("  " + jets.get(i));
			}

			System.out.print(reset);

		}
		System.out.println();
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
		System.out.printf("%s%s%37s%n", bblackBg, black, "");
		System.out.printf("%11s ADDING NEW JET%11s%n", "", "");
		System.out.printf("%37s%n", "");
		System.out.printf("%11s  Select Type: %11s%n", "", "");
		message = black + "1) PASSENGER | 2) FIGHTER | 3) CARGO " + blue + "\nEnter Selection:  ";
		type = VerifyScanner.inputValidation(sc, "int", message, 0, 4);

		System.out.printf("Enter Model: ");
		sc.nextLine();
		model = sc.nextLine().trim();

		message = black + "Enter Speed (MPH): " + blue;
		speed = VerifyScanner.inputValidation(sc, "double", message);

		message = black + "Enter Range (Miles): " + blue;
		range = VerifyScanner.inputValidation(sc, "int", message);

		message = black + "Enter price [Numbers only]: " + blue;
		price = VerifyScanner.inputValidation(sc, "long", message);

		System.out.println(reset);
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
		int counter = 0;
		int userInput;
		System.out.println();
		System.out.printf("%s%s%28s Remove A Jet %28s%s%n", redBg, black, "", "", reset);
		System.out.printf("%s%s0) Cancel operation %50s%s%n", greenBg, black, "", reset);
		for (Jet jet : hanger) {
			if (counter % 2 == 0) {
				System.out.print(bblackBg + black);
			} else {
				System.out.print(blackBg + bblack);
			}

			System.out.println((counter + 1) + ") " + jet);
			counter++;
		}
		String message = redBg + black + "Enter Jet number you wish to remove: ";
		userInput = VerifyScanner.inputValidation(sc, "int", message, -1, hanger.size() + 1);

		if (userInput == 0) {
			System.out.printf("%s%s%26s Removal Canceled %26s%n", greenBg, black, "", "");
			System.out.println();
			return;
		}
		System.out.println(reset);
		hanger.remove(userInput - 1);
		System.out.println();
	}
}
