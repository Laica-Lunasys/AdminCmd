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
package be.Balor.Player;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import be.Balor.Tools.Files.YmlFilter;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class ACPlayerFactory {
	final String directory;
	private final Set<String> existingPlayers = new HashSet<String>();

	/**
	 * 
	 */
	public ACPlayerFactory(String directory) {
		this.directory = directory;
		File[] players = YmlFilter.listRecursively(new File(directory), 1);
		for (File player : players) {
			String name = player.getName();
			existingPlayers.add(name.substring(0, name.lastIndexOf('.')));
		}
	}

	void addExistingPlayer(String player) {
		existingPlayers.add(player);
	}

	ACPlayer createPlayer(String playername) {
		if (!existingPlayers.contains(playername))
			return new EmptyPlayer(playername);
		else if (directory != null)
			return new FilePlayer(directory, playername);
		else
			return null;
	}

}