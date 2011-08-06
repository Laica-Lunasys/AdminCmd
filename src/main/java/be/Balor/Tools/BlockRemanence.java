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
package be.Balor.Tools;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class BlockRemanence {
	private Block block;
	private Material oldType;

	/**
	 * 
	 */
	public BlockRemanence(Block block) {
		this.block = block;
		this.oldType = Material.getMaterial(block.getTypeId());
	}

	public void returnToThePast() {
		this.setBlockType(oldType);
	}

	public void setBlockType(Material mat) {
		try {
			if (block != null)
				block.setType(mat);
		} catch (NullPointerException e1) {
		} catch (Exception e) {
			Logger.getLogger("Minecraft").info(
					"[AdminCmd] While replacing the block, an execption occured : ");
			Logger.getLogger("Minecraft").info(e.getLocalizedMessage());
		}

	}
}