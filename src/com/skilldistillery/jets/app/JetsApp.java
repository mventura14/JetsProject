package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.ventura.util.ConsoleEffect;

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

	}

	public void displayUserMenu() {
		System.out.print(blackBg);
		System.out.printf("%32s%n", "");
		System.out.printf("1) List Fleet %18s%n", "");
		System.out.printf("2) Fly all jets %16s%n", "");
		System.out.printf("3) View fastest jet %12s%n", "");
		System.out.printf("4) View jet with longest range %1s%n", "");
		System.out.printf("5) Load all Cargo Jets %9s%n", "");
		System.out.printf("6) Dogfight! %19s%n", "");
		System.out.printf("7) Add a jet to Fleet %10s%n", "");
		System.out.printf("8) Remove a jet from Fleet %5s%n", "");
		System.out.printf("9) Quit %24s%n", "");
		System.out.printf("%32s%n", "");
		System.out.print(reset);
	};

	public boolean userSelection(boolean running, Scanner sc) {
		int user = sc.nextInt();
		running = false;
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
			a1.addJet(sc);
			break;
		case 7:
			a1.addJet(sc);
			break;
		case 8:
			System.out.println("Selected 8");
			break;
		case 9:
			System.out.println("Program ends");
			running = false;
			break;
		}
		System.out.println();
		return running;

	};
}
