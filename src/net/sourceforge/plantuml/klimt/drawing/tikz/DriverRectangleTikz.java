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
 */
package net.sourceforge.plantuml.klimt.drawing.tikz;

import net.sourceforge.plantuml.klimt.UParam;
import net.sourceforge.plantuml.klimt.color.ColorMapper;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorGradient;
import net.sourceforge.plantuml.klimt.drawing.UDriver;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.tikz.TikzGraphics;
import net.sourceforge.plantuml.utils.MathUtils;

public class DriverRectangleTikz implements UDriver<URectangle, TikzGraphics> {

	public void draw(URectangle rect, double x, double y, ColorMapper mapper, UParam param, TikzGraphics tikz) {
		final double width = rect.getWidth();
		final double height = rect.getHeight();
		final double r = MathUtils.min(rect.getRx(), rect.getRy(), width / 2, height / 2);

		final HColor back = param.getBackcolor();
		if (back instanceof HColorGradient) {
			final HColorGradient gr = (HColorGradient) back;
			tikz.setGradientColor(gr.getColor1(), gr.getColor2(), gr.getPolicy());
		} else {
			tikz.setFillColor(back);
		}
		tikz.setStrokeColor(param.getColor());
		tikz.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDashTikz());
		if (r == 0) {
			tikz.rectangle(x, y, width, height);
		} else {
			tikz.rectangleRound(x, y, width, height, r);
		}
	}

}
