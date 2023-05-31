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
package net.sourceforge.plantuml.activitydiagram3.gtile;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.activitydiagram3.Branch;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.style.ISkinParam;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.StyleSignatureBasic;

public class GtileIfAlone extends GtileTopDown3 {

	private static final double SUPP_WIDTH = 15;

	public GtileIfAlone(Swimlane swimlane, Gtile gtile, Branch branch0) {
		super(getShape1(swimlane, branch0, gtile.getStringBounder(), gtile.skinParam()), gtile,
				getShape2(swimlane, branch0.getColor(), gtile.getStringBounder(), gtile.skinParam()));

	}

	private static Gtile getShape1(Swimlane swimlane, Branch branch0, StringBounder stringBounder,
			ISkinParam skinParam) {
		GtileHexagonInside tmp = Gtiles.hexagonInside(swimlane, stringBounder, skinParam,
				getDefaultStyleDefinitionDiamond(), branch0.getColor(), branch0.getLabelTest());
		final TextBlock tmp0 = branch0.getTextBlockPositive();
		return Gtiles.withSouthMargin(tmp.withSouthLabel(tmp0), 20);
	}

	private static Gtile getShape2(Swimlane swimlane, HColor color, StringBounder stringBounder, ISkinParam skinParam) {
		final AbstractGtileRoot tmp = Gtiles.diamondEmpty(swimlane, stringBounder, skinParam,
				getDefaultStyleDefinitionDiamond(), color);
		return Gtiles.withNorthMargin(tmp, 20);
	}

	final public StyleSignatureBasic getDefaultStyleDefinitionActivity() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activity);
	}

	final static public StyleSignatureBasic getDefaultStyleDefinitionDiamond() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activity, SName.diamond);
	}

	final public StyleSignatureBasic getDefaultStyleDefinitionArrow() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.arrow);
	}

	@Override
	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return super.calculateDimension(stringBounder).delta(SUPP_WIDTH, 0);
	}

	@Override
	public Collection<GConnection> getInnerConnections() {

		final GConnection arrow1 = new GConnectionVerticalDown(getPos1(), tile1.getGPoint(GPoint.SOUTH_HOOK), getPos2(),
				tile2.getGPoint(GPoint.NORTH_HOOK), TextBlockUtils.EMPTY_TEXT_BLOCK);
		final GConnection arrow2 = new GConnectionVerticalDown(getPos2(), tile2.getGPoint(GPoint.SOUTH_HOOK), getPos3(),
				tile3.getGPoint(GPoint.NORTH_HOOK), TextBlockUtils.EMPTY_TEXT_BLOCK);

		final double xright = calculateDimension(stringBounder).getWidth();

		final GConnection arrow3 = new GConnectionSideThenVerticalThenSide(getPos1(), tile1.getGPoint(GPoint.EAST_HOOK),
				getPos3(), tile3.getGPoint(GPoint.EAST_HOOK), xright, TextBlockUtils.EMPTY_TEXT_BLOCK);

		return Arrays.asList(arrow1, arrow2, arrow3);
	}

}
