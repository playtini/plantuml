/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2024, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;

public class CenterBorderColor {

	private final HColor center;
	private final HColor border;
	private final String style;

	public CenterBorderColor(HColor center, HColor border) {
		this(center, border, null);
	}

	public CenterBorderColor(HColor center, HColor border, String style) {
		this.center = center;
		this.border = border;
		this.style = style;
	}

	public UGraphic apply(UGraphic ug) {
		if (isOk() == false) {
			throw new IllegalStateException();
		}
		ug = ug.apply(center.bg());
		if (border == null) {
			ug = ug.apply(center);
		} else {
			ug = ug.apply(border);
		}
		return ug;
	}

	public boolean isOk() {
		return center != null;
	}

	public final HColor getCenter() {
		return center;
	}

	public final String getStyle() {
		return style;
	}

	public CenterBorderColor unlinearTo(CenterBorderColor other, int completion) {
		final HColor newCenter = HColors.unlinear(this.center, other.center, completion);
		final HColor newBorder = HColors.unlinear(this.border, other.border, completion);

		return new CenterBorderColor(newCenter, newBorder, style);
	}
}
