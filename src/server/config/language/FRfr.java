package server.config.language;

import java.util.HashMap;
import java.util.Map;

/**
 * french Language from France (hope it is exact (I'm french ><))
 * @author jonatjano
 */
class FRfr extends AbstractLanguage
{
	/**
	 * method used to define translation pairs : key -> value
	 * @return the map containning the translations linked to the keys
	 */
	@Override
	Map<String, String> createTranslationMap()
	{
		Map<String, String> ret = new HashMap<String, String>();

		/************************/
		/* Config file messages */
		/************************/
		ret.put("ConfigFileDoesNotExist", "Le fichier de config entré n'existe pas");
		ret.put("ConfigFileCantBeRead", "Le fichier de config ne peut être lu, les valeurs par défaut seront utilisées");
		ret.put("ConfigDoesNotExist", "Il n'y as pas de valeur pour la configuration suivante :");
		ret.put("InvalidConfigValue", "La valeur n'est pas valide pour la configuration suivante :");
		ret.put("CreatingConfigFile", "Il va maintenant être créé");
		ret.put("FailedToCreateDefaultConfigFile", "Echec de la création du fichier de configuration veuillez réessayer");
		ret.put("FileIsCreatedModifyBeforeRestarting", "Le fichier de configuration à été créer. Merci de l'adapter à vos besoin avant de redémarrer le serveur");
		ret.put("IHMTypeNotValid", "Ce type d'interface n'est pas valide");

		/**********************/
		/*  General messages  */
		/**********************/
		ret.put("PleaseContactDevs", "Merci d'informer les développeurs pour que ce problème soit réglé au plus vite, merci d'inclure ce message d'erreur dans le message");
		ret.put("ServerIsGoingDown", "Le server est en cours d'extinction, veuillez patienter");

		return ret;
	}
}
