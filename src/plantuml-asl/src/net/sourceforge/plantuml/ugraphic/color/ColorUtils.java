/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
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
package net.sourceforge.plantuml.ugraphic.color;

import java.awt.Color;

public class ColorUtils {

	static int getGrayScale(Color color) {
		final int grayScale = (int) (color.getRed() * .3 + color.getGreen() * .59 + color.getBlue() * .11);
		return grayScale;
	}

	public static Color getGrayScaleColor(Color color) {
		final int grayScale = getGrayScale(color);
		return new Color(grayScale, grayScale, grayScale);
	}

	public static Color getGrayScaleColorReverse(Color color) {
		final int grayScale = 255 - getGrayScale(color);
		return new Color(grayScale, grayScale, grayScale);
	}

	/*
	 * This code is still experimental. If you can improve it, please go ahead :-)
	 * 
	 * Many thanks to Alexei Boronine for the idea.
	 * 
	 * Some pointer to help you: https://www.hsluv.org/
	 * https://www.kuon.ch/post/2020-03-08-hsluv/
	 * https://www.boronine.com/2012/03/26/Color-Spaces-for-Human-Beings/
	 * 
	 */
	public static synchronized Color getReversed(Color color) {
		final int red = color.getRed();
		final int green = color.getGreen();
		final int blue = color.getBlue();

		final double hsluv[] = HUSLColorConverter.rgbToHsluv(new double[] { red / 256.0, green / 256.0, blue / 256.0 });

		final double h = hsluv[0];
		final double s = hsluv[1];
		double l = hsluv[2];

		if (s > 40 && s < 60) {
			if (l > 50) {
				l -= 50;
			} else if (l < 50) {
				l += 50;
			}
		} else {
			l = 100 - l;
		}

		final double rgb[] = HUSLColorConverter.hsluvToRgb(new double[] { h, s, l });

		final int red2 = to255(rgb[0]);
		final int green2 = to255(rgb[1]);
		final int blue2 = to255(rgb[2]);

		return new Color(red2, green2, blue2);
	}

	private static int to255(final double value) {
		final int result = (int) (255 * value);
		if (result < 0) {
			return 0;
		}
		if (result > 255) {
			return 255;
		}
		return result;
	}

}
