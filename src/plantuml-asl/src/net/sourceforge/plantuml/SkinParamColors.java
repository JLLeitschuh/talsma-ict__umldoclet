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
package net.sourceforge.plantuml;

import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.graphic.color.ColorType;
import net.sourceforge.plantuml.graphic.color.Colors;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class SkinParamColors extends SkinParamDelegator {

	public final Colors getColors() {
		return colors;
	}

	final private Colors colors;

	public SkinParamColors(ISkinParam skinParam, Colors colors) {
		super(skinParam);
		this.colors = colors;
	}

	@Override
	public String toString() {
		return "SkinParamColors::" + colors;
	}

	@Override
	public boolean shadowing(Stereotype stereotype) {
		if (colors.getShadowing() == null) {
			return super.shadowing(stereotype);
		}
		return colors.getShadowing();
	}

	@Override
	public HColor getFontHtmlColor(Stereotype stereotype, FontParam... param) {
		final HColor value = colors.getColor(ColorType.TEXT);
		if (value == null) {
			return super.getFontHtmlColor(stereotype, param);
		}
		return value;
	}

	@Override
	public HColor getHtmlColor(ColorParam param, Stereotype stereotype, boolean clickable) {
		final ColorType type = param.getColorType();
		if (type == null) {
			return super.getHtmlColor(param, stereotype, clickable);
		}
		final HColor value = colors.getColor(type);
		if (value != null) {
			return value;
		}
		assert value == null;
		return super.getHtmlColor(param, stereotype, clickable);
	}

}
