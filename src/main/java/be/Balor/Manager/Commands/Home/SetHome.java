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
package be.Balor.Manager.Commands.Home;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.Balor.Manager.LocaleManager;
import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Exceptions.ActionNotPermitedException;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.PermissionManager;
import be.Balor.Player.ACPlayer;
import be.Balor.Tools.Type;
import be.Balor.Tools.CommandUtils.Users;
import be.Balor.bukkit.AdminCmd.ACHelper;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class SetHome extends HomeCommand {

	/**
	 * 
	 */
	public SetHome() {
		permNode = "admincmd.tp.home";
		cmdName = "bal_sethome";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Manager.ACCommands#execute(org.bukkit.command.CommandSender,
	 * java.lang.String[])
	 */
	@Override
	public void execute(final CommandSender sender, final CommandArgs args) throws ActionNotPermitedException, PlayerNotFound {
		if (Users.isPlayer(sender)) {
			final Player p = ((Player) sender);
			final be.Balor.Tools.Home home = getHome(sender, args.getString(0));
			if (home == null) {
				return;
			}
			if (sender.getName().equals(home.player)) {
				this.verifyCanExecute(sender, p);
			}
			final ACPlayer player = ACPlayer.getPlayer(home.player);
			final Set<String> tmp = player.getHomeList();
			final Location loc = p.getLocation();
			if (!tmp.contains(home.home) && !PermissionManager.hasPerm(p, "admincmd.admin.home", false)
					&& tmp.size() + 1 > ACHelper.getInstance().getLimit(p, Type.Limit.MAX_HOME)) {
				LocaleManager.sI18n(sender, "homeLimit");
				return;
			}
			player.setHome(home.home, loc);
			LocaleManager.sI18n(sender, "setMultiHome", "home", home.home);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.Balor.Manager.ACCommands#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(final String... args) {
		return args != null;
	}

}
