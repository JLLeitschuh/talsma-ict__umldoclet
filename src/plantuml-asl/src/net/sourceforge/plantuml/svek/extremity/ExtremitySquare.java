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
package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.Point2D;

import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColor;

class ExtremitySquare extends Extremity {

	private final HColor backgroundColor;
	private final Point2D dest;
	private final double radius = 5;

	@Override
	public Point2D somePoint() {
		return dest;
	}

	public ExtremitySquare(Point2D p1, HColor backgroundColor) {
		this.dest = new Point2D.Double(p1.getX(), p1.getY());
		this.backgroundColor = backgroundColor;
	}

	public void drawU(UGraphic ug) {
		ug.apply(new UStroke(1.5)).apply(backgroundColor.bg()).apply(new UTranslate(dest.getX() - radius, dest.getY() - radius)).draw(new URectangle(radius * 2, radius * 2));
	}

}
