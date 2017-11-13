/*
 * Copyright (C) 2004-2016 L2J Server
 *
 * This file is part of L2J Server.
 *
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package f3.commons.geometry;

import java.util.Iterator;

/**
 * 
 * @author FBIagent
 * @author n3k0nation
 * @since 14.06.2014
 */
public class Line3DIterator implements Iterator<Point3D>, Iterable<Point3D> {
	public final static int DEFAULT_PRECISION = 1;
	
	private int srcX;
	private int srcY;
	private int srcZ;
	private final int dstX;
	private final int dstY;
	private final int dstZ;
	private final long dx;
	private final long dy;
	private final long dz;
	private final long sx;
	private final long sy;
	private final long sz;
	private long error;
	private long error2;
	private boolean zstep = false;
	
	public Line3DIterator(int x1, int y1, int z1, int x2, int y2, int z2, int precision) {
		srcX = x1;
		srcY = y1;
		srcZ = z1;
		dstX = x2;
		dstY = y2;
		dstZ = z2;
		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);
		dz = Math.abs(z2 - z1);
		sx = x1 < x2 ? precision : -precision;
		sy = y1 < y2 ? precision : -precision;
		sz = z1 < z2 ? precision : -precision;
		
		if ((dx >= dy) && (dx >= dz))         
			error = error2 = dx / 2;
		else if ((dy >= dx) && (dy >= dz))
			error = error2 = dy / 2;
		else
			error = error2 = dz / 2;
	}
	
	public Line3DIterator(int x1, int y1, int z1, int x2, int y2, int z2) {
		this(x1, y1, z1, x2, y2, z2, DEFAULT_PRECISION);
	}
	
	public Line3DIterator(IPosition3D start, IPosition3D end) {
		this(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ(), DEFAULT_PRECISION);
	}
	
	@Override
	public Iterator<Point3D> iterator() {
		return this;
	}
	
	public boolean isZAxisStep() {
		return zstep;
	}
	
	@Override
	public boolean hasNext() {
	    return (dx >= dy && dx >= dz && srcX != dstX) || 
	    		(dy >= dx && dy >= dz && srcY != dstY) || 
	    		srcZ != dstZ;
	}

	@Override
	public Point3D next() {
		if ((dx >= dy) && (dx >= dz)) {
			if (srcX != dstX) {
				srcX += sx;
				
				error += dy;
				if (error >= dx) {
					srcY += sy;
					error -= dx;
				}
				
				error2 += dz;
				if (error2 >= dx) {
					srcZ += sz;
					error2 -= dx;
				}
			}
		} else if ((dy >= dx) && (dy >= dz)) {
			if (srcY != dstY) {
				srcY += sy;
				
				error += dx;
				if (error >= dy) {
					srcX += sx;
					error -= dy;
				}
				
				error2 += dz;
				if (error2 >= dy) {
					srcZ += sz;
					error2 -= dy;
				}
			}
		} else {
			if (srcZ != dstZ) {
				srcZ += sz;
				zstep = true;
				
				error += dx;
				if (error >= dz) {
					srcX += sx;
					error -= dz;
					zstep = false;
				}
				
				error2 += dy;
				if (error2 >= dz) {
					srcY += sy;
					error2 -= dz;
					zstep = false;
				}
			}
		}
		
		return new Point3D(srcX, srcY, srcZ);
	}

	@Override
	public void remove() {
		throw new AssertionError();
	}
}
