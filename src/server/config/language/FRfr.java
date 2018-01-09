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
		Map<String, String> tm = new HashMap<String, String>();

		/************************/
		/* Config file messages */
		/************************/
		tm.put("ConfigFileDoesNotExist", "Le fichier de config entré n'existe pas");
		tm.put("ConfigFileCantBeRead", "Le fichier de config ne peut être lu, les valeurs par défaut seront utilisées");
		tm.put("ConfigDoesNotExist", "Il n'y as pas de valeur pour la configuration suivante : ");
		tm.put("InvalidConfigValue", "La valeur n'est pas valide pour la configuration suivante : ");
		tm.put("CreatingConfigFile", "Il va maintenant être créé");
		tm.put("FailedToCreateDefaultConfigFile", "Echec de la création du fichier de configuration veuillez réessayer");
		tm.put("FileIsCreatedModifyBeforeRestarting", "Le fichier de configuration à été créer. Merci de l'adapter à vos besoin avant de redémarrer le serveur");
		tm.put("PrintingConfigs", "Affichage des configurations :");
		tm.put("CreatingConfigFileIn", "Création du fichier de configurations à l'emplacement : ");

		/************************/
		/*     IHM messages     */
		/************************/
		tm.put("IHMTypeNotValid", "Ce type d'interface n'est pas valide");

		/************************/
		/*   General messages   */
		/************************/
		tm.put("PleaseContactDevs", "Merci d'informer les développeurs pour que ce problème soit réglé au plus vite, merci d'inclure ce message d'erreur dans le message ainsi que le contexte dans lequel l'erreur est apparue");
		tm.put("ServerIsGoingDown", "Le server est en cours d'extinction, veuillez patienter");
		tm.put("FailedToCloseSocket", "Echec lors de la fermeture du la Connection, si le port n'est pas libéré proprement peut être sera-t-il nécéssaire de rallumer la machine");
		tm.put("UnknowError", "Une erreur inconnue est apparue, si le problème persiste après plusieurs essais, " + tm.get("PleaseContactDevs"));
		tm.put("ERROR", "ERROR : ");
		tm.put("INFO", "INFO : ");

		return tm;
	}
}
