package server.config.language;

import java.util.HashMap;
import java.util.Map;

/**
 * english Language from the USA (may be inexact)
 * @author jonatjano
 */
class ENus extends AbstractLanguage
{
	/**
	 * method used to define translation pairs : key -> value
	 * @return the map containning the translations linked to the keys
	 */
	@Override
	Map<String, String> createTranslationMap()
	{
		Map<String, String> ret = new HashMap<String, String>();

		ret.put("ConfigFileDoesNotExist", "The config file doesn't exists");
		ret.put("PleaseContactDevs", "Please contact devs in order for them to correct the problem ASAP, join the entire message in your issue");

		return ret;
	}
}
