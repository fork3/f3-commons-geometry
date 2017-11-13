/*
 * Copyright (c) 2010-2016 fork3
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package f3.commons.geometry;

import java.util.Iterator;

/**
 * @author n3k0nation
 * @since 14.06.2014
 */
public class Line2DIterator implements Iterator<Point2D>, Iterable<Point2D> {
	public final static int DEFAULT_PRECISION = 1;
	private final int precision;
	
	private final int sx, sy;
	private final int dx, dy;
	private final int lx, ly;
	
	private int x, y, error;
	
	public Line2DIterator(int x1, int y1, int x2, int y2, int precision) {
		this.precision = precision;

		sx = x1 < x2 ? precision : -precision;
		sy = y1 < y2 ? precision : -precision;
			 
		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);
		
		error = dx - dy;
		
		x = x1;
		y = y1;
		
		lx = x2;
		ly = y2;
	}
	
	public Line2DIterator(int x1, int y1, int x2, int y2) {
		this(x1, y1, x2, y2, DEFAULT_PRECISION);
	}
	
	public Line2DIterator(IPosition2D s, IPosition2D e) {
		this(s.getX(), s.getY(), e.getX(), e.getY(), DEFAULT_PRECISION);
	}
	
	@Override
	public Iterator<Point2D> iterator() {
		return this;
	}
	
	@Override
	public boolean hasNext() {
	    return Math.abs(x - lx) > precision || Math.abs(y - ly) > precision;
	}

	@Override
	public Point2D next() {
		int e2 = error << 1;
		if (e2 > -dy) {
			error -= dy;
			x += sx;
		}
		if (e2 < dx) {
			error += dx;
			y += sy;
		}
		
		return new Point2D(x, y);
	}

	@Override
	public void remove() {
		throw new AssertionError();
	}
}
