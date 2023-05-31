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
package net.sourceforge.plantuml.oregon;

import static net.sourceforge.plantuml.klimt.shape.GraphicStrings.createGreenOnBlackMonospaced;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.atmp.ImageBuilder;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.PlainDiagram;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.shape.UDrawable;

public class PSystemOregon extends PlainDiagram {

	private Screen screen;
	private List<String> inputs;

	@Deprecated
	public PSystemOregon(UmlSource source, Keyboard keyboard) {
		super(source);
		final BasicGame game = new OregonBasicGame();
		try {
			game.run(keyboard);
			this.screen = game.getScreen();
			// this.screen = new Screen();
			// screen.print("Game ended??");
		} catch (NoInputException e) {
			this.screen = game.getScreen();
		}
	}

	@Override
	public ImageBuilder createImageBuilder(FileFormatOption fileFormatOption) throws IOException {
		return super.createImageBuilder(fileFormatOption).blackBackcolor();
	}

	public PSystemOregon(UmlSource source) {
		super(source);
		this.inputs = new ArrayList<>();
	}

	public void add(String line) {
		if (StringUtils.isNotEmpty(line)) {
			inputs.add(line);
		}
	}

	private Screen getScreen() {
		if (screen == null) {
			final Keyboard keyboard = new KeyboardList(inputs);
			final BasicGame game = new OregonBasicGame();
			try {
				game.run(keyboard);
				this.screen = game.getScreen();
				// this.screen = new Screen();
				// screen.print("Game ended??");
			} catch (NoInputException e) {
				this.screen = game.getScreen();
			}
		}
		return screen;
	}

	@Override
	protected UDrawable getRootDrawable(FileFormatOption fileFormatOption) throws IOException {
		return createGreenOnBlackMonospaced(getScreen().getLines());
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(The Oregon Trail)");
	}

}
