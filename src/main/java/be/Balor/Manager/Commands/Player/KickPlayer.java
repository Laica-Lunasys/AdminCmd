/************************************************************************
 * This file is part of AdminCmd.
 *
 * AdminCmd is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AdminCmd is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AdminCmd.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package be.Balor.Manager.Commands.Player;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Commands.CoreCommand;
import be.Balor.Tools.Utils;
import be.Balor.bukkit.AdminCmd.ACPluginManager;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class KickPlayer extends CoreCommand {

	/**
	 *
	 */
	public KickPlayer() {
		permNode = "admincmd.player.kick";
		cmdName = "bal_kick";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.ACCommands#execute(org.bukkit.command.CommandSender,
	 * java.lang.String[])
	 */
	@Override
	public void execute(CommandSender sender, CommandArgs args) {
		Player toKick = sender.getServer().getPlayer(args.getString(0));
		HashMap<String, String> replace = new HashMap<String, String>();
		String message = "";
		if (args.hasFlag('m')) {
			message = LocaleManager.getInstance().get("kickMessages", args.getValueFlag('m'),
					"player", toKick.getName());
		} else if (args.length >= 2)
			for (int i = 1; i < args.length; i++)
				message += args.getString(i) + " ";
		if (message == null || (message != null && message.isEmpty())) {
			message = "You have been kick by ";
			if (!Utils.isPlayer(sender, false))
				message += "Server Admin";
			else
				message += Utils.getPlayerName((Player) sender);
		}

		if (toKick != null) {
			final String finalmsg = message.trim();
			final Player finalToKick = toKick;
			replace.put("player", Utils.getPlayerName(toKick));
			ACPluginManager.scheduleSyncTask(new Runnable() {

				@Override
				public void run() {
					finalToKick.kickPlayer(finalmsg);

				}
			});
		} else
			Utils.sI18n(sender, "playerNotFound", "player", args.getString(0));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.Balor.Manager.ACCommands#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(String... args) {
		return args != null && args.length >= 1;
	}

}
