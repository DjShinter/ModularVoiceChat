<p align="center">
  <a href="https://github.com/Nathanael2611/ModularVoiceChat">
    <img src="micro.png" alt="micro" width="80" height="80">
  </a>

  <h3 align="center">ModularVoiceChat</h3>

  <p align="center">
    Open-sourced proximity voice chat for Minecraft 1.12.2.
    <br />
    <a href="https://github.com/Nathanael2611/ModularVoiceChat/issues">Report Bug</a>
  </p>
</p>


**ModularVoiceChat** is a voice chat mod for **Minecraft**, Initially created for an **RP** server, I decided to make this project public, in order to make it an open-source mod open to everyone. This mod includes everything that a voice chat mod on Minecraft should have.
Namely the possiblity to choose the radius in which the voice will be broadcast, as well as being able to change it's input device, and output device and you can change the volume of your microphone volume and other players volume. What's more, ModularVoiceChat has the unique feature of using the Opus Audio Codec instead of the other voice-related mods that use a different audio codec, This fast and efficient audio codec allows flawless audio quality, within the limits of the microphone used, a lot of the configuration is  available in the server configuration and client configuration.




French Translation Below
# Informations
ModularVoiceChat est un mod de chat-vocal sur minecraft. Initialement créé pour un serveur RP, j'ai décidé de rendre ce projet public, afin d'en faire un mod open source ouvert à tous.
Ce mod inclut donc initialement tout ce qu'un mod de chat vocal sur Minecraft devrait avoir. A savoir la possibilité de choisir le rayon dans lequel la voix va être diffusée, la possibilité de changer ses péréphériques d'entrées, ainsi que de sortie audio, ainsi que le volume de ces derniers.
Plus encore, ModularVoiceChat a la particularité d'utiliser le codec audio Opus, à l'instar de ses concurrents. Ce codec audio rapide et efficace permet une qualité audio généralement irréprochable, dans la limite du micro utilisé, bien sûr.
Bien entendu, une multitudes d'options sont disponibles dans la configuration du serveur afin de s'adapter nativement à tout besoin.

# Plus qu'un mod, une API
En effet, ModularVoiceChat dispose d'une API. Il est donc facile de développer des addons sur celui-ci.
Ladite API s'utilise via divers events vous permettant de manipuler et de transmettre l'audio selon différentes conditions, en voici la liste.

Les events serveurs
 - **HearDistanceEvent**: Il est déclenché lorsque le mod va tenter de connaître la distance maximale à laquelle un joueur peut entendre. Vous permettant ainsi de la modifier.
 - **VoiceDispatchEvent**: Il est déclenché avant l'envoi des packets audios du joueur qui parle, aux différents joueurs, et vous donne accès à une multitude de fonctions pour dispatcher l'audio selon vos souhaits.
 - **VoiceServerStartEvent**: Il est délenché lorsque le serveur vocal se lance. Il vous permet de définir un IVoiceDispatcher custom afin de créer votre propre système de voix.

Les events client:
 - **StartVoiceRecordEvent**: Il est déclenché juste avant que la voix commence à être enregistrée, puis envoyée au serveur.
 - **StopVoiceRecordEvent**: Il est déclenché juste après que la voix aie fini d'être envoyée. (quand le joueur arrête de parler)
 - **VoiceRecordEvent**: Il est déclenché juste avant qu'un packet audio soit envoyé au serveur. Vous permettant de manipuler l'audio à vos souhaits.
 - **VoicePlayEvent**: Il est déclenché juste après qu'un packet audio soit reçu. Vous permettant de modifier l'audio à vos souhaits avant que celui-ci soit lu!

Une liste plus détaillée des events et de leur utilisation est disponible sur le wiki du mod.

# Téléchargement
Avant que le développement du mod ne touche à sa fin, ou tout du moins qu'une première release soit publiée, celui-ci ne sera disponible que sur le discord!

# Rejoindre la communauté
En effet, le mod possède un serveur discord sur lequel vous pouvez venir discuter, et demander de l'aide par rapport à celui-ci.
Je vous invite à le rejoindre!
--> https://discord.gg/kSu7eFE
