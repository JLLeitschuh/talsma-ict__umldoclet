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
package net.sourceforge.plantuml.svek.image;

import net.sourceforge.plantuml.Dimension2DDouble;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.cucadiagram.ILeaf;
import net.sourceforge.plantuml.cucadiagram.Rankdir;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.AbstractEntityImage;
import net.sourceforge.plantuml.svek.ShapeType;
import net.sourceforge.plantuml.ugraphic.Shadowable;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.color.HColor;
import net.sourceforge.plantuml.ugraphic.color.HColorNone;

public class EntityImageSynchroBar extends AbstractEntityImage {

	// private final SName styleName;

	public EntityImageSynchroBar(ILeaf entity, ISkinParam skinParam, SName styleName) {
		super(entity, skinParam);
		// this.styleName = styleName;
	}

	private StyleSignatureBasic getStyleSignature() {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.activityDiagram, SName.activityBar);
	}

	public Dimension2D calculateDimension(StringBounder stringBounder) {
		if (getSkinParam().getRankdir() == Rankdir.LEFT_TO_RIGHT)
			return new Dimension2DDouble(8, 80);

		return new Dimension2DDouble(80, 8);
	}

	final public void drawU(UGraphic ug) {
		final Dimension2D dim = calculateDimension(ug.getStringBounder());
		final Shadowable rect = new URectangle(dim.getWidth(), dim.getHeight());

		final Style style = getStyleSignature().withTOBECHANGED(getEntity().getStereotype())
				.getMergedStyle(getSkinParam().getCurrentStyleBuilder());
		final HColor color = style.value(PName.BackGroundColor).asColor(getSkinParam().getThemeStyle(),
				getSkinParam().getIHtmlColorSet());
		final double shadowing = style.value(PName.Shadowing).asDouble();

		rect.setDeltaShadow(shadowing);
		ug.apply(new HColorNone()).apply(color.bg()).draw(rect);
	}

	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

}
