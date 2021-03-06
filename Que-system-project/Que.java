import java.util.Arrays;
import java.util.Scanner;

public class Que {  
	
	static String[] queueList;
	static final String EMPTY_SLOT = "-";
	
	enum Command { // Our command list
        ADD, // Adds people to the que
        PROCESS, // Processes next in que and moves everyone up 1 step in the que
        RESET,	// clears the que so it's empty from all people in line
        QUIT, // Quits the program
        HELP,// Posts a helptext with available commands
        UNKNOWN // Returns all other inputs to console and posts message
    }

    public static Command parseCommand(String userInput) {
    	//Our method that takes in the input string from the console and splits it apart, so the first string becomes our command.
    	//It also runs the command against our command list checker to trigger the right command.
    	
    	String commandString = userInput.split(" ")[0];
    	
    	if (commandString.equals("add")) {
    		return Command.ADD;
    	} else if (commandString.equals("process")) {
    		return Command.PROCESS;
    	} else if (commandString.equals("reset")) {
    		return Command.RESET;
    	} else if (commandString.equals("quit")) { 
    		return Command.QUIT;
    	} else if (commandString.equals("help")) { 
    		return Command.HELP;
    	} else {
    		return Command.UNKNOWN;
    	}

    }
    
    public static String[] parseArguments(String userInput) {
    	//Method that takes our input and divides our string into 1 command and separate arguments
    	//We will use this to add our arguments to our queue after the command has been triggered
    	
        String[] commandAndArguments = userInput.split(" ");
        String[] arguments = new String[commandAndArguments.length - 1];
        for (int i=1; i<commandAndArguments.length; i++) {
            arguments[i-1] = commandAndArguments[i];
        }
        
        return arguments;
    }
    
    public static void queRender() {
    	//Our rendering function that posts our Queue information clearly and properly visable.
    	
    	System.out.println("\n");
       	String replaceComma = Arrays.toString(queueList);
    	String replaceBracket = replaceComma.replaceAll(",", "|");
    	replaceBracket = replaceBracket.replaceFirst("]" , "|");
    	replaceBracket = replaceBracket.replaceFirst("\\[", "|");
    	System.out.println("CHECKOUT QUEUE STATUS:" + replaceBracket);
    	System.out.println("----------------------");
    		
    }
    
    public static void handleCommandAdd(String[] arguments) {
    	//Our method that handles "Add". It tests add against a varierty of conditions
    	//If the conditions are met, it will add the name(arguments) to the queue.
    	
    	String[] nameToAdd = new String[arguments.length];
    	int j = 0;
    	for (int i=0; i<arguments.length; i++) {
			 nameToAdd[i] = arguments[i];
    	}
    	
    	for(int p = 0; p<nameToAdd.length; p++) {
    		boolean isThereAFreeSlot = false;
    		
    	for (int i=0; i<queueList.length; i++) {
    		if (queueList[i] == EMPTY_SLOT && queueList[7] == EMPTY_SLOT) {
    		
    			if ( nameToAdd[p].length() < 10 && nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {
    					
    				isThereAFreeSlot = true;
    					queueList[i] = nameToAdd[p];
    					break;
    					
    				}else if (nameToAdd[p].length() > 10 && nameToAdd[p].equals(nameToAdd[p].toLowerCase())){
    					isThereAFreeSlot = true;
    					System.out.println("\n" +  nameToAdd[p] + " has to many letters, this name will not be added to the queue");
    					break;
    				}else if (nameToAdd[p].length() > 10 && !nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {
    					isThereAFreeSlot = true;
    					System.out.println("\n" +  nameToAdd[p] + " has to many letters and is also not strictly lowercase, this name will not be added to the queue");
    					break;
    				}else if (!nameToAdd[p].equals(nameToAdd[p].toLowerCase())) {
    					isThereAFreeSlot = true;
    					System.out.println("\n" +  nameToAdd[p] + " is not strictly lowercase, this name will not be added to the queue");
    					break;
    				}
    					if (p == nameToAdd.length-1) {
    						break;  				
    					}
    		
    			}
    			
    		}
    				if (!isThereAFreeSlot) {
    					System.out.println("\n" +  "Queue is full. " + nameToAdd[p] + " was not added to the queue");
			
    					}
    	}
    }	
   
  
    
    public static void handleCommandProcess(String[] args) {
    	//Our method that handles process. It removes the first one in line and moves all the other elements up 1 step.
    	
    	for (int i = 0; i<queueList.length-1; i++) {
    		queueList[i] = queueList[i+1];
    		
    	}
    		queueList[7] = EMPTY_SLOT;
    		System.out.println("\n" + "First person in the queue have been processed, Queue has been updated");
    		
    	
     }
    
    public static void handleCommandReset(String[] args) {   //Resets the full que with a simple loop.
    	//Our method that handles the reset. It clears the entire queue.
    	
    	for (int i = 0; i<queueList.length; i++)
    	queueList[i] = EMPTY_SLOT;
    	System.out.println("\n" + "Queue cleared. Please add new names");
	}
    
    public static void handleCommandQuit(String[] args) {
    	//Our simple quit method. It simply quits the program.
    	
    	System.out.println("\n" +  "Exiting Program, thank you for using this software.");
    	System.exit(0); 
	}
    
    public static void handleCommandHelp(String[] args) {
    	//Our method that handles our help command. It prints a help text for the available commands.
    	
    	String add = "add - Adds new person to queue. NOTE: names must be in lowercase and maximum of 10 letters";
    	String process = "process - Processes next person in queue";
    	String reset = "reset - Resets and clears the whole queue";
    	String quit = "quit - Exits the program";
    	System.out.println("\n" + "AVAILABLE COMMANDS:");
    	System.out.println("------------------:");
    	System.out.printf("%s\n%s\n%s\n%s\n\n", add, process, reset, quit);
	}
    
    public static void handleCommandUnknown(String[] args) {
    	//Method that handles any unknown commands that are put in the console.
    	
    	System.out.println("\n" + "Unknown command. Please try again, If you need a command list, please type help");
    }
    
    
    public static void main(String[] args) {
    	//Our main method. It defines what are elements are to be filled with and also calls our previous methods
    	//
    	
    	queueList = new String[] {EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT, EMPTY_SLOT};
    	
    	queRender();    	
    	
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
        	
        	
        	System.out.println("Please enter a command:");
        	System.out.print("> ");
        	
            String userInput = scanner.nextLine();
           
            Command command = parseCommand(userInput);
            
            
            String[] arguments = parseArguments(userInput); 
            
            if (command == Command.ADD) {
                handleCommandAdd(arguments); // Takes in the add command and runs the add method
            } else if (command == Command.PROCESS) {
            	handleCommandProcess(arguments); // Takes in the process command and runs the process method
            } else if (command == Command.RESET) {
            	handleCommandReset(arguments); // Takes in the reset command and runs the reset method
            } else if (command == Command.QUIT) {
            	handleCommandQuit(arguments); // Takes in the quit command and runs the quit method
            } else if (command == Command.HELP) {
            	handleCommandHelp(arguments); // Takes in the help command and runs the help method
            } else if (command == Command.UNKNOWN) {
            	handleCommandUnknown(arguments); // Takes in the help command and runs the add method
            }
            	queRender();
        }
    }
    
}