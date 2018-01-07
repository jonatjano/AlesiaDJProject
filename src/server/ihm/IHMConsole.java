package server.ihm;

public class IHMConsole extends IHM
{
	// static final String RESET = "\u001B[0m";
	//
	// static final String BLACK_TEXT = "\u001B[30m";
	// static final String RED_TEXT = "\u001B[31m";
	// static final String GREEN_TEXT = "\u001B[32m";
	// static final String YELLOW_TEXT = "\u001B[33m";
	// static final String BLUE_TEXT = "\u001B[34m";
	// static final String PURPLE_TEXT = "\u001B[35m";
	// static final String CYAN_TEXT = "\u001B[36m";
	// static final String WHITE_TEXT = "\u001B[37m";
	//
	// static final String BLACK_BACKGROUND = "\u001B[40m";
	// static final String RED_BACKGROUND = "\u001B[41m";
	// static final String GREEN_BACKGROUND = "\u001B[42m";
	// static final String YELLOW_BACKGROUND = "\u001B[43m";
	// static final String BLUE_BACKGROUND = "\u001B[44m";
	// static final String PURPLE_BACKGROUND = "\u001B[45m";
	// static final String CYAN_BACKGROUND = "\u001B[46m";
	// static final String WHITE_BACKGROUND = "\u001B[47m";

	public void printMsg(String message, String... styles)
	{
		System.out.println(message);
	}
}
