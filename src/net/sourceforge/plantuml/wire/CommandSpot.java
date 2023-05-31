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
package net.sourceforge.plantuml.wire;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColorSet;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandSpot extends SingleLineCommand2<WireDiagram> {

	public CommandSpot() {
		super(false, getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSpot.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("spot"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("NAME", "([\\w][.\\w]*)"), //
				new RegexOptional(new RegexConcat(//
						new RegexLeaf("\\("), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("X", "(-?\\d+(%|%[-+]\\d+)?)"), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf(","), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("Y", "(-?\\d+(%|%[-+]\\d+)?)"), //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("\\)") //
				)), //
				new RegexOptional(new RegexConcat( //
						RegexLeaf.spaceZeroOrMore(), //
						new RegexLeaf("COLOR", "(#\\w+)?"))), //

				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(WireDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final String name = arg.get("NAME", 0);

		final String stringColor = arg.get("COLOR", 0);
		HColor color = null;
		if (stringColor != null)
			color = HColorSet.instance().getColor(stringColor);

		final String x = arg.get("X", 0);
		final String y = arg.get("Y", 0);

		return diagram.spot(name, color, x, y);
	}

}
