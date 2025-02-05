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
package net.sourceforge.plantuml.activitydiagram3;

import java.util.Collections;
import java.util.Set;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.FtileWithNotes;
import net.sourceforge.plantuml.activitydiagram3.gtile.Gtile;
import net.sourceforge.plantuml.activitydiagram3.gtile.GtileGroup;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.graphic.VerticalAlignment;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.sequencediagram.NoteType;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.ugraphic.color.HColorUtils;

public class InstructionGroup extends AbstractInstruction implements Instruction, InstructionCollection {

	private final InstructionList list;
	private final Instruction parent;
	private final HColor backColor;
	private final HColor borderColor;
	private final HColor titleColor;
	private final LinkRendering linkRendering;
	private final USymbol type;

	private final Display title;
	private final double roundCorner;
	private PositionedNote note = null;

	@Override
	public boolean containsBreak() {
		return list.containsBreak();
	}

	public InstructionGroup(Instruction parent, Display title, HColor backColor, HColor titleColor, Swimlane swimlane,
			HColor borderColor, LinkRendering linkRendering, USymbol type, double roundCorner) {
		this.list = new InstructionList(swimlane);
		this.type = type;
		this.linkRendering = linkRendering;
		this.parent = parent;
		this.title = title;
		this.borderColor = borderColor;
		this.backColor = backColor;
		this.titleColor = titleColor;
		this.roundCorner = roundCorner;
	}

	@Override
	public CommandExecutionResult add(Instruction ins) {
		return list.add(ins);
	}

	@Override
	public Gtile createGtile(ISkinParam skinParam, StringBounder stringBounder) {
		Gtile tmp = list.createGtile(skinParam, stringBounder);
		return new GtileGroup(tmp, title, null, HColorUtils.BLUE, backColor, titleColor, tmp.skinParam(), borderColor,
				type, roundCorner);
	}

	@Override
	public Ftile createFtile(FtileFactory factory) {
		Ftile tmp = list.createFtile(factory);
		if (note != null)
			tmp = new FtileWithNotes(tmp, Collections.singleton(note), factory.skinParam(), VerticalAlignment.CENTER);

		return factory.createGroup(tmp, title, backColor, titleColor, null, borderColor, type, roundCorner);
	}

	public Instruction getParent() {
		return parent;
	}

	@Override
	final public boolean kill() {
		return list.kill();
	}

	@Override
	public LinkRendering getInLinkRendering() {
		return linkRendering;
	}

	@Override
	public boolean addNote(Display note, NotePosition position, NoteType type, Colors colors, Swimlane swimlaneNote) {
		if (list.isEmpty()) {
			this.note = new PositionedNote(note, position, type, swimlaneNote, colors);
			return true;
		}
		return list.addNote(note, position, type, colors, swimlaneNote);
	}

	@Override
	public Set<Swimlane> getSwimlanes() {
		return list.getSwimlanes();
	}

	@Override
	public Swimlane getSwimlaneIn() {
		return list.getSwimlaneIn();
	}

	@Override
	public Swimlane getSwimlaneOut() {
		return list.getSwimlaneOut();
	}

	@Override
	public Instruction getLast() {
		return list.getLast();
	}

}
