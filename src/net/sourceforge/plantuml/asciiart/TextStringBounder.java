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
package net.sourceforge.plantuml.asciiart;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.klimt.font.StringBounderRaw;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class TextStringBounder extends StringBounderRaw {

	public TextStringBounder() {
		super(FileFormat.gg.getFontRenderContext());
	}

	protected XDimension2D calculateDimensionInternal(UFont font, String text) {
		final int length1 = text.codePointCount(0, text.length());
		final int length2 = text.length();
		final int length3 = Wcwidth.length(text);
		return new XDimension2D(length2, 1);
	}

	@Override
	public boolean matchesProperty(String propertyName) {
		return false;
	}

}
