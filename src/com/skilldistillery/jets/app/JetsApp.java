package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.ventura.util.ConsoleEffect;
import com.ventura.util.VerifyScanner;

public class JetsApp implements ConsoleEffect {
	private AirField a1 = new AirField("Jets.txt");

	public static void main(String[] args) {
		JetsApp app = new JetsApp();
		app.run();

	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {
			displayUserMenu();
			run = userSelection(run, sc);
		}
		sc.close();
	}

	public void displayUserMenu() {

		String[] menuOptions = { "Air Field -- Main Menu", "List Fleet", "Fly all jets", "View fastest jet",
				"View jet with longest range", "Load all Cargo Jets", "Dogfight!",
				"Add a jet to Fleet", "Remove a jet from Fleet", "Quit" };
		int count = 0;
		for (String option : menuOptions) {
			if (count == 0) {
				System.out.print(bmagenta);
				System.out.println(option);
				System.out.println(reset);
			} else {
				System.out.println(count + ") " + option);
			}
			count++;

		}
		System.out.println();
	}

	public boolean userSelection(boolean running, Scanner sc) {
		String message = bcyan + "Enter Selection: " + bcyan;
		running = true;
		int user = VerifyScanner.inputValidation(sc, "int", message, 0, 10);
		System.out.print(reset);
		switch (user) {
		case 1:
			a1.showHanger();
			break;
		case 2:
			a1.flyAllJets();
			break;
		case 3:
			a1.showFastest();
			break;
		case 4:
			a1.showLongestRange();
			break;
		case 5:
			a1.loadAllCargo();
			break;
		case 6:
			a1.dogfight();
			break;
		case 7:
			a1.addJet(sc);
			break;
		case 8:
			a1.removeJet(sc);
			break;
		case 9:
			System.out.println();
			System.out.println(bred + "Program Terminated");
			running = false;
			break;
		}

		System.out.print(reset);
		return running;

	};
}
