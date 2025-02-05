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
package net.sourceforge.plantuml.ugraphic;

import net.sourceforge.plantuml.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import net.sourceforge.plantuml.Dimension2DDouble;

public class UTranslate implements UChange {

	private final double dx;
	private final double dy;

	@Override
	public String toString() {
		return "translate dx=" + dx + " dy=" + dy;
	}

	public static UTranslate none() {
		return new UTranslate(0, 0);
	}

	public UTranslate(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public static UTranslate dx(double dx) {
		return new UTranslate(dx, 0);
	}

	public static UTranslate dy(double dy) {
		return new UTranslate(0, dy);
	}

	public UTranslate(Point2D p) {
		this(p.getX(), p.getY());
	}

	public UTranslate() {
		this(0, 0);
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public boolean isAlmostSame(UTranslate other) {
		return this.dx == other.dx || this.dy == other.dy;
	}

	public Point2D getTranslated(Point2D p) {
		if (p == null) {
			return null;
		}
		return new Point2D.Double(p.getX() + dx, p.getY() + dy);
	}

	public Dimension2D getTranslated(Dimension2D dim) {
		return new Dimension2DDouble(dim.getWidth() + dx, dim.getHeight() + dy);
	}

	public UTranslate scaled(double scale) {
		return new UTranslate(dx * scale, dy * scale);
	}

	public UTranslate compose(UTranslate other) {
		return new UTranslate(dx + other.dx, dy + other.dy);
	}

	public UTranslate reverse() {
		return new UTranslate(-dx, -dy);
	}

	public Rectangle2D apply(Rectangle2D rect) {
		return new Rectangle2D.Double(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());
	}

	public UTranslate multiplyBy(double v) {
		return new UTranslate(dx * v, dy * v);
	}

	public UTranslate sym() {
		return new UTranslate(dy, dx);
	}

	public Point2D getPosition() {
		return new Point2D.Double(dx, dy);
	}

}
