package server.config;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import server.config.language.Language;
import server.config.language.LanguageManager;
import server.ihm.IHM;

/**
 * class used to read, store and save configs
 * @author jonatjano
 */
public abstract class Config
{
	/**
	 * a map containing the default values for each config
	 */
	private static Map<String, String> defaultConfigMap;
	/**
	 * a map containing the values used for each config
	 */
	private static Map<String, String> configMap;

	/**
	 * initialize defaultConfigMap
	 */
	static
	{
		defaultConfigMap = new HashMap<String, String>();
		defaultConfigMap.put("Language", "ENus");
		defaultConfigMap.put("FillLanguage", "FRfr");
		defaultConfigMap.put("Port", "7777");
		defaultConfigMap.put("Interface", "Console");
		defaultConfigMap.put("MOTD", "Welcome on this server :)");
	}

	/**
	 * return config value for configName as a String,
	 * if the value should follow a pattern then the returned String follow it :
	 * 		for exemple the Port can be parsed into an {@link Integer}
	 * show an error if there is no value for this config
	 * @param  configName name of the config to get
	 * @return a String containning the value of the config
	 */
	public static String getConfig(String configName)
	{
		String configValue = configMap.get(configName);
		if (configValue == null)
		{
			IHM.printMessage(LanguageManager.get("ConfigDoesNotExist") + " " + configName + " " + LanguageManager.get("PleaseContactDevs"), IHM.ERROR);
		}
		return configValue;
	}

	/**
	 * call itself with the default config file as a parameter
	 */
	public static void readConfigsFromFile()
	{
		readConfigsFromFile("config.dtc");
	}

	/**
	 * read config from the config file
	 * the config file is validated by a call of getConfigFile(String filePath)
	 * this method read the file and store configs into the configMap
	 * then validate the configs stored ans replace them from those of defaultConfigMap if they're not valid
	 * @param filePath path to the config file relative to where you start the Server
	 */
	public static void readConfigsFromFile(String filePath)
	{
		// validate the file
		File configFile = getConfigFile(filePath);

		// create the configMap
		configMap = new HashMap<String, String>();

		// if this try failed it mean the file exists since it was validated but can't be read
		try
		{
			// going to scan over the whole file
			Scanner scan = new Scanner(configFile);
			while(scan.hasNext())
			{
				String line = scan.nextLine();
				// if a line starts with a # it's a comment in the config
				if (!line.startsWith("#"))
				{
					// else get the key:value pair
					String[] keyValueArr = line.split(":");
					// if keyValueArr.length < 2 it mean there is no value on this line
					if (keyValueArr.length >= 2)
					{
						// the value is everything after the first ':'
						configMap.put(keyValueArr[0], line.substring(line.indexOf(":") + 1));
					}
				}
			}
			// cleanly close the {@link Scanner}
			scan.close();
		}
		catch (Exception e)
		{
			// the file can't be read for any reason
			IHM.printMessage(LanguageManager.get("ConfigFileCantBeRead"), IHM.ERROR);
		}

		// we verify for each key in defaultConfigMap if there is a value in configMap
		for (String key : defaultConfigMap.keySet())
		{
			// get the value in configMap
			String value = configMap.get(key);
			// used to determine if value in configMap is valid or if it should be replaced by the default one
			boolean valueIsValid = true;
			// if there is no value replace it
			if (value == null)
			{
				valueIsValid = false;
			}
			// there is a value
 			else
			{
				// valid it according to it's type
				switch (key)
				{
					// test if it is a valid int
					case "Port":
						if (!value.matches("[0-9]*")) { valueIsValid = false; }
					break;

					// test if it is a valid Language
					case "Language":
					case "FillLanguage":
						// return null if the Language is not a valid one
						if (Language.getLanguage(value, null) == null) { valueIsValid = false; }
					break;

					// no default case since it corresponds to configs with no need to verify value
				}
			}

			// if the value is not valid replace it with default one
			if (!valueIsValid)
			{
				IHM.printMessage(LanguageManager.get("InvalidConfigValue") + key, IHM.ERROR);
				configMap.put(key, defaultConfigMap.get(key));
			}
		}
	}

	/**
	 * test if file exists, if it does return it else create it and stop the Server for the user to modify it
	 * @param  filePath the path to the config file
	 * @return the config file if it exists else nothing since the serve is stopped
	 */
	private static File getConfigFile(String filePath)
	{
		// use the default language since cnfig file is not read yet,
		// used in case of error or to tell the user about the server stopping if the file must be created
		LanguageManager.setActiveLanguage(Language.getLanguage(defaultConfigMap.get("Language")));
		LanguageManager.setFillLanguage(Language.getLanguage(defaultConfigMap.get("FillLanguage"), Language.FRfr));

		// seek the file with an absolute path if contains '/' or relative one otherwise
		File configFile;
		if (filePath.contains("/"))
		{
			configFile = new File(filePath);
		}
		else
		{
			configFile = new File("./" + filePath);
		}

		// if the file does not exists
		if(!configFile.exists())
		{
			// tell the user about it
			// and create it at the specified path indicated to the user
			IHM.printMessage(LanguageManager.get("ConfigFileDoesNotExist"), IHM.ERROR);
			IHM.printMessage(LanguageManager.get("CreatingConfigFileIn") + " " + configFile.getAbsolutePath());

			// try to create dirs and the file, if fail to do it prevent the user and stop the server
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

			// at this point file exists and is empty since it was just created
			// so we write default values in it
			writeDefaultConfig(configFile);
			// and inform the user about th server stopping
			IHM.printMessage(LanguageManager.get("FileIsCreatedModifyBeforeRestarting"));
			IHM.printMessage(LanguageManager.get("ServerIsGoingDown"));
			System.exit(0);
		}

		// if the file already existed and was read succesfully return it
		return configFile;
	}

	/**
	 * method used to write default config file
	 * called by getConfilFile() when the file didn't exists
	 * @param File configFile the config file
	 */
	private static void writeDefaultConfig(File configFile)
	{
		// get the defaults config for easier writing
		String language = defaultConfigMap.get("Language");
		String fillLanguage = defaultConfigMap.get("FillLanguage");
		String port = defaultConfigMap.get("Port");
		String ihmType = defaultConfigMap.get("Interface");
		String motd = defaultConfigMap.get("MOTD");

		// the printwriter may fail
		try
		{
			PrintWriter pw = new PrintWriter(configFile);

			/***********************************/
			/* starting comment to give        */
			/*        informations to the user */
			/***********************************/
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


			/***********************************/
			/* activeLanguage config           */
			/* see LanguageManager             */
			/***********************************/
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



			/***********************************/
			/* fillLanguage config             */
			/* see LanguageManager             */
			/***********************************/
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



			/***********************************/
			/* port config                     */
			/***********************************/
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



			/***********************************/
			/* type of IHM used config         */
			/* see IHM.getInstance(String)     */
			/***********************************/
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



			/***********************************/
			/* message of the day config       */
			/***********************************/
			pw.println();
			pw.println("# MOTD mean Message Of The Day");
			pw.println("# it is a message shown to client at connection");
			pw.println();
			pw.println("# default value : " + motd);
			pw.println("# every value is a valid value");
			pw.println();
			pw.println("MOTD:" + motd);
			pw.println();


			// cleanly close the PrintWriter
			pw.close();
		}
		catch (Exception e)
		{
			// prevent the user if PrintWriter failed
			IHM.printMessage(LanguageManager.get("FailedToCreateDefaultConfigFile"), IHM.ERROR);
			System.exit(0);
		}
	}
}
