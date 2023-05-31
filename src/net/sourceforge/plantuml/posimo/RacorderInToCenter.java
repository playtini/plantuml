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
package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.klimt.geom.BezierUtils;
import net.sourceforge.plantuml.klimt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class RacorderInToCenter extends RacorderAbstract implements Racorder {

	@Override
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante) {
		final DotPath result = new DotPath();

		final XPoint2D center = new XPoint2D(rect.getCenterX(), rect.getCenterY());
		final XLine2D line = XLine2D.line(tangeante.getP1(), center);
		final XPoint2D inter = BezierUtils.intersect(line, rect);

		final XCubicCurve2D curv = new XCubicCurve2D(line.getX1(), line.getY1(), line.getX1(), line.getY1(),
				inter.getX(), inter.getY(), inter.getX(), inter.getY());
		return result.addAfter(curv);
	}

}
