package com.ventura.util;

public class ColoredOutput implements ConsoleEffect {

	public static void main(String[] args) {
		// Print text in different colors

		System.out.println(bred + "Good to be here" + reset);
		System.out.printf("%s%s%s %23s gerwvr %23s %s%n", redBg, blue, bold, "-dgewvdreqevdsage", "-", reset);
		System.out.println(redBg + blue + bold + "  " + reset + "				" + redBg + blue + bold
				+ "  " + reset);
		System.out.println(redBg + blue + bold + "  " + reset + "				" + redBg + blue + bold + "  " + reset);
		System.out.println(redBg + blue + bold + "  " + reset + "				" + redBg + blue + bold + "  " + reset);
		System.out.println(redBg + blue + bold + "  " + reset + "				" + redBg + blue + bold + "  " + reset);
		System.out.println(redBg + blue + bold + "  " + reset + "				" + redBg + blue + bold + "  " + reset);
		System.out.printf("%s%s%s %24s %s%n", redBg, blue, bold, " ", reset);
		String output = String.format("%20s", "Good Game");
		System.out.print(output);
		System.out.print(output);

	}

	public static void testOuput(String color) {
		switch (color) {
		case "red":
			break;

		}

	};

}
