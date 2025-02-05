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
package net.sourceforge.plantuml.sequencediagram;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class ParticipantEnglober {

	final private ParticipantEnglober parent;
	final private Display title;
	final private HColor boxColor;
	final private Stereotype stereotype;

	@Override
	public String toString() {
		return title.toString();
	}

	public static ParticipantEnglober build(Display title, HColor boxColor, Stereotype stereotype) {
		return new ParticipantEnglober(null, title, boxColor, stereotype);
	}

	public ParticipantEnglober newChild(Display title, HColor boxColor, Stereotype stereotype) {
		return new ParticipantEnglober(this, title, boxColor, stereotype);
	}

	private ParticipantEnglober(ParticipantEnglober parent, Display title, HColor boxColor, Stereotype stereotype) {
		this.parent = parent;
		this.title = title;
		this.boxColor = boxColor;
		this.stereotype = stereotype;
	}

	public final Display getTitle() {
		return title;
	}

	public final HColor getBoxColor() {
		return boxColor;
	}

	public final Stereotype getStereotype() {
		return stereotype;
	}

	public final ParticipantEnglober getParent() {
		return parent;
	}

	public final List<ParticipantEnglober> getGenealogy() {
		final LinkedList<ParticipantEnglober> result = new LinkedList<>();
		ParticipantEnglober current = this;
		while (current != null) {
			result.addFirst(current);
			current = current.getParent();
		}
		return Collections.unmodifiableList(result);
	}

}
