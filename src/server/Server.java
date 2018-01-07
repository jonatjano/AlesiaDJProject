package server;

import server.config.Config;
import server.ihm.IHM;

public class Server
{
	private static Server instance = null;

	private Server(int port)
	{
		IHM.printMessage("balec de ton port mec");
		IHM.printMessage("Language = " + Config.getConfig("Language"));
		IHM.printMessage("FillLanguage = " + Config.getConfig("FillLanguage"));
		IHM.printMessage("Port = " + port);
		IHM.printMessage("MOTD = " + Config.getConfig("MOTD"));
		IHM.printMessage("Interface = " + Config.getConfig("Interface"));
	}

	public static Server getInstance()
	{
		if (instance == null)
		{
			int port = 0;
			try
			{
				port = Integer.parseInt(Config.getConfig("Port"));
			}
			catch (Exception e) { /* Can't fail since if the config is invalid, it is replaced by default value which is valid */}
			instance = new Server(port);
		}
		return instance;
	}

	public static void main(String[] args)
	{
		if (args.length != 0)
		{
			Config.readConfigsFromFile(args[0]);
		}
		else
		{
			Config.readConfigsFromFile();
		}

		IHM.getInstance(Config.getConfig("Interface"));
		Server.getInstance();
	}
}
