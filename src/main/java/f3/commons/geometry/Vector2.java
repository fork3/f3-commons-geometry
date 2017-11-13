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
@EqualsAndHashCode
@ToString
public class Vector2 {
	protected final static float FLOAT_ERROR = 0.000001f;
	
	public final static Vector2 ZERO = new Vector2(0, 0);
	public final static Vector2 AXIS_X = new Vector2(1, 0);
	public final static Vector2 AXIS_Y = new Vector2(0, 1);
	
	public static Vector2 createRandomNormal2() {
		final ThreadLocalRandom rnd = ThreadLocalRandom.current();
		return new Vector2(
				(rnd.nextBoolean() ? -1 : 1) * rnd.nextFloat(), 
				(rnd.nextBoolean() ? -1 : 1) * rnd.nextFloat()
		);
	}
	
	public static float magnitude2(float x, float y) {
		return x*x + y*y;
	}
	
	public static float magnitude(float x, float y) {
		return (float) Math.sqrt(magnitude2(x, y));
	}
	
	@Getter @Setter protected float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public Vector2(IPosition2D location) {
		this.x = location.getX();
		this.y = location.getY();
	}
	
	public int getIntX() {
		return (int) x;
	}
	
	public int getIntY() {
		return (int) y;
	}
	
	public int getRoundX() {
		return Math.round(x);
	}
	
	public int getRoundY() {
		return Math.round(y);
	}
	
	public Vector2 scale() {
		this.x = Math.scalb(x, 2);
		this.y = Math.scalb(y, 2);
		return this;
	}
	
	public Vector2 set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2 setZero() {
		set(0f, 0f);
		return this;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float magnitude2() {
		return x * x + y * y;
	}
	
	public Vector2 normalize() {
		final float magnitude = magnitude();
		x /= magnitude;
		y /= magnitude;
		return this;
	}
	
	public Vector2 add(Vector2 vector) {
		return add(vector.getX(), vector.getY());
	}
	
	public Vector2 add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2 substract(Vector2 vector) {
		return substract(vector.getX(), vector.getY());
	}
	
	public Vector2 substract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2 multiply(float value) {
		this.x *= value;
		this.y *= value;
		return this;
	}
	
	public Vector2 divide(float value) {
		this.x /= value;
		this.y /= value;
		return this;
	}
	
	public float distance(Vector2 v) {
		return distance(v.getX(), v.getY());
	}
	
	public float distance(float x, float y) {
		final float dx = x - this.x,
				dy = y - this.y;
		return magnitude(dx, dy);
	}
	
	public float dot(Vector2 vector) {
		return this.x * vector.x + this.y * vector.y;
	}
	
	public double angle(Vector2 vector) {
		final double angle = Math.acos(dot(vector) / magnitude() * vector.magnitude());
		if(Double.isNaN(angle)) {
			return 0d;
		}
		return angle;
	}
	
	public Vector2 multiplyAndAdd(Vector2 v2, float mul2) {
		this.x += mul2 * v2.x;
		this.y += mul2 * v2.y;
		return this;
	}
	
	public Vector2 multiplyAndAdd(float mul1, Vector2 v2, float mul2) {
		this.x = mul1 * x + mul2 * v2.x;
		this.y = mul1 * y + mul2 * v2.y;
		return this;
	}
	
	public Vector2 rotate2(float angle) {
		final double rad = Math.toRadians(angle);
		this.x *= Math.cos(rad);
		this.y *= Math.sin(rad);
		return this;
	}
	
	public boolean isSameDirection(Vector2 vector) {
		return dot(vector) > 0f;
	}
	
	public boolean isBackwardDirection(Vector2 vector) {
		return dot(vector) < 0f;
	}
	
	@Override
	public Vector2 clone() {
		return new Vector2(this);
	}
	
	public final Point2D toPoint2D() {
		return new Point2D(getRoundX(), getRoundY());
	}
}
