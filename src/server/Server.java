package server;

import server.config.Config;
import server.ihm.IHM;

/**
 * main class
 * @author jonatjano
 */
public class Server
{
	/**
	 * unique instance of {@link Server}
	 */
	private static Server instance = null;

	/**
	 * create {@link ServerSocket} with port and necessary instances for the server to work properly
	 * @param  port port used for {@link ServerSocket}
	 */
	private Server(int port)
	{
		IHM.printMessage("balec de ton port mec");
		IHM.printMessage("Language = " + Config.getConfig("Language"));
		IHM.printMessage("FillLanguage = " + Config.getConfig("FillLanguage"));
		IHM.printMessage("Port = " + port);
		IHM.printMessage("MOTD = " + Config.getConfig("MOTD"));
		IHM.printMessage("Interface = " + Config.getConfig("Interface"));
	}

	/**
	 * get the unique instance of {@link Server}, create it if not instantiated yet
	 * @return the instance of Server
	 */
	public static Server getInstance()
	{
		if (instance == null)
		{
			int port = 0;
			try
			{
				port = Integer.parseInt(Config.getConfig("Port"));
			}
			catch (Exception e)
			{
				// Can't fail since if the config is invalid, it is replaced by default value which is valid
				// see Config.readConfigsFromFile
			}
			instance = new Server(port);
		}
		return instance;
	}

	/**
	 * method used to launch a Server
	 * @param args only one arg is used as filepath to config. If not present using default file
	 */
	public static void main(String[] args)
	{
		// read config from given config file or the default one if none is found
		if (args.length != 0)
		{
			Config.readConfigsFromFile(args[0]);
		}
		else
		{
			Config.readConfigsFromFile();
		}

		// create the IHM according to config
		IHM.getInstance(Config.getConfig("Interface"));
		Server.getInstance();
	}
}
