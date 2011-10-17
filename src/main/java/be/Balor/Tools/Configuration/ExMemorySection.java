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
package be.Balor.Tools.Configuration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class ExMemorySection extends MemorySection {

	/**
	 * Create a {@link ConfigurationSection} if it not existing else return the
	 * existing one.
	 * 
	 * @param path
	 *            Path to create/get the section at.
	 * @return the ConfigurationSection
	 */
	public ConfigurationSection addSection(String path) {
		ConfigurationSection result = getConfigurationSection(path);
		if (result == null) {
			result = createSection(path);
		}
		return result;
	}

	/**
	 * Create an entry in the {@link ConfigurationSection} if it not existing
	 * else return the existing one.
	 * 
	 * @param path
	 *            Path to create/get the entry at.
	 */
	public void add(String path, Object value) {
		if (isSet(path))
			return;
		set(path, value);
	}

	/**
	 * Shortcut to remove an item by setting it null
	 * 
	 * @param path
	 *            Path to remove the entry at.
	 */
	public void remove(String path) {
		set(path, null);
	}
}