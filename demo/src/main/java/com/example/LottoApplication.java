package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LottoApplication {

    private Stage currentStage = Stage.LOTTO;
    private LottoModel model;

    public LottoApplication() {
        model = new LottoModel();
    }

    public void run() throws Exception { 
		
		displayWelcome();
		
		boolean exitRequested = false;
		
		while(!exitRequested) {
			String userInput = getUserInput();
			exitRequested = processUserInput(userInput);
		}
		
		System.out.println("Auf Wiedersehen Herr Keller");
	}

    private boolean processUserInput(String userInput) throws IOException {
        switch (userInput) {
            case "Exit":
                return true;
            case "Lotto":
                currentStage = Stage.LOTTO;
                displayOptions(1);
                break;
            case "Eurojackpot":
                currentStage = Stage.EURO;
                displayOptions(1);
                break;
            case "Alle Loeschen":
                model.deleteNumbers();
                break;
            case "Loeschen":
                displayWhichNumber();
                model.deleteNumber(getUserInputAsInt());
                break;
            case "Einfuegen":
                displayWhichNumber();
                model.addNumber(getUserInputAsInt());
                break;
            case "Tippreihe":
                
                break;
            case "Alle Anzeigen":
                model.getUnluckyNumbers();
                displayUnluckyNumbers();
                break;
            case "":
                currentStage = Stage.LOTTO;
                break;
            default:
                displayError(userInput + 
                " ist keine richtige Eingabe\n Geben Sie 'Lotto', 'Eurojackpot', 'Alle Loeschen', 'Loeschen', \n'Einfuegen', 'Tippreihe', 'Alle Anzeigen' oder 'Exit' ein");
                break;
        }
        return false;
    }

    

    private String getUserInput() throws IOException {
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		return consoleInput.readLine();
    }

    // No Error Handeling
    private int getUserInputAsInt() throws IOException {
        return Integer.valueOf(getUserInput());
    }
    
    private void displayUnluckyNumbers() {
        System.out.println(model.getUnluckyNumbers());
    }

    private void displayError(String message) {
        System.out.println("Es ist ein Fehler aufgetreten");
        System.out.println("Fehler: " + message);
        displayOptions(-1);
    }

    private void displayWelcome() {
        System.out.println("Willkommen Herr Keller");
        displayOptions(0);
    }

    private void displayOptions(int option) {
        System.out.println("Falls das Programm geschlossen werden soll, schreiben Sie 'Exit'");
        if (option == 0) {
            System.out.println("Welches Spiel wollen Sie wählen?");
        } else if (option == 1) {
            System.out.println("Sie können mit 'Alle Anzeigen' alle Unglückszahlen anzeigen lassen");
            System.out.println("Sie können mit 'Alle Löschen' alle Unglückszahlen löschen");
            System.out.println("Sie können mit 'Löschen' eine Unglückszahl löschen");
            System.out.println("Sie können mit 'Einfügen' eine Unglückszahl einfügen");
            System.out.println("Sie können mit 'Tippreihe' eine Tippreihe generieren");
        }
        
    }

    private void displayWhichNumber() {
        System.out.println("Welche Nummer?");
    }


}
