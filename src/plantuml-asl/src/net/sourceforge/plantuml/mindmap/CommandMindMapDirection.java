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
package net.sourceforge.plantuml.mindmap;

import net.sourceforge.plantuml.Direction;
import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandMindMapDirection extends SingleLineCommand2<MindMapDiagram> {

	public CommandMindMapDirection() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandMindMapDirection.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("[^*]*"), //
				new RegexLeaf("\\b"), //
				new RegexLeaf("DIRECTION", "(left|right)"), //
				new RegexLeaf("\\b"), //
				new RegexLeaf("[^*]*"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(MindMapDiagram diagram, LineLocation location, RegexResult arg) {
		final String direction = arg.get("DIRECTION", 0);
		diagram.setDefaultDirection(Direction.valueOf(direction.toUpperCase()));
		return CommandExecutionResult.ok();
	}

}
