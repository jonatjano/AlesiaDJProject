package server.config;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import server.config.language.Language;
import server.config.language.LanguageManager;
import server.ihm.IHM;

public abstract class Config
{
	private static Map<String, String> defaultConfigMap;
	private static Map<String, String> configMap;

	static
	{
		defaultConfigMap = new HashMap<String, String>();
		defaultConfigMap.put("Language", "ENus");
		defaultConfigMap.put("FillLanguage", "FRfr");
		defaultConfigMap.put("Port", "7777");
		defaultConfigMap.put("Interface", "Console");
		defaultConfigMap.put("MOTD", "Welcome on this server :)");
	}

	public static String getConfig(String configName)
	{
		String configValue = configMap.get(configName);
		if (configValue == null)
		{
			IHM.printMessage(LanguageManager.get("ConfigDoesNotExist") + " " + configName + " " + LanguageManager.get("PleaseContactDevs"), IHM.ERROR);
		}
		return configValue;
	}

	public static void readConfigsFromFile()
	{
		readConfigsFromFile("config.dtc");
	}

	public static void readConfigsFromFile(String filePath)
	{
		File configFile = getConfigFile(filePath);

		configMap = new HashMap<String, String>();

		try
		{
			Scanner scan = new Scanner(configFile);
			while(scan.hasNext())
			{
				String line = scan.nextLine();
				if (!line.startsWith("#"))
				{
					String[] keyValueArr = line.split(":");
					if (keyValueArr.length >= 2)
					{
						String value = "";
						for (int i = 1; i < keyValueArr.length; i++)
						{
							if (i != 1) { value += ":"; }
							value += keyValueArr[i];
						}
						configMap.put(keyValueArr[0], value);
					}
				}
			}
			scan.close();
		}
		catch (Exception e)
		{
			IHM.printMessage(LanguageManager.get("ConfigFileCantBeRead"), IHM.ERROR);
		}

		for (String key : defaultConfigMap.keySet())
		{
			String value = configMap.get(key);
			boolean valueIsValid = true;
			if (value == null)
			{
				valueIsValid = false;
			}
 			else
			{
				switch (key)
				{
					// test if it is a valid int
					case "Port":
						if (!value.matches("[0-9]*")) { valueIsValid = false; }
					break;

					// test if it is a valid Language
					case "Language":
					case "FillLanguage":
						if (Language.getLanguage(value, null) == null) { valueIsValid = false; }
					break;

					// no default case since it corresponds to configs with no need to verify value
				}
			}

			if (!valueIsValid)
			{
				IHM.printMessage(LanguageManager.get("InvalidConfigValue") + key, IHM.ERROR);
				configMap.put(key, defaultConfigMap.get(key));
			}
		}
	}

	private static File getConfigFile(String filePath)
	{
		LanguageManager.setActiveLanguage(Language.getLanguage(defaultConfigMap.get("Language")));
		LanguageManager.setFillLanguage(Language.getLanguage(defaultConfigMap.get("FillLanguage"), Language.FRfr));
		File configFile;
		if (filePath.contains("/"))
		{
			configFile = new File(filePath);
		}
		else
		{
			configFile = new File("./" + filePath);
		}

		if(!configFile.exists())
		{
			IHM.printMessage(LanguageManager.get("ConfigFileDoesNotExist"), IHM.ERROR);
			IHM.printMessage(LanguageManager.get("CreatingConfigFileIn") + " " + configFile.getAbsolutePath());

			try
			{
				configFile.getParentFile().mkdirs();
				if (!configFile.createNewFile())
				{
					IHM.printMessage(LanguageManager.get("FailedToCreateDefaultConfigFile"), IHM.ERROR);
					System.exit(0);
				}
			}
			catch (Exception e)
			{
				IHM.printMessage(LanguageManager.get("FailedToCreateDefaultConfigFile"), IHM.ERROR);
				e.printStackTrace();
				System.exit(0);
			}

			writeDefaultConfig(configFile);
			IHM.printMessage(LanguageManager.get("FileIsCreatedModifyBeforeRestarting"));
			IHM.printMessage(LanguageManager.get("ServerIsGoingDown"));
			System.exit(0);
		}

		return configFile;
	}

	private static void writeDefaultConfig(File configFile)
	{
		String language = defaultConfigMap.get("Language");
		String fillLanguage = defaultConfigMap.get("FillLanguage");
		String port = defaultConfigMap.get("Port");
		String ihmType = defaultConfigMap.get("Interface");
		String motd = defaultConfigMap.get("MOTD");

		try
		{
			PrintWriter pw = new PrintWriter(configFile);

			pw.println("# Hello this is the default config file");
			pw.println("# in this file are the default values for config");
			pw.println("# every missing config from your final file is going to take these values");
			pw.println("# if you want to recreate, you just need to delete it");
			pw.println();
			pw.println("# every line starting with a '#' is not read");
			pw.println("# config are written as 'propertyName:propertyValue'");
			pw.println("# you can use ':' in value if needed");
			pw.println("# don't add space around the ':' nor modify propertyName or the config could be invalidated");
			pw.println("# take care of uppercase letters in values ;)");
			pw.println("# starting now are the actual configs");
			pw.println();



			pw.println();
			pw.println("# Language correspond to the language you want the printed messages to be");
			pw.println("# Error messages will be written using the default language before this file is fully loaded");
			pw.println("# default value : " + language);
			pw.println();
			pw.println("# possible values are : ");
			for (Language loopLanguage : Language.values())
			{
				pw.println("# 	- " + loopLanguage.name());
			}
			pw.println();
			pw.println("Language:" + language);
			pw.println();



			pw.println();
			pw.println("# FillLanguage correspond to the language used when the translation is missing for the chosen language");
			pw.println("# It is a bad idea to use the value used for Language");
			pw.println();
			pw.println("# default value : " + fillLanguage);
			pw.println("# possible values are : ");
			for (Language loopLanguage : Language.values())
			{
				pw.println("# 	- " + loopLanguage.name());
			}
			pw.println();
			pw.println("FillLanguage:" + fillLanguage);
			pw.println();



			pw.println();
			pw.println("# Port correspond to the port on which the server is opened");
			pw.println("# Online server need this port to be opened in the router to do that contact your internet access provider");
			pw.println("# !!! WARNING !!! opening a port may cause security issues in your network don't do it if not needed");
			pw.println();
			pw.println("# default value : " + port);
			pw.println("# possible values are integers from 1000 to 65000");
			pw.println("# other values may work but be careful there is a maximum of one software per port");
			pw.println("# change here if you got an error stating port is already used");
			pw.println();
			pw.println("Port:" + port);
			pw.println();



			pw.println();
			pw.println("# Interface correspond to the type of interface you want to use");
			pw.println("# 'Window' or 'Graphic' will create a window allowing you see most of the needed values");
			pw.println("# 'Console' or 'terminal' will use the terminal");
			pw.println();
			pw.println("# default value : " + ihmType);
			pw.println("# possible values are :");
			pw.println("# 	- Window");
			pw.println("# 	- Graphic");
			pw.println("# 	- Console");
			pw.println("# 	- Terminal");
			pw.println();
			pw.println("Interface:" + ihmType);
			pw.println();



			pw.println();
			pw.println("# MOTD mean Message Of The Day");
			pw.println("# it is a message shown to client at connection");
			pw.println();
			pw.println("# default value : " + motd);
			pw.println("# every value is a valid value");
			pw.println();
			pw.println("MOTD:" + motd);
			pw.println();



			pw.close();
		}
		catch (Exception e)
		{
			IHM.printMessage(LanguageManager.get("FailedToCreateDefaultConfigFile"), IHM.ERROR);
			System.exit(0);
		}
	}
}
