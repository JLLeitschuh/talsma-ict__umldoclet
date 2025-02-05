/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
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
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml.project.lang;

import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.project.Failable;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class ComplementWithColorLink implements Something {

	public IRegex toRegex(String suffix) {
		final String optionalStyle = "(?:(dotted|bold|dashed)[%s]+)?";
		return new RegexLeaf("COMPLEMENT" + suffix,
				"with[%s]+" + optionalStyle + "(#?\\w+)[%s]+" + optionalStyle + "link");
	}

	public Failable<CenterBorderColor> getMe(GanttDiagram diagram, RegexResult arg, String suffix) {
		final String style0 = arg.get("COMPLEMENT" + suffix, 0);
		final String color1 = arg.get("COMPLEMENT" + suffix, 1);
		final String style2 = arg.get("COMPLEMENT" + suffix, 2);
		final HColor col1 = color1 == null ? null
				: diagram.getIHtmlColorSet().getColorOrWhite(diagram.getSkinParam().getThemeStyle(), color1);
		final String style = style0 == null ? style2 : style0;
		return Failable.ok(new CenterBorderColor(col1, col1, style));
	}
}
