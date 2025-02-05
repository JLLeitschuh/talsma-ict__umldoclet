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
package net.sourceforge.plantuml.bpm;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class BpmDiagramFactory extends PSystemCommandFactory {

	public BpmDiagramFactory(DiagramType type) {
		super(DiagramType.BPM);
	}

	@Override
	protected List<Command> createCommands() {
		final List<Command> result = new ArrayList<>();
		result.add(new CommandDockedEvent());
		result.add(new CommandMerge());
		result.add(new CommandResume());
		result.add(new CommandGoto());
		result.add(new CommandNewBranch());
		result.add(new CommandElseBranch());
		result.add(new CommandEndBranch());
		return result;
	}

	@Override
	public AbstractPSystem createEmptyDiagram(ThemeStyle style, UmlSource source, ISkinSimple skinParam) {
		return new BpmDiagram(style, source);
	}

}
