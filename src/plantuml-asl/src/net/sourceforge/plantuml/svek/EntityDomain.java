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
package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class EntityDomain extends AbstractTextBlock implements TextBlock {

	private final double margin = 4;

	private final double radius = 12;
	private final double suppY = 2;
	private final SymbolContext symbolContext;

	public EntityDomain(SymbolContext symbolContext) {
		this.symbolContext = symbolContext;
	}

	public void drawU(UGraphic ug) {
		double x = 0;
		double y = 0;
		x += margin;
		y += margin;
		ug = symbolContext.apply(ug);
		final UEllipse circle = new UEllipse(radius * 2, radius * 2);
		circle.setDeltaShadow(symbolContext.getDeltaShadow());
		ug.apply(new UTranslate(x, y)).draw(circle);
		ug.apply(new UTranslate(x, y + 2 * radius + suppY)).draw(ULine.hline(2 * radius));
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		return new Dimension2DDouble(radius * 2 + 2 * margin, radius * 2 + 2 * margin);
	}

}
