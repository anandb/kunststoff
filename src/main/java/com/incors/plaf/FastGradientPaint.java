package com.incors.plaf;

/*
 * This code was contributed by Sebastian Ferreyra (sebastianf@citycolor.net).
 * It is published under the terms of the GNU Lesser General Public License.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.ColorModel;

public class FastGradientPaint implements Paint {
	int startColor, endColor;
	boolean isVertical;

	public FastGradientPaint( Color sc, Color ec, boolean isV ) {
		startColor = sc.getRGB();
		endColor = ec.getRGB();
		isVertical = isV;
	}

	@Override
	public synchronized PaintContext createContext( ColorModel cm, Rectangle r, Rectangle2D r2d, AffineTransform xform, RenderingHints hints ) {
		return new FastGradientPaintContext( cm, r, startColor, endColor, isVertical );
	}

	@Override
	public int getTransparency() {
		boolean opaque = ( ( startColor >> 24 ) & 0xFF ) == 0xFF
		                && ( ( endColor   >> 24 ) & 0xFF ) == 0xFF;
		return opaque ? OPAQUE : TRANSLUCENT;
	}
}

