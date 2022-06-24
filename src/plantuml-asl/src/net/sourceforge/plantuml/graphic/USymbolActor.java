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
package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.skin.ActorStyle;
import net.sourceforge.plantuml.style.SName;

class USymbolActor extends USymbolSimpleAbstract {

	private final ActorStyle actorStyle;

	public USymbolActor(ActorStyle actorStyle) {
		this.actorStyle = actorStyle;

	}

	@Override
	public SName getSName() {
		return SName.actor;
	}

	@Override
	protected TextBlock getDrawing(SymbolContext symbolContext) {
		// final double deltaShadow = symbolContext.isShadowing() ? 4.0 : 0.0;
		// final SymbolContext tmp =
		// symbolContext.withDeltaShadow(deltaShadow).withStroke(new UStroke(2));
		return actorStyle.getTextBlock(symbolContext);
	}

}
