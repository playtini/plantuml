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
package net.sourceforge.plantuml.project.core;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.project.Load;
import net.sourceforge.plantuml.project.lang.CenterBorderColor;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.url.Url;

public class TaskGroup extends AbstractTask implements Task {

	private final TaskGroup parent;
	private final List<Task> children = new ArrayList<>();

	public TaskGroup(TaskGroup parent, StyleBuilder styleBuilder, String name) {
		super(styleBuilder, new TaskCode(name));
		this.parent = parent;
	}

	public Day getStart() {
		Day min = null;
		for (Task child : children)
			if (min == null || min.compareTo(child.getStart()) > 0)
				min = child.getStart();

		if (min != null)
			return min;

		throw new UnsupportedOperationException();
	}

	public Day getEnd() {
		Day max = null;
		for (Task child : children)
			if (max == null || max.compareTo(child.getEnd()) < 0)
				max = child.getEnd();

		if (max != null)
			return max;

		throw new UnsupportedOperationException();
	}

	public void setStart(Day start) {
		throw new UnsupportedOperationException();
	}

	public void setEnd(Day end) {
		throw new UnsupportedOperationException();
	}

	public void setColors(CenterBorderColor... colors) {
		throw new UnsupportedOperationException();
	}

	public void addResource(Resource resource, int percentage) {
		throw new UnsupportedOperationException();
	}

	public Load getLoad() {
		throw new UnsupportedOperationException();
	}

	public void setLoad(Load load) {
		throw new UnsupportedOperationException();
	}

	public void setDiamond(boolean diamond) {
		throw new UnsupportedOperationException();
	}

	public boolean isDiamond() {
		throw new UnsupportedOperationException();
	}

	public void setCompletion(int completion) {
		throw new UnsupportedOperationException();
	}

	public void setUrl(Url url) {
		throw new UnsupportedOperationException();
	}

	public void addPause(Day pause) {
		throw new UnsupportedOperationException();
	}

	public void addPause(DayOfWeek pause) {
		throw new UnsupportedOperationException();
	}

	public void setNote(Display note) {
	}

	public void addTask(Task child) {
		children.add(child);
	}

	public final TaskGroup getParent() {
		return parent;
	}

}
