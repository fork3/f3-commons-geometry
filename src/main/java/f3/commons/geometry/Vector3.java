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

import java.util.concurrent.ThreadLocalRandom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author n3k0nation
 *
 */
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Vector3 extends Vector2 implements Cloneable {
	public final static Vector3 ZERO = new Vector3(0, 0, 0);
	public final static Vector3 AXIS_X = new Vector3(1, 0, 0);
	public final static Vector3 AXIS_Y = new Vector3(0, 1, 0);
	public final static Vector3 AXIS_Z = new Vector3(0, 0, 1);
	
	
	public static Vector3 createRandomNormal3() {
		final ThreadLocalRandom rnd = ThreadLocalRandom.current();
		return new Vector3(
				(rnd.nextBoolean() ? -1 : 1) * rnd.nextFloat(), 
				(rnd.nextBoolean() ? -1 : 1) * rnd.nextFloat(),
				(rnd.nextBoolean() ? -1 : 1) * rnd.nextFloat()
		);
	}
	
	public static float magnitude2(float x, float y, float z) {
		return x*x + y*y + z*z;
	}
	
	public static float magnitude(float x, float y, float z) {
		return (float) Math.sqrt(magnitude2(x, y, z));
	}
	
	
	@Getter @Setter protected float z;
	
	public Vector3(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}
	
	public Vector3(Vector3 vector3) {
		super(vector3);
		this.z = vector3.z;
	}
	
	public Vector3(Point3D location) {
		super(location);
		this.z = location.getZ();
	}
	
	public int getIntZ() {
		return (int) z;
	}
	
	public int getRoundZ() {
		return Math.round(z);
	}
	
	@Override
	public Vector3 scale() {
		super.scale();
		this.z = Math.scalb(z, 2);
		return this;
	}
	
	public Vector3 set(float x, float y, float z) {
		super.set(x, y);
		this.z = z;
		return this;
	}
	
	@Override
	public Vector3 setZero() {
		return set(0f, 0f, 0f);
	}
	
	@Override
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	@Override
	public float magnitude2() {
		return x * x + y * y + z * z;
	}
	
	@Override
	public Vector3 normalize() {
		final float magnitude = magnitude();
		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
		return this;
	}
	
	public Vector3 add(Vector3 vector3) {
		return add(vector3.getX(), vector3.getY(), vector3.getZ());
	}
	
	public Vector3 add(float x, float y, float z) {
		super.add(x, y);
		this.z += z;
		return this;
	}
	
	public Vector3 substract(Vector3 vector3) {
		return substract(vector3.getX(), vector3.getY(), vector3.getZ());
	}
	
	public Vector3 substract(float x, float y, float z) {
		super.substract(x, y);
		this.z -= z;
		return this;
	}
	
	@Override
	public Vector3 multiply(float value) {
		super.multiply(value);
		this.z *= value;
		return this;
	}
	
	@Override
	public Vector3 divide(float value) {
		super.divide(value);
		this.z /= value;
		return this;
	}
	
	public float distance(Vector3 v) {
		final float dx = v.x - x,
				dy = v.y - y,
				dz = v.z - z;
		return magnitude(dx, dy, dz);
	}
	
	public float distance(float x, float y, float z) {
		final float dx = x - this.x,
				dy = y - this.y,
				dz = z - this.z;
		return magnitude(dx, dy, dz);
	}
	
	public Vector3 cross(Vector3 vector3) {
		final float cx = x,
				cy = y,
				cz = z;
		
		this.x = cy * vector3.z - cz * vector3.y;
		this.y = cz * vector3.x - cx * vector3.z;
		this.z = cx * vector3.y - cy * vector3.x;
		return this;
	}
	
	public float dot(Vector3 vector3) {
		return this.x * vector3.x + this.y * vector3.y + this.z * vector3.z;
	}
	
	public double angle(Vector3 vector3) {
		final double angle = Math.acos(dot(vector3) / magnitude() * vector3.magnitude());
		if(Double.isNaN(angle)) {
			return 0d;
		}
		return angle;
	}
	
	public Vector3 multiplyAndAdd(Vector3 v2, float mul2) {
		this.x += mul2 * v2.x;
		this.y += mul2 * v2.y;
		this.z += mul2 * v2.z;
		return this;
	}
	
	public Vector3 multiplyAndAdd(float mul1, Vector3 v2, float mul2) {
		this.x = mul1 * x + mul2 * v2.x;
		this.y = mul1 * y + mul2 * v2.y;
		this.z = mul1 * z + mul2 * v2.z;
		return this;
	}
	
	public boolean isSameDirection(Vector3 vector) {
		return dot(vector) > 0f;
	}
	
	public boolean isBackwardDirection(Vector3 vector) {
		return dot(vector) < 0f;
	}
	
	@Override
	public Vector3 clone() {
		return new Vector3(this);
	}
	
	public final Point3D toPoint3D() {
		return new Point3D(getRoundX(), getRoundY(), getRoundZ());
	}
	
	public final Vector2 toVector2() {
		return new Vector2(this.x, this.y);
	}
}
