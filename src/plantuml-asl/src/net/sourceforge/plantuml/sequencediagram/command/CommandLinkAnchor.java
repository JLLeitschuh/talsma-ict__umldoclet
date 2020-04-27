/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
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
package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;

public class CommandLinkAnchor extends SingleLineCommand2<SequenceDiagram> {

	public CommandLinkAnchor() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLinkAnchor.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ANCHOR1", "\\{([\\p{L}0-9_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINK", "\\<-\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("ANCHOR2", "\\{([\\p{L}0-9_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), new RegexLeaf("MESSAGE", "(?::[%s]*(.*))?"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String anchor1 = arg.get("ANCHOR1", 0);
		final String anchor2 = arg.get("ANCHOR2", 0);
		final String message = arg.get("MESSAGE", 0);
		return diagram.linkAnchor(anchor1, anchor2, message);
	}

}
