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
package net.sourceforge.plantuml.sudoku;

import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;

public class PSystemSudoku extends AbstractPSystem {

	final private ISudoku sudoku;

	@Override
	final protected ImageData exportDiagramNow(OutputStream os, int num, FileFormatOption fileFormat)
			throws IOException {
		final GraphicsSudoku sud = new GraphicsSudoku(sudoku);
		// ::comment when __CORE__
		if (fileFormat.getFileFormat() == FileFormat.EPS)
			return sud.writeImageEps(os);

		if (fileFormat.getFileFormat() == FileFormat.LATEX
				|| fileFormat.getFileFormat() == FileFormat.LATEX_NO_PREAMBLE)
			return sud.writeImageLatex(os, fileFormat.getFileFormat());
		// ::done

		if (fileFormat.getFileFormat() == FileFormat.SVG)
			return sud.writeImageSvg(os);

		return sud.writeImagePng(os);
	}

	@Override
	public void exportDiagramGraphic(UGraphic ug) {
		final GraphicsSudoku sud = new GraphicsSudoku(sudoku);
		sud.drawInternal(ug);
	}

	public DiagramDescription getDescription() {
		return new DiagramDescription("(Sudoku)");
	}

	public PSystemSudoku(UmlSource source, Long seed) {
		super(source);
		sudoku = new SudokuDLX(seed);
	}

}
