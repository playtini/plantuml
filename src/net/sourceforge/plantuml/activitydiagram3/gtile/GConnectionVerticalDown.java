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

import net.sourceforge.plantuml.activitydiagram3.ftile.Snake;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;

public class GConnectionVerticalDown extends GAbstractConnection {

	private final TextBlock textBlock;
	private final UTranslate pos1;
	private final UTranslate pos2;

	public GConnectionVerticalDown(UTranslate pos1, GPoint gpoint1, UTranslate pos2, GPoint gpoint2,
			TextBlock textBlock) {
		super(gpoint1, gpoint2);
		this.textBlock = textBlock;
		this.pos1 = pos1;
		this.pos2 = pos2;
		// See FtileFactoryDelegatorAssembly
	}

	@Override
	public void drawTranslate(UGraphic ug, UTranslate translate1, UTranslate translate2) {
		final Snake snake = Snake.create(skinParam(), getInLinkRenderingColor(), skinParam().arrows().asToDown())
				.withLabel(textBlock, HorizontalAlignment.LEFT);
		final XPoint2D p1 = pos1.getTranslated(gpoint1.getPoint2D());
		final XPoint2D p2 = pos2.getTranslated(gpoint2.getPoint2D());
		final XPoint2D mp1a = translate1.getTranslated(p1);
		final XPoint2D mp2b = translate2.getTranslated(p2);
		final double middle = (mp1a.getY() + mp2b.getY()) / 2.0;
		snake.addPoint(mp1a);
		snake.addPoint(mp1a.getX(), middle);
		snake.addPoint(mp2b.getX(), middle);
		snake.addPoint(mp2b);
		ug.draw(snake);

	}

	@Override
	public void drawU(UGraphic ug) {
		final Snake snake = Snake.create(skinParam(), getInLinkRenderingColor(), skinParam().arrows().asToDown())
				.withLabel(textBlock, HorizontalAlignment.LEFT);
		final XPoint2D p1 = pos1.getTranslated(gpoint1.getPoint2D());
		final XPoint2D p2 = pos2.getTranslated(gpoint2.getPoint2D());
		snake.addPoint(p1);
		snake.addPoint(p2);
		ug.draw(snake);
	}

//	// DUPLICATE 4561
//	private Rainbow getInLinkRenderingColor() {
//		Rainbow color;
//		final ISkinParam skinParam = gpoint1.getGtile().skinParam();
//		if (UseStyle.useBetaStyle()) {
//			final Style style = getDefaultStyleDefinitionArrow().getMergedStyle(skinParam.getCurrentStyleBuilder());
//			color = Rainbow.build(style, skinParam.getIHtmlColorSet(), skinParam.getThemeStyle());
//		} else
//			color = Rainbow.build(skinParam);
////		final LinkRendering linkRendering = tile.getInLinkRendering();
////		if (linkRendering == null) {
////			if (UseStyle.useBetaStyle()) {
////				final Style style = getDefaultStyleDefinitionArrow()
////						.getMergedStyle(skinParam().getCurrentStyleBuilder());
////				return Rainbow.build(style, skinParam().getIHtmlColorSet(), skinParam().getThemeStyle());
////			} else {
////				color = Rainbow.build(skinParam());
////			}
////		} else {
////			color = linkRendering.getRainbow();
////		}
////		if (color.size() == 0) {
////			if (UseStyle.useBetaStyle()) {
////				final Style style = getDefaultStyleDefinitionArrow()
////						.getMergedStyle(skinParam().getCurrentStyleBuilder());
////				return Rainbow.build(style, skinParam().getIHtmlColorSet(), skinParam().getThemeStyle());
////			} else {
////				color = Rainbow.build(skinParam());
////			}
////		}
//		return color;
//	}

//	@Override
//	public void drawTranslate(UGraphic ug, UTranslate translate1, UTranslate translate2) {
//		final Snake snake = Snake.create(color, Arrows.asToDown()).withLabel(textBlock, HorizontalAlignment.LEFT);
//		final XPoint2D mp1a = translate1.getTranslated(p1);
//		final XPoint2D mp2b = translate2.getTranslated(p2);
//		final double middle = (mp1a.getY() + mp2b.getY()) / 2.0;
//		snake.addPoint(mp1a);
//		snake.addPoint(mp1a.getX(), middle);
//		snake.addPoint(mp2b.getX(), middle);
//		snake.addPoint(mp2b);
//		ug.draw(snake);
//
//	}

}