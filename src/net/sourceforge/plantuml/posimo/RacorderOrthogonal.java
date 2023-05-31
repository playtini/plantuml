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

import net.sourceforge.plantuml.klimt.geom.XCubicCurve2D;
import net.sourceforge.plantuml.klimt.geom.XLine2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.shape.DotPath;

public class RacorderOrthogonal extends RacorderAbstract implements Racorder {

	@Override
	public DotPath getRacordIn(XRectangle2D rect, XLine2D tangeante) {

		final XPoint2D in = tangeante.getP1();

		final DotPath result = new DotPath();
		XPoint2D inter = null;

		if (in.getX() > rect.getMinX() && in.getX() < rect.getMaxX()) {
			if (in.getY() < rect.getMinY())
				inter = new XPoint2D(in.getX(), rect.getMinY());
			else if (in.getY() > rect.getMaxY())
				inter = new XPoint2D(in.getX(), rect.getMaxY());
			else
				throw new IllegalArgumentException();

		} else if (in.getY() > rect.getMinY() && in.getY() < rect.getMaxY()) {
			if (in.getX() < rect.getMinX())
				inter = new XPoint2D(rect.getMinX(), in.getY());
			else if (in.getX() > rect.getMaxX())
				inter = new XPoint2D(rect.getMaxX(), in.getY());
			else
				throw new IllegalArgumentException();

		} else {
			final XPoint2D p1 = new XPoint2D(rect.getMinX(), rect.getMinY());
			final XPoint2D p2 = new XPoint2D(rect.getMaxX(), rect.getMinY());
			final XPoint2D p3 = new XPoint2D(rect.getMaxX(), rect.getMaxY());
			final XPoint2D p4 = new XPoint2D(rect.getMinX(), rect.getMaxY());

			inter = LineRectIntersection.getCloser(tangeante.getP1(), p1, p2, p3, p4);

		}

		final XCubicCurve2D curv = new XCubicCurve2D(tangeante.getX1(), tangeante.getY1(), tangeante.getX1(),
				tangeante.getY1(), inter.getX(), inter.getY(), inter.getX(), inter.getY());
		return result.addAfter(curv);
	}

}
