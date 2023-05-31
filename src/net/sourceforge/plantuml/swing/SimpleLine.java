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
package net.sourceforge.plantuml.swing;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

import net.sourceforge.plantuml.GeneratedImage;

class SimpleLine implements Comparable<SimpleLine> {

	private final File file;
	private final GeneratedImage generatedImage;
	private final Future<List<GeneratedImage>> future;

	public static SimpleLine fromFuture(File file, Future<List<GeneratedImage>> future) {
		return new SimpleLine(file, null, future);
	}

	public static SimpleLine fromGeneratedImage(File file, GeneratedImage generatedImage) {
		return new SimpleLine(file, generatedImage, null);
	}

	private SimpleLine(File file, GeneratedImage generatedImage, Future<List<GeneratedImage>> future) {
		this.generatedImage = generatedImage;
		this.file = file;
		this.future = future;
	}

	public File getFile() {
		return file;
	}

	public boolean pendingAndFinished() {
		return generatedImage == null && future.isDone();
	}

	@Override
	public String toString() {
		if (generatedImage == null) {
			return file.getName() + " (...pending...)";
		}
		final StringBuilder sb = new StringBuilder(generatedImage.getPngFile().getName());
		sb.append(" ");
		sb.append(generatedImage.getDescription());
		return sb.toString();
	}

	public Future<List<GeneratedImage>> getFuture() {
		return future;
	}

	public int compareTo(SimpleLine other) {
		return toString().compareTo(other.toString());
	}

	public GeneratedImage getGeneratedImage() {
		return generatedImage;
	}

}
