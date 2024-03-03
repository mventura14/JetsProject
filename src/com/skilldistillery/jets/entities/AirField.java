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
					System.err.println("File error on line: " + count + reset);

				} finally {
					count++;

				}

			}
			System.out.println();
		} catch (IOException e) {
			System.err.println("File not found, Generating empty airfield \n");
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

	public ArrayList<Jet> getFastestJets() {
		ArrayList<Jet> jets = getHanger();
		ArrayList<Jet> fastJets = new ArrayList<>();

		Jet fastest = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getSpeed() >= fastest.getSpeed()) {
				fastJets.add(jet);
				if (jet.getSpeed() > fastest.getSpeed()) {
					fastJets = new ArrayList<>();
					fastJets.add(jet);
					fastest = jet;
				}
			}
		}
		return fastJets;
	}

	public void showFastest() {

		System.out.printf("%n%s%20s", bmagenta, "");
		System.out.printf("%sOur Fastest jet(s) %15s%n%n", underline, "");
		System.out.print(reset);
		int count = 0;
		for (Jet jet : getFastestJets()) {
			if (count % 2 == 0) {
				System.out.print(bblackBg + black);
			} else {
				System.out.print(blackBg + white);
			}

			System.out.println(" " + jet + " ");
			count++;
		}

		System.out.println();
	};

	public ArrayList<Jet> getLongestRangeJets() {
		ArrayList<Jet> jets = getHanger();
		ArrayList<Jet> fastJets = new ArrayList<>();

		Jet fastest = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getRange() >= fastest.getRange()) {
				fastJets.add(jet);
				if (jet.getRange() > fastest.getRange()) {
					fastJets = new ArrayList<>();
					fastJets.add(jet);
					fastest = jet;
				}
			}
		}
		return fastJets;
	}
	
	public void showLongestRange() {
		System.out.printf("%n%s%20s", bmagenta, "");
		System.out.printf("%sOur long range jet(s) %15s%n%n", underline, "");
		System.out.print(reset);
		int count = 0;
		for (Jet jet : getLongestRangeJets()) {
			if (count % 2 == 0) {
				System.out.print(bblackBg + black);
			} else {
				System.out.print(blackBg + white);
			}

			System.out.println(" " + jet + " ");
			count++;
		}

		System.out.println();
	};

	public void loadAllCargo() {
		System.out.println();
		ArrayList<Jet> jets = getHanger();
		int counter = 0;
		for (Jet jet : jets) {
			if (jet instanceof IntrfCargo) {
				CargoPlane cargo = (CargoPlane) jet;

				System.out.print(green + "Loading up cargo ");

				if (counter % 2 == 0) {
					System.out.print(black + bblackBg);
				} else {
					System.out.print(bblack + blackBg);
				}

				cargo.loadCarrier();
				counter++;
			}
		}
		System.out.println(reset);
	};

	public void dogfight() {
		System.out.println();
		ArrayList<Jet> jets = getHanger();
		int counter = 0;
		for (Jet jet : jets) {

			if (jet instanceof IntfFighter) {
				FighterJet fighter = (FighterJet) jet;
				System.out.print(green + "Heading out, engaging targets " + reset);

				if (counter % 2 == 0) {
					System.out.print(black + bblackBg);
				} else {
					System.out.print(bblack + blackBg);
				}
				fighter.dogFight();
				System.out.print(reset);

				counter++;
			}
		}
		System.out.println();
	}

	public void addJet(Scanner sc) {
		int type;
		String model;
		double speed;
		int range;
		long price;
		String message;
		System.out.println(greenBg + black);
		System.out.printf("%11s ADDING NEW JET%11s%s%n", "", "", reset);
		System.out.println(magenta);
		System.out.printf("%11s  Select Type: %11s%n", "", "");
		message = white + "\n1) PASSENGER | 2) FIGHTER | 3) CARGO " + bcyan + "\n\nEnter Selection [0. to cancel]:  ";
		type = VerifyScanner.inputValidation(sc, "int", message, -1, 4);

		if (type == 0) {
			System.out.print(greenBg + black);
			System.out.printf("%8s Opreation Canceled %8s%s%n", "", "", reset);
			System.out.println(reset);
			return;

		}
		System.out.printf("Enter Model: ");
		sc.nextLine();
		model = sc.nextLine().trim();

		message = "Enter Speed (MPH): ";
		speed = VerifyScanner.inputValidation(sc, "double", message);

		message = "Enter Range (Miles): ";
		range = VerifyScanner.inputValidation(sc, "int", message);

		message = "Enter price [Numbers only]: ";
		price = VerifyScanner.inputValidation(sc, "long", message);

		System.out.println(reset);
		System.out.printf("%11s New Jet Added %11s%s%n", "", "", reset);

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
		String message = reset + bcyan + "\nEnter Jet number you wish to remove: ";
		userInput = VerifyScanner.inputValidation(sc, "int", message, -1, hanger.size() + 1);

		if (userInput == 0) {
			System.out.printf("%s%s%26s Removal Canceled %26s%n", greenBg, black, "", "");
			System.out.println();
			return;
		}
		System.out.print(reset);
		hanger.remove(userInput - 1);
		System.out.printf("%s%s%28s Jet  Removed %28s%n", redBg, black, "", "");
		System.out.println();
	}
}
