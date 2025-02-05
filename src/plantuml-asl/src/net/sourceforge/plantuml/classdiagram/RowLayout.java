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
package net.sourceforge.plantuml.classdiagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class RowLayout implements UDrawable {

	private final List<TextBlock> all = new ArrayList<>();

	public void addLeaf(TextBlock entityImageClass) {
		this.all.add(entityImageClass);
	}

	public double getHeight(StringBounder stringBounder) {
		double y = 0;
		for (TextBlock leaf : all) {
			y = Math.max(y, leaf.calculateDimension(stringBounder).getHeight());
		}
		return y;
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		for (TextBlock leaf : all) {
			leaf.drawU(ug.apply(UTranslate.dx(x)));
			x += leaf.calculateDimension(ug.getStringBounder()).getWidth() + 20;
		}

	}

}
