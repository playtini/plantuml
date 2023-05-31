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
package net.sourceforge.plantuml.board;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.skin.UmlDiagramType;

public class BoardDiagram extends UmlDiagram {

	private final List<Activity> activities = new ArrayList<>();

	public DiagramDescription getDescription() {
		return new DiagramDescription("Board");
	}

	public BoardDiagram(UmlSource source) {
		super(source, UmlDiagramType.BOARD, null);
	}

	@Override
	protected ImageData exportDiagramInternal(OutputStream os, int index, FileFormatOption fileFormatOption)
			throws IOException {

		return createImageBuilder(fileFormatOption).drawable(getTextBlock()).write(os);
	}

	@Override
	protected TextBlock getTextBlock() {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				drawMe(ug);
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				final double width = 200;
				final double height = 200;
				return new XDimension2D(width, height);

			}
		};
	}

	private void drawMe(final UGraphic ug) {
		UGraphic mug = ug;
		for (Activity activity : activities) {
			activity.drawMe(mug);
			mug = mug.apply(UTranslate.dx(activity.getFullWidth()));
		}

		final ULine line = ULine.hline(getFullWidth());

		for (int i = 0; i < getMaxStage(); i++) {
			final double dy = (i + 1) * PostIt.getHeight() - 10;
			ug.apply(HColors.BLACK).apply(new UStroke(5, 5, 0.5)).apply(UTranslate.dy(dy)).draw(line);
		}
	}

	private double getFullWidth() {
		double width = 0;
		for (Activity activity : activities) {
			width += activity.getFullWidth();
		}
		return width;
	}

	private int getMaxStage() {
		int max = 0;
		for (Activity activity : activities) {
			max = Math.max(max, activity.getMaxStage());
		}
		return max;
	}

	private Activity getLastActivity() {
		return this.activities.get(this.activities.size() - 1);
	}

	public CommandExecutionResult addLine(String plus, String label) {
		if (plus.length() == 0) {
			final Activity activity = new Activity(this, label, getSkinParam());
			this.activities.add(activity);
			return CommandExecutionResult.ok();
		}
		getLastActivity().addRelease(plus.length(), label);
		return CommandExecutionResult.ok();
	}

}
