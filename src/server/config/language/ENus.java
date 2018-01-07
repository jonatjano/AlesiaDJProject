package server.config.language;

import java.util.HashMap;
import java.util.Map;

public class ENus extends AbstractLanguage
{
	@Override
	Map<String, String> createTranslationMap()
	{
		Map<String, String> ret = new HashMap<String, String>();

		ret.put("ConfigFileDoesNotExist", "The config file doesn't exists");
		ret.put("PleaseContactDevs", "Please contact devs in order for them to correct the problem ASAP, join the entire message in your issue");

		return ret;
	}
}
